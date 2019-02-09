import React, { Component } from 'react';

class Sneaker extends Component {
  render() {
    return (
      <div className="card text-white bg-primary mb-3 sneaker-container">
        <div className="card-header">{this.props.brand}</div>
        <div className="card-body">
          <h5 className="card-title">{this.props.name}</h5>
          <p className="card-text">The brand is {this.props.brand} and the size is {this.props.size}</p>
        </div>
      </div>
    );
  }
}

export default Sneaker;