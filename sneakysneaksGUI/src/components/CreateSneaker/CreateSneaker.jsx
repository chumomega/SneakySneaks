import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ReactDOM from 'react-dom'
import Popup from "reactjs-popup";

class CreateSneaker extends Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.getInputs = this.getInputs.bind(this);
        this.previewImage = this.previewImage.bind(this);
        this.state = {
            size: '',
            price: '',
            name: '',
            about: '',
            brand: '',
            picture: '',
        }
    }

    handleSubmit(e) {
        e.preventDefault();
        const newSneaker = {};
        const selectedFile = document.getElementById('image').files[0];
        var filepath = ReactDOM.findDOMNode(this.refs['picture']).value.trim();
        var storageRef = this.props.storage_service.ref().child(filepath);
        
        console.log("this is the filepath: " + filepath)
        storageRef.put(selectedFile).then(function(snapshot) {
            console.log('Uploaded a blob or file!');
        })

        //TODO - create new sneaker out of properties in the state
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

    previewImage(event) {

    }
    
    getInputs() {
        let inputs = []
        this.props.attributes.forEach(function(attribute){
            if (attribute == "picture") {
                inputs.push(
                    <p key={attribute} className="form-group row">
                        <input type="file" ref={attribute} name="pic" accept="image/*" id="image" placeholder="Image"/>
                        <img src="" height="200" alt="Preview"></img>
                    </p>
                )
            }
            else {
                inputs.push(
                    <p key={attribute} className="form-group row">
                        <input type="text" placeholder={attribute} ref={attribute} className="field form-control" />
                    </p>
                )
            }
        })
        return inputs
    }

    render() {
        const inputs = this.getInputs()

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
