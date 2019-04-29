import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ReactDOM from 'react-dom'
import Popup from "reactjs-popup";

class CreateSneaker extends Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const newSneaker = {};
        this.props.attributes.forEach(attribute => {
            newSneaker[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newSneaker);

        // clear out the dialog's inputs
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = '';
        });

        // Navigate away from the dialog to hide it.
        window.location = "#";
    }

    render() {
        const inputs = this.props.attributes.map(attribute =>
            <p key={attribute} className="form-group row">
                <input type="text" placeholder={attribute} ref={attribute} className="field form-control" />
            </p>
        );

        return (
            <div>
                <button type="button" className="btn btn-warning">Create</button>
                <div id="createSneaker" className="modalDialog">

                    <a href="#" title="Close" className="close">X</a>

                    <form>
                        {inputs}
                        <button className="btn btn-primary" onClick={this.handleSubmit}>Create</button>
                    </form>
                </div>
            </div>
        )
    }

}

export default CreateSneaker;