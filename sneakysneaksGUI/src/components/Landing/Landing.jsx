import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';

class Landing extends Component {
  constructor(props){
    super(props)
    this.state = {
      sneakers: [],
    }
  }

  componentDidMount() {
    fetch('/api/getSneakers')
      .then(response => response.json())
      .then(response => {
        let sneakers = response;
        this.setState({ sneakers: sneakers });

        // console.log(" This is the number of clubs in the carousel " + this.state.books.length);
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