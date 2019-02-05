import React, { Component } from 'react';
import {Link } from 'react-router-dom'

class Landing extends Component {
  render() {
    return (
      <div className="">
        <form>
          <div class="form-group">
            <label for="exampleFormControlInput1">Email address</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com"/>

            <label for="exampleFormControlInput2">Password</label>
            <input type="password" class="form-control" id="exampleFormControlInput2" placeholder="----------"/>

            <Link to="/landing" className="btn btn-primary mb-2">Login</Link>
 
          </div>
            
          </form>
      </div>
        );
      }
    }
    
export default Landing