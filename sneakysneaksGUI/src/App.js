import React, { Component } from 'react';
import './App.css';
import NavBar from './components/NavBar/NavBar';
import Home from './components/Home/Home'

import {
  BrowserRouter as Router,
  Route, Switch
} from "react-router-dom";
import Login from './components/Login/Login';
import Landing from './components/Landing/Landing';
import CreateSneaker from './components/CreateSneaker/CreateSneaker';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      sneakers: [],
      isLoading: true,
      groups: []
      //user: props.location.state
    }
  }

  render() {
    return (
      <div className="App container-fluid">
        <Router>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/landing" component={Landing} />
            <Route path="/addsneaker" component={CreateSneaker} />
            {/* <Route path="/register" component />
            <Route path="/about" component />
            <Route path="/sneakers" component />
            <Route path="/mysneakers" component /> */}
            <NavBar />
          </Switch>
        </Router>


        {/* <SneakerList sneakers={this.state.sneakers}/> */}
      </div>

    );
  }
}

export default App;
