import React, { Component } from 'react';
import {Link } from 'react-router-dom'

class Login extends Component {
  render() {
    return (
      <div className="">
        <form>
          <div className="form-group">
            <label htmlFor="exampleFormControlInput1">Email address</label>
            <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="name@example.com"/>

            <label htmlFor="exampleFormControlInput2">Password</label>
            <input type="password" className="form-control" id="exampleFormControlInput2" placeholder="----------"/>

            <Link to="/landing" className="btn btn-primary mb-2">Login</Link>
 
          </div>
            
          </form>
      </div>
        );
      }
    }
    
export default Login