import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import './Home.css'

class Home extends Component {
  constructor(props) {
    console.log("react is here")
    super(props);
    this.state = {
      welcome: ""
    }
  }

  componentDidMount() {
    setInterval(this.welcome, 250);
}

  welcome (){
    fetch('/api/')
        .then(response => response.text())
        .then(message => {
            this.setState({welcome: message})
        })
}

  render() {

    return (
      <div className="d-flex justify-content-center">
        <div className="jumbotron ">
          <h1 className="display-4">Welcome to SneakySneaks</h1>

          <p>{this.state.welcome}</p>
          <p>Come check out some gear</p>
          <hr className="my-4" />
          <div class="row justify-content-center">
            <div class="col-4">
              <Link to="/login" className="btn btn-primary">Login</Link>
            </div>
            <div class="col-4">
              <Link to="/register" className="btn btn-primary">Register</Link>
            </div>
          </div>
        </div>
      </div>
    )

  }
}

export default Home;