import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';


class Landing extends Component {
  constructor(props){
    super(props)
    this.state = {
      sneakers: [],
      users: [],
    }
  }

  componentDidMount() {
    fetch('/api/getSneakers')
      .then(response => response.json())
      .then(response => {
        let sneakers = response;
        this.setState({ sneakers: sneakers });
      })
      .catch(err => {
        console.log(err)
      })

      fetch('/api/users')
      .then(response => response.json())
      .then(response => {
        let users = response;
        this.setState({ users: users });
      })
      .catch(err => {
        console.log(err)
      }) 
  }

  render() {

    console.log(this.state.sneakers)
    return (
      <div className="">
        <h1>This is the landing page. Welcome Dana</h1>
        <ErrorBoundary>
          <SneakerList sneakers={this.state.sneakers} listName="Main"/>
        </ErrorBoundary>
        
      </div>
    );
  }
}

export default Landing