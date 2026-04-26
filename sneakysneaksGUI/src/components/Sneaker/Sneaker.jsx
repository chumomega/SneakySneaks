import React from 'react';
import UpdateSneaker from '../UpdateSneaker/UpdateSneaker';
import SneakerImage from '../SneakerImage/SneakerImage';
import './Sneaker.css';

function Sneaker({ sneaker, onUpdate, onDelete, loggedInUser }) {
    const isOwner = sneaker.entity.user?.name === loggedInUser;

    return (
        <div className="sneaker-card">
            <div className="sneaker-img-wrap">
                <SneakerImage src={sneaker.entity.picture} alt={sneaker.entity.name} />
            </div>
            <div className="sneaker-body">
                <div className="sneaker-brand">{sneaker.entity.brand}</div>
                <div className="sneaker-name">{sneaker.entity.name}</div>
                <div className="sneaker-meta">Size {sneaker.entity.size} · ${sneaker.entity.price}</div>
                {sneaker.entity.about && <p className="sneaker-about">{sneaker.entity.about}</p>}
                <div className="sneaker-owner">@{sneaker.entity.user?.name}</div>
                {isOwner && (
                    <div className="sneaker-actions">
                        <UpdateSneaker sneaker={sneaker} onUpdate={onUpdate} />
                        <button
                            type="button"
                            className="btn btn-outline-danger btn-sm ml-2"
                            onClick={() => onDelete(sneaker)}
                        >
                            Delete
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Sneaker;
