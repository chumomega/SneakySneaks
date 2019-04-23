import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';
import CreateSneaker from '../CreateSneaker/CreateSneaker';
import client from '../../clientAndApi/client';
import "./Landing.css";


import { Link } from 'react-router-dom'

import follow from '../../clientAndAPi/follow'; // function to hop multiple links by "rel"

const root = '/api';
const when = require('when');

class Landing extends Component {
    constructor(props) {
        super(props)
        this.state = {
            sneakers: [],
            attributes: [],
            pageSize: 3,
            links: {}
        }

        this.updatePageSize = this.updatePageSize.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onNavigate = this.onNavigate.bind(this);
    }


    componentDidMount() {
        this.loadFromServer(this.state.pageSize)
    }

    loadFromServer(pageSize) {
        follow(client, root, [
            { rel: 'sneakers', params: { size: pageSize } }]
        ).then(sneakerCollection => {
            return client({
                method: 'GET',
                path: sneakerCollection.entity._links.profile.href,
                headers: { 'Accept': 'application/schema+json' }
            }).then(schema => {
                this.schema = schema.entity;
                this.links = sneakerCollection.entity._links;
                return sneakerCollection;
            });
        }).then(sneakerCollection => {
            return sneakerCollection.entity._embedded.sneakers.map(sneaker =>
                    client({
                        method: 'GET',
                        path: sneaker._links.self.href
                    })
            );
	}).then(sneakerPromises => {
		return when.all(sneakerPromises);
        
        }).done(sneakers => {
            this.setState({
                sneakers: sneakers,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: this.links
            });
        });
    }

    onCreate(newSneaker) {
        follow(client, root, ['sneakers']).then(sneakerCollection => {
            return client({
                method: 'POST',
                path: sneakerCollection.entity._links.self.href,
                entity: newSneaker,
                headers: { 'Content-Type': 'application/json' }
            })
        }).then(response => {
            return follow(client, root, [
                { rel: 'sneakers', params: { 'size': this.state.pageSize } }]);
        }).done(response => {
            if (typeof response.entity._links.last !== "undefined") {
                this.onNavigate(response.entity._links.last.href);
            } else {
                this.onNavigate(response.entity._links.self.href);
            }
        });
    }

    // tag::update[]
	onUpdate(sneaker, updatedSneaker) {
		client({
			method: 'PUT',
			path: sneaker.entity._links.self.href,
			entity: updatedSneaker,
			headers: {
				'Content-Type': 'application/json',
				'If-Match': sneaker.headers.Etag
			}
		}).done(response => {
			this.loadFromServer(this.state.pageSize);
		}, response => {
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' +
					sneaker.entity._links.self.href + '. Your copy is stale.');
			}
		});
	}
	// end::update[]

    onNavigate(navUri) {
        client({ 
            method: 'GET', 
            path: navUri 
        }).then(sneakerCollection => {
            this.links = sneakerCollection.entity._links;

            return sneakerCollection.entity._embedded.sneakers.map(sneaker =>
                        client({
                            method: 'GET',
                            path: sneaker._links.self.href
                        })
                );
            }).then(sneakerPromises => {
                return when.all(sneakerPromises);
            }).done(sneakers => {
                this.setState({
                    sneakers: sneakers,
                    attributes: Object.keys(this.schema.properties),
                    pageSize: this.state.pageSize,
                    links: this.links
                });
            });
    }

    onDelete(sneaker) {
        client({ method: 'DELETE', path: sneaker._links.self.href }).done(response => {
            this.loadFromServer(this.state.pageSize);
        });
    }

    updatePageSize(pageSize) {
        if (pageSize !== this.state.pageSize) {
            this.loadFromServer(pageSize);
        }
    }


    render() {
        return (
            <div className="d-flex justify-content-center">
                <div className="jumbotron">
                    <div className="page-header">
                        <h1>This is the landing page. Welcome Dana</h1>
                        <CreateSneaker attributes={this.state.attributes} onCreate={this.onCreate} />
                    </div>
                    
                    <ErrorBoundary>
                        <SneakerList sneakers={this.state.sneakers}
                            listName="Main"
                            links={this.state.links}
                            attributes={this.state.attributes}
                            pageSize={this.state.pageSize}
                            onNavigate={this.onNavigate}
                            onDelete={this.onDelete}
                            onUpdate={this.onUpdate}
                            updatePageSize={this.updatePageSize} />
                    </ErrorBoundary>
                </div>

            </div>
        );
    }
}

export default Landing