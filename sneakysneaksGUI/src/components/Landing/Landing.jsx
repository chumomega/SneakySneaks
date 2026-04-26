import React, { useCallback, useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import SneakerList from '../SneakerList/SneakerList';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';
import CreateSneaker from '../CreateSneaker/CreateSneaker';
import client from '../../clientAndApi/client';
import { useAuth } from '../../auth';
import './Landing.css';

const stompClient = require('../../clientAndApi/websocket-listener');
const PAGE_SIZE = 6;

function Landing() {
    const { user, loading: authLoading } = useAuth();
    const [sneakers, setSneakers] = useState([]);
    const [page, setPage] = useState({});
    const [links, setLinks] = useState({});
    const [loading, setLoading] = useState(true);

    const loadFrom = useCallback((path) => {
        setLoading(true);
        client({ method: 'GET', path })
            .then(res => {
                setLinks(res.entity._links || {});
                setPage(res.entity.page || {});
                const items = res.entity._embedded?.sneakers || [];
                return Promise.all(items.map(s => client({ method: 'GET', path: s._links.self.href })));
            })
            .then(detailed => setSneakers(detailed))
            .catch(() => setSneakers([]))
            .then(() => setLoading(false));
    }, []);

    useEffect(() => {
        if (authLoading || !user) return;
        const myUrl = `/api/sneakers/search/findByOwner?name=${encodeURIComponent(user)}&size=${PAGE_SIZE}&sort=id,desc`;
        loadFrom(myUrl);

        const refresh = () => loadFrom(myUrl);
        stompClient.register([
            { route: '/topic/newSneaker', callback: refresh },
            { route: '/topic/updateSneaker', callback: refresh },
            { route: '/topic/deleteSneaker', callback: refresh },
        ]);
    }, [authLoading, user, loadFrom]);

    const onCreate = (newSneaker) => {
        client({
            method: 'POST',
            path: '/api/sneakers',
            entity: newSneaker,
            headers: { 'Content-Type': 'application/json' },
        }).catch(() => alert('Failed to create sneaker.'));
    };

    const onUpdate = (sneaker, updated) => {
        if (sneaker.entity.user?.name !== user) {
            alert("You can only edit your own sneakers.");
            return;
        }
        updated.user = sneaker.entity.user;
        client({
            method: 'PUT',
            path: sneaker.entity._links.self.href,
            entity: updated,
            headers: {
                'Content-Type': 'application/json',
                'If-Match': sneaker.headers.Etag,
            },
        }).catch(res => {
            if (res?.status?.code === 403) alert('You are not authorized to edit this.');
            else if (res?.status?.code === 412) alert('Your copy is stale — refresh and try again.');
            else alert('Failed to update sneaker.');
        });
    };

    const onDelete = (sneaker) => {
        if (!window.confirm('Delete this sneaker?')) return;
        client({ method: 'DELETE', path: sneaker.entity._links.self.href })
            .catch(res => {
                if (res?.status?.code === 403) alert('You are not authorized to delete this.');
                else alert('Failed to delete sneaker.');
            });
    };

    if (authLoading) return <p className="text-muted">Loading…</p>;
    if (!user) return <Navigate to="/login" replace />;

    return (
        <div className="landing">
            <div className="landing-header">
                <div>
                    <h1>Welcome, {user}</h1>
                    <p className="text-muted">Your closet and the latest from everyone.</p>
                </div>
                <CreateSneaker onCreate={onCreate} />
            </div>

            <ErrorBoundary>
                <SneakerList
                    sneakers={sneakers}
                    loading={loading}
                    links={links}
                    page={page}
                    onNavigate={loadFrom}
                    onUpdate={onUpdate}
                    onDelete={onDelete}
                    loggedInUser={user}
                />
            </ErrorBoundary>
        </div>
    );
}

export default Landing;
