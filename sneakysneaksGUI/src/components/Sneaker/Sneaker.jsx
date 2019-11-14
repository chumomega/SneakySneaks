import React, { Component } from 'react';
import UpdateSneaker from '../UpdateSneaker/UpdateSneaker'
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
                <div className="card-header">{this.props.sneaker.entity.brand}</div>
                <div className="card-body">
                <h5 className="card-title">{this.props.sneaker.entity.name}</h5>
                <p className="card-text">The brand is {this.props.sneaker.entity.brand} and the size is {this.props.sneaker.entity.size}</p>
                <UpdateSneaker 
                    sneaker={this.props.sneaker}
                    attributes={this.props.attributes}
                    onUpdate={this.props.onUpdate}
					loggedInUser={this.props.loggedInUser}/>
                <button type="button" className="btn btn-secondary" onClick={this.handleDelete}>Delete</button>
                </div>
            </div>
        );
    }
}

export default Sneaker;
///*<p className="card-text">This sneaker is owned by {this.props.sneaker.user.firstName}</p>*/