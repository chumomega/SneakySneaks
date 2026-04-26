import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../../auth';

function NavBar() {
    const { user, loading } = useAuth();

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
            <Link to="/" className="navbar-brand">SneakySneaks</Link>
            <div className="navbar-nav mr-auto">
                <Link to="/" className="nav-item nav-link">Feed</Link>
                {user && <Link to="/landing" className="nav-item nav-link">My Closet</Link>}
            </div>
            <div className="navbar-nav ml-auto">
                {loading ? null : user ? (
                    <>
                        <span className="navbar-text mr-3">Hi, {user}</span>
                        <form action="/logout" method="POST" className="form-inline">
                            <button type="submit" className="btn btn-outline-light btn-sm">Log out</button>
                        </form>
                    </>
                ) : (
                    <>
                        <Link to="/login" className="nav-item nav-link">Log in</Link>
                        <Link to="/signup" className="btn btn-primary btn-sm ml-2">Sign up</Link>
                    </>
                )}
            </div>
        </nav>
    );
}

export default NavBar;
