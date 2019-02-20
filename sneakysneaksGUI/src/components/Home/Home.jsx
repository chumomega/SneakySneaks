import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import './Home.css'

class Home extends Component {
  constructor(props) {
    super(props);
  }

  render() {

    return (
      <div className="d-flex justify-content-center">
        <div className="jumbotron ">
          <h1 className="display-4">Welcome to SneakySneaks</h1>

          <p>Come check out some gear</p>
          <hr className="my-4" />
          <div className="row justify-content-center">
            <div className="col-4">
              <Link to="/login" className="btn btn-primary">Login</Link>
            </div>
            <div className="col-4">
              <Link to="/register" className="btn btn-primary">Register</Link>
            </div>
          </div>
        </div>
      </div>
    )

  }
}

export default Home;