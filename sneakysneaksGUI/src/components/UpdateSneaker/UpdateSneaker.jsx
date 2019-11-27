import React, { Component } from 'react';
import ReactDOM from 'react-dom'

class UpdateSneaker extends Component {

    constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const updatedSneaker = {};
		this.props.attributes.forEach(attribute => {
            updatedSneaker[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
            
        });
        
		this.props.onUpdate(this.props.sneaker, updatedSneaker);
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes.map(attribute =>
			<p key={this.props.sneaker.entity[attribute]}>
				<input type="text" placeholder={attribute}
					   defaultValue={this.props.sneaker.entity[attribute]}
					   ref={attribute} className="field"/>
			</p>
		);

		const dialogId = "updateSneaker-" + this.props.sneaker.entity._links.self.href;

		return (
			<div key={this.props.sneaker.entity._links.self.href}>
				<a href={"#" + dialogId}>Update</a>
				<div id={dialogId} className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Update a sneaker</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Update</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

}

export default UpdateSneaker;