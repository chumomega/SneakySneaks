import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';
import CreateSneaker from '../CreateSneaker/CreateSneaker';
import client from '../../clientAndApi/client';
import "./Landing.css";
import follow from '../../clientAndAPi/follow'; // function to hop multiple links by "rel"

const root = '/api';

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
                return sneakerCollection;
            });
        }).done(sneakerCollection => {
            this.setState({
                sneakers: sneakerCollection.entity._embedded.sneakers,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: sneakerCollection.entity._links
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
        }).then(() => {
                return follow(client, root, [
                    { rel: 'sneakers', params: { 'size': this.state.pageSize } }
                ]);
            }).done(response => {
            if (typeof response.entity._links.last !== "undefined") {
                this.onNavigate(response.entity._links.last.href);
            } else {
                this.onNavigate(response.entity._links.self.href);
            }
        });
    }

    onNavigate(navUri) {
        client({ method: 'GET', path: navUri }).done(sneakerCollection => {
            this.setState({
                sneakers: sneakerCollection.entity._embedded.sneakers,
                attributes: this.state.attributes,
                pageSize: this.state.pageSize,
                links: sneakerCollection.entity._links
            });
        });
    }

    onDelete(sneaker) {
        client({ method: 'DELETE', path: sneaker._links.self.href }).done(() => {
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
                            pageSize={this.state.pageSize}
                            onNavigate={this.onNavigate}
                            onDelete={this.onDelete}
                            updatePageSize={this.updatePageSize} />
                    </ErrorBoundary>
                </div>

            </div>
        );
    }
}

export default Landing