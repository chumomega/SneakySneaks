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
            <div id="createSneaker" className="modalDialog">
                <Popup trigger={<button type="button" className="btn btn-warning">Create</button>}>
                    <h2>Create new Sneaker</h2>
                    <form>
                        {inputs}
                        <button className="btn btn-primary" onClick={this.handleSubmit}>Create</button>
                    </form>
                </Popup>
            </div>
        )
    }

}

export default CreateSneaker;