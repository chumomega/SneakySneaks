import React, { useState } from 'react';
import Modal from '../Modal/Modal';

const FIELDS = [
    { name: 'brand', label: 'Brand', placeholder: 'Nike, Adidas, ...' },
    { name: 'name', label: 'Model', placeholder: 'Off-White Air Force One' },
    { name: 'size', label: 'Size', placeholder: '10', type: 'number' },
    { name: 'price', label: 'Price ($)', placeholder: '150', type: 'number' },
    { name: 'about', label: 'About', placeholder: 'A short note about these' },
    { name: 'picture', label: 'Image URL', placeholder: 'https://...' },
];

function emptyForm() {
    return FIELDS.reduce((acc, f) => ({ ...acc, [f.name]: '' }), {});
}

function CreateSneaker({ onCreate }) {
    const [open, setOpen] = useState(false);
    const [form, setForm] = useState(emptyForm());

    const update = (field) => (e) => setForm({ ...form, [field]: e.target.value });

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = { ...form };
        if (payload.size) payload.size = Number(payload.size);
        if (payload.price) payload.price = Number(payload.price);
        onCreate(payload);
        setForm(emptyForm());
        setOpen(false);
    };

    return (
        <>
            <button type="button" className="btn btn-primary" onClick={() => setOpen(true)}>
                + Add a sneaker
            </button>
            <Modal open={open} onClose={() => setOpen(false)} title="Add a sneaker">
                <form onSubmit={handleSubmit}>
                    {FIELDS.map(f => (
                        <div key={f.name} className="form-group">
                            <label htmlFor={`cs-${f.name}`}>{f.label}</label>
                            <input
                                id={`cs-${f.name}`}
                                type={f.type || 'text'}
                                placeholder={f.placeholder}
                                value={form[f.name]}
                                onChange={update(f.name)}
                                className="form-control"
                                required={f.name !== 'about'}
                            />
                        </div>
                    ))}
                    <button type="submit" className="btn btn-primary">Create</button>
                </form>
            </Modal>
        </>
    );
}

export default CreateSneaker;
