import React from 'react';
import { Link, useSearchParams } from 'react-router-dom';

function Login() {
    const [searchParams] = useSearchParams();
    const hasError = searchParams.has('error');

    return (
        <div className="auth-card">
            <h2>Log in</h2>
            {hasError && (
                <div className="alert alert-danger" role="alert">
                    Invalid username or password.
                </div>
            )}
            <form action="/login" method="POST">
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        name="username"
                        id="username"
                        className="form-control"
                        required
                        autoFocus
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        name="password"
                        id="password"
                        className="form-control"
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary btn-block">Log in</button>
            </form>
            <p className="auth-footer">
                New here? <Link to="/signup">Create an account</Link>
            </p>
        </div>
    );
}

export default Login;
