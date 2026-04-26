import React, { useState } from 'react';
import Modal from '../Modal/Modal';

const EDITABLE = ['brand', 'name', 'size', 'price', 'about', 'picture'];

function UpdateSneaker({ sneaker, onUpdate }) {
    const [open, setOpen] = useState(false);
    const [form, setForm] = useState(() =>
        EDITABLE.reduce((acc, f) => ({ ...acc, [f]: sneaker.entity[f] ?? '' }), {})
    );

    const update = (field) => (e) => setForm({ ...form, [field]: e.target.value });

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = { ...form };
        if (payload.size) payload.size = Number(payload.size);
        if (payload.price) payload.price = Number(payload.price);
        onUpdate(sneaker, payload);
        setOpen(false);
    };

    return (
        <>
            <button type="button" className="btn btn-outline-primary btn-sm" onClick={() => setOpen(true)}>
                Edit
            </button>
            <Modal open={open} onClose={() => setOpen(false)} title="Edit sneaker">
                <form onSubmit={handleSubmit}>
                    {EDITABLE.map(field => (
                        <div key={field} className="form-group">
                            <label htmlFor={`us-${field}`}>{field.charAt(0).toUpperCase() + field.slice(1)}</label>
                            <input
                                id={`us-${field}`}
                                type={['size', 'price'].includes(field) ? 'number' : 'text'}
                                value={form[field]}
                                onChange={update(field)}
                                className="form-control"
                            />
                        </div>
                    ))}
                    <button type="submit" className="btn btn-primary">Save</button>
                </form>
            </Modal>
        </>
    );
}

export default UpdateSneaker;
