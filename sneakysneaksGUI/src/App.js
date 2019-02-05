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

class App extends Component {
  state = {
    isLoading: true,
    groups: []
  };

  constructor(props) {
    super();
    this.state = {
      sneakers: [],
      //user: props.location.state
    }
  }
  // componentDidMount() {
  //   fetch('/api/getSneakers')
  //     .then(response => response.json())
  //     .then(json => {
  //       let sneakers = json.items;
  //       this.setState({ sneakers: sneakers });
  //       // console.log(" This is the number of clubs in the carousel " + this.state.books.length);
  //     })
  //     .catch(err => {
  //       console.log(err)
  //     })
  // }

  render() {
    return (
      <div className="App container-fluid">

        <Router>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/landing" component={Landing} />
            <Route path="/register" component />
            <Route path="/about" component />
            <Route path="/sneakers" component />
            <Route path="/mysneakers" component />
            <NavBar />
          </Switch>
        </Router>


        {/* <SneakerList sneakers={this.state.sneakers}/> */}
      </div>

    );
  }
}

export default App;
