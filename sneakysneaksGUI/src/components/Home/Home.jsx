import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import './Home.css'

class Home extends Component {
    render() {

        return (
            <div className="d-flex justify-content-center">
                <div className="jumbotron ">
                    <div className="page-header">
                        <h1 className="display-4">Welcome to SneakySneaks!</h1>
                        <p>Come check out some gear</p>
                    </div>
                    
                    <div className="row">
                        <div className="col">
                            <div className="card text-white bg-info mb-3 col-4">
                                <div className="card-body">
                                    <h5 className="card-title">Sneaker Closet</h5>
                                    <p className="card-text">This is where you can see your sneakers</p>
                                    <Link to="/landing" className="btn btn-primary">My closet</Link>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card text-white bg-info mb-3 col-4">
                                <div className="card-body">
                                    <h5 className="card-title">Public Closet</h5>
                                    <p className="card-text">This is where you can see your public sneakers</p>
                                    <Link to="/landing" className="btn btn-primary">My closet</Link>
                                </div>
                            </div>
                        </div>
                        <div className="w-100"></div>
                        <div className="col">
                            <div className="card text-white bg-info mb-3 col-4">
                                <div className="card-body">
                                    <h5 className="card-title">People</h5>
                                    <p className="card-text">View the different users</p>
                                    <Link to="/landing" className="btn btn-primary">Users</Link>
                                </div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="card text-white bg-info mb-3 col-4">
                                <div className="card-body">
                                    <h5 className="card-title">Profile</h5>
                                    <p className="card-text">This is just your profile page</p>
                                    <Link to="/landing" className="btn btn-primary">My Profile</Link>
                                </div>
                            </div>
                        </div>
                    </div>









                </div>
            </div>
        )

    }
}

export default Home;