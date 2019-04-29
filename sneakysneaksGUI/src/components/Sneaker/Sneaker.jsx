import React, { Component } from 'react';
import "./Sneaker.css";

class Sneaker extends Component {
    constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}
    handleDelete() {
		this.props.onDelete(this.props.sneaker);
    }
    render() {
        return (
            <div className="card text-white bg-primary mb-3 sneaker-container">
                <div className="card-header">{this.props.brand}</div>
                <div className="card-body">
                <h5 className="card-title">{this.props.name}</h5>
                <p className="card-text">The brand is {this.props.brand} and the size is {this.props.size}</p>
                <button type="button" className="btn btn-secondary" onClick={this.handleDelete}>Delete</button>
                </div>
            </div>
        );
    }
}

export default Sneaker;