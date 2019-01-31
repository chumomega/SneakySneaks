import React, { Component } from 'react';
import './App.css';
import NavBar from './components/NavBar/NavBar';
import Home from './components/Home/Home'

import {
  BrowserRouter as Router,
  Route, Switch
} from "react-router-dom";
import Login from './components/Login/Login';

class App extends Component {
  render() {
    return (
      <div className="App container-fluid">
      
        <Router>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/register" component />
            <Route path="/about" component />
            <Route path="/sneakers" component />
            <Route path="/mysneakers" component />
            <NavBar />
          </Switch>
        </Router>
      </div>

    );
  }
}

export default App;
