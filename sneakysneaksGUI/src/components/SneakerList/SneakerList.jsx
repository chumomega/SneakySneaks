import React from 'react';
import Sneaker from '../Sneaker/Sneaker';
import './SneakerList.css';

function SneakerList({ sneakers, loading, links, page, onNavigate, onUpdate, onDelete, loggedInUser }) {
    if (loading) {
        return <p className="text-muted">Loading sneakers…</p>;
    }
    if (!sneakers.length) {
        return (
            <div className="empty-state">
                <p>No sneakers in the closet yet.</p>
                <p className="text-muted">Click <strong>Add a sneaker</strong> to get started.</p>
            </div>
        );
    }

    const cards = sneakers.map(s => (
        <Sneaker
            key={s.entity._links.self.href}
            sneaker={s}
            onUpdate={onUpdate}
            onDelete={onDelete}
            loggedInUser={loggedInUser}
        />
    ));

    const navButton = (rel, label) =>
        links?.[rel] ? (
            <button
                key={rel}
                type="button"
                className="btn btn-outline-secondary btn-sm"
                onClick={() => onNavigate(links[rel].href)}
            >
                {label}
            </button>
        ) : null;

    return (
        <>
            {page?.totalPages > 1 && (
                <div className="page-info">
                    Page {page.number + 1} of {page.totalPages}
                </div>
            )}
            <div className="sneaker-grid">{cards}</div>
            <div className="sneaker-nav">
                {navButton('first', '« First')}
                {navButton('prev', '‹ Prev')}
                {navButton('next', 'Next ›')}
                {navButton('last', 'Last »')}
            </div>
        </>
    );
}

export default SneakerList;
