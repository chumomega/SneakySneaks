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
            file: null
        }
    }

    handleSubmit(e) {
        e.preventDefault();
        const newSneaker = {};
        const selectedFile = document.getElementById('image').files[0];
        var filepath = ReactDOM.findDOMNode(this.refs['picture']).value.trim();
        var storageRef = this.props.storage_service.ref().child(filepath);
        
        var upload_task = storageRef.put(selectedFile)
        var this_object = this

        // Register three observers:
        // 1. 'state_changed' observer, called any time the state changes
        // 2. Error observer, called on failure
        // 3. Completion observer, called on successful completion
        upload_task.on('state_changed', function(snapshot){
            // Observe state change events such as progress, pause, and resume
            // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
            var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            // console.log('Upload is ' + progress + '% done');
            // switch (snapshot.state) {
            //     case firebase.storage.TaskState.PAUSED: // or 'paused'
            //         console.log('Upload is paused');
            //         break;
            //     case firebase.storage.TaskState.RUNNING: // or 'running'
            //         console.log('Upload is running');
            //         break;
            // }
        }, function(error) {
            // Handle unsuccessful uploads
        }, function() {
            // Handle successful uploads on complete
            // For instance, get the download URL: https://firebasestorage.googleapis.com/...
            upload_task.snapshot.ref.getDownloadURL().then(function(downloadURL) {
                //TODO - create new sneaker out of properties in the state
                this_object.props.attributes.forEach(attribute => {
                    if (attribute == 'picture') {
                        newSneaker[attribute] = downloadURL;
                    } else {
                        newSneaker[attribute] = ReactDOM.findDOMNode(this_object.refs[attribute]).value.trim();
                    }
                });
                this_object.props.onCreate(newSneaker);

                // clear out the dialog's inputs
                this_object.props.attributes.forEach(attribute => {
                    ReactDOM.findDOMNode(this_object.refs[attribute]).value = '';
                });
                this_object.setState({
                    file: null
                  })
            })
        })


        // Navigate away from the dialog to hide it.
        window.location = "#";
    }

    previewImage(event) {
        this.setState({
            file: URL.createObjectURL(event.target.files[0])
          })
    }
    
    getInputs() {
        let inputs = []
        var this_object = this
        this.props.attributes.forEach(function(attribute){
            if (attribute == "picture") {
                inputs.push(
                    <p key={attribute} className="form-group row">
                        <input type="file" ref={attribute} name="pic" accept="image/*" id="image" placeholder="Image" onChange={this_object.previewImage} />
                        <img id="preview" src={this_object.state.file} height="200" alt="Preview"></img>
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
