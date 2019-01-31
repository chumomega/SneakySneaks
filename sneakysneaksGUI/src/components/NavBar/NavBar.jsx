import React, { Component } from 'react';
import {
  Link
} from 'react-router-dom'

class NavBar extends Component {
  render() {

    return (
      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <Link to="/" className="navbar-brand">SneakySneaks</Link>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
              <Link to="/login" className="nav-item nav-link">Login</Link>
              <Link to="/register" className="nav-item nav-link">Register</Link>
              <Link to="/about" className="nav-item nav-link">About</Link>
              <Link to="/sneakers" className="nav-item nav-link disabled">Sneakers</Link>
              <Link to="/mysneakers" className="nav-item nav-link disabled">My Sneakers</Link>
            </div>
          </div>
        </nav>
      </div>

    )

  }
}

export default NavBar;