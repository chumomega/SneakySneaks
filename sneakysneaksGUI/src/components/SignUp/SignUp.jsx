import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function SignUp() {
    const navigate = useNavigate();
    const [form, setForm] = useState({ name: '', password: '', email: '', description: '' });
    const [error, setError] = useState(null);
    const [submitting, setSubmitting] = useState(false);

    const update = (field) => (e) => setForm({ ...form, [field]: e.target.value });

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setSubmitting(true);
        try {
            const res = await fetch('/api/save', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(form),
            });
            if (!res.ok) {
                const data = await res.json().catch(() => ({}));
                setError(data.error || 'Sign up failed.');
                return;
            }
            navigate('/login');
        } catch {
            setError('Network error. Try again.');
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className="auth-card">
            <h2>Create your account</h2>
            {error && <div className="alert alert-danger">{error}</div>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="su-name">Username</label>
                    <input id="su-name" type="text" className="form-control" value={form.name} onChange={update('name')} required autoFocus />
                </div>
                <div className="form-group">
                    <label htmlFor="su-password">Password</label>
                    <input id="su-password" type="password" className="form-control" value={form.password} onChange={update('password')} required minLength={6} />
                </div>
                <div className="form-group">
                    <label htmlFor="su-email">Email <span className="text-muted">(optional)</span></label>
                    <input id="su-email" type="email" className="form-control" value={form.email} onChange={update('email')} />
                </div>
                <div className="form-group">
                    <label htmlFor="su-desc">Bio <span className="text-muted">(optional)</span></label>
                    <input id="su-desc" type="text" className="form-control" value={form.description} onChange={update('description')} placeholder="Sneakerhead since '04" />
                </div>
                <button type="submit" className="btn btn-primary btn-block" disabled={submitting}>
                    {submitting ? 'Creating…' : 'Sign up'}
                </button>
            </form>
            <p className="auth-footer">
                Already have an account? <Link to="/login">Log in</Link>
            </p>
        </div>
    );
}

export default SignUp;
