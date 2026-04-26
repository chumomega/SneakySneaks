import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import client from '../../clientAndApi/client';
import { useAuth } from '../../auth';
import SneakerImage from '../SneakerImage/SneakerImage';
import './Home.css';

function Home() {
    const { user, loading: authLoading } = useAuth();
    const [sneakers, setSneakers] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        client({ method: 'GET', path: '/api/sneakers?size=12&sort=id,desc' })
            .then(res => {
                const items = res.entity._embedded?.sneakers || [];
                return Promise.all(items.map(s => client({ method: 'GET', path: s._links.self.href })));
            })
            .then(detailed => setSneakers(detailed))
            .catch(() => setSneakers([]))
            .then(() => setLoading(false));
    }, []);

    return (
        <div className="home">
            <div className="hero">
                <h1>Show off your sneakers.</h1>
                <p className="lead">Build your closet. Browse what others are rocking. No catch.</p>
                {!authLoading && (
                    user ? (
                        <Link to="/landing" className="btn btn-primary btn-lg">Go to my closet →</Link>
                    ) : (
                        <div className="hero-cta">
                            <Link to="/signup" className="btn btn-primary btn-lg">Sign up free</Link>
                            <Link to="/login" className="btn btn-outline-secondary btn-lg ml-2">Log in</Link>
                        </div>
                    )
                )}
            </div>

            <h2 className="feed-heading">Latest from the community</h2>
            {loading ? (
                <p className="text-muted">Loading the feed…</p>
            ) : sneakers.length === 0 ? (
                <p className="text-muted">No sneakers yet — be the first to add one.</p>
            ) : (
                <div className="feed-grid">
                    {sneakers.map(s => (
                        <div key={s.entity._links.self.href} className="feed-card">
                            <SneakerImage src={s.entity.picture} alt={s.entity.name} />
                            <div className="feed-card-body">
                                <div className="feed-card-brand">{s.entity.brand}</div>
                                <div className="feed-card-name">{s.entity.name}</div>
                                <div className="feed-card-meta">
                                    Size {s.entity.size} · ${s.entity.price}
                                </div>
                                <div className="feed-card-owner">@{s.entity.user?.name}</div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default Home;
