import React, { useEffect } from 'react';
import './Modal.css';

function Modal({ open, onClose, title, children }) {
    useEffect(() => {
        if (!open) return;
        const onKey = (e) => { if (e.key === 'Escape') onClose(); };
        window.addEventListener('keydown', onKey);
        return () => window.removeEventListener('keydown', onKey);
    }, [open, onClose]);

    if (!open) return null;

    return (
        <div className="modal-backdrop-custom" onClick={onClose}>
            <div className="modal-card" onClick={(e) => e.stopPropagation()}>
                <div className="modal-card-header">
                    <h3>{title}</h3>
                    <button type="button" className="modal-close" onClick={onClose} aria-label="Close">×</button>
                </div>
                <div className="modal-card-body">{children}</div>
            </div>
        </div>
    );
}

export default Modal;
