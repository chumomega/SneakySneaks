import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';

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
        <SneakerList sneakers={this.state.sneakers} listName="Main"/>
        


      </div>
    );
  }
}

export default Landing