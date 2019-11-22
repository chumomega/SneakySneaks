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

const stompClient = require('../../clientAndApi/websocket-listener');

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
		this.refreshAndGoToLastPage = this.refreshAndGoToLastPage.bind(this);
	}
	//put this method in a seperate file for modularity
	follow(api, rootPath, relArray) {
		const root = api({
			method: 'GET',
			path: rootPath
		});

		return relArray.reduce(function(root, arrayItem) {
			const rel = typeof arrayItem === 'string' ? arrayItem : arrayItem.rel;
			return traverseNext(root, rel, arrayItem);
		}, root);

		function traverseNext(root, rel, arrayItem) {
			return root.then(function(response) {
				if (hasEmbeddedRel(response.entity, rel)) {
					return response.entity._embedded[rel];
				}

				if (!response.entity._links) {
					return [];
				}

				if (typeof arrayItem === 'string') {
					return api({
						method: 'GET',
						path: response.entity._links[rel].href
					});
				} else {
					return api({
						method: 'GET',
						path: response.entity._links[rel].href,
						params: arrayItem.params
					});
				}
			});
		}

		function hasEmbeddedRel(entity, rel) {
			return entity._embedded && entity._embedded.hasOwnProperty(rel);
		}
	};


	componentDidMount() {
		this.loadFromServer(this.state.pageSize)
		stompClient.register([
			{ route: '/topic/newSneaker', callback: this.refreshAndGoToLastPage },
			{ route: '/topic/updateSneaker', callback: this.refreshCurrentPage },
			{ route: '/topic/deleteSneaker', callback: this.refreshCurrentPage }
		]);
	}

	refreshCurrentPage(message) {
		follow(client, root, [{
			rel: 'sneakers',
			params: {
				size: this.state.pageSize,
				page: this.state.page.number
			}
		}]).then(sneakerCollection => {
			this.links = sneakerCollection.entity._links;
			this.page = sneakerCollection.entity.page;

			return sneakerCollection.entity._embedded.sneakers.map(sneaker => {
				return client({
					method: 'GET',
					path: sneaker._links.self.href
				})
			});
		}).then(sneakerPromises => {
			return when.all(sneakerPromises);
		}).then(sneakers => {
			this.setState({
				page: this.page,
				sneakers: sneakers,
				attributes: Object.keys(this.schema.properties),
				pageSize: this.state.pageSize,
				links: this.links
			});
		});
	}

	loadFromServer(pageSize) {
		this.follow(client, root, [
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
		this.follow(client, root, ['sneakers']).done(response => {
			client({
				method: 'POST',
				path: response.entity._links.self.href,
				entity: newSneaker,
				headers: { 'Content-Type': 'application/json' }
			})
		})



		// this.follow(client, root, ['sneakers']).then(sneakerCollection => {
		//     return client({
		//         method: 'POST',
		//         path: sneakerCollection.entity._links.self.href,
		//         entity: newSneaker,
		//         headers: { 'Content-Type': 'application/json' }
		//     })
		// }).then(response => {
		//     return this.follow(client, root, [
		//         { rel: 'sneakers', params: { 'size': this.state.pageSize } }]);
		// }).done(response => {
		//     if (typeof response.entity._links.last !== "undefined") {
		//         this.onNavigate(response.entity._links.last.href);
		//     } else {
		//         this.onNavigate(response.entity._links.self.href);
		//     }
		// });
	}

	// websocket-handlers
	refreshAndGoToLastPage(message) {
		this.follow(client, root, [{
			rel: 'sneakers',
			params: { size: this.state.pageSize }
		}]).done(response => {
			if (response.entity._links.last !== undefined) {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		})
	}


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
		client({ method: 'DELETE', path: sneaker.entity._links.self.href })
			.done(response => {
				//websocket handles this
				/*this.loadFromServer(this.state.pageSize);*/
			}, response => {
					if (response.status.code === 403) {
				alert('ACCESS DENIED: You are not authorized to delete ' +
						sneaker.entity._links.self.href);
				}
			});

	}

	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}

	onUpdate(sneaker, updatedSneaker) {
		if (sneaker.entity.user.firstName === this.state.loggedInUser) {
			updatedSneaker["user"] = sneaker.entity.user;
			client({
				method: 'PUT',
				path: sneaker.entity._links.self.href,
				entity: updatedSneaker,
				headers: {
					'Content-Type': 'application/json',
					'If-Match': sneaker.headers.Etag
				}
			}).done(response => {
				/* Let the websocket handler update the state */
				/*this.loadFromServer(this.state.pageSize);*/
			}, response => {
				if (response.status.code === 403) {
					alert('ACCESS DENIED: You are not authorized to update ' + sneaker.entity._links.self.href);
				}
				if (response.status.code === 412) {
					alert('DENIED: Unable to update ' +
						sneaker.entity._links.self.href + '. Your copy is stale.');
				}
			});
		}
		else {
			alert("You are not authorized to update");
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
							attributes={this.state.attributes}
							listName="Main"
							links={this.state.links}
							pageSize={this.state.pageSize}
							onNavigate={this.onNavigate}
							onDelete={this.onDelete}
							updatePageSize={this.updatePageSize}
							onUpdate={this.onUpdate} />
					</ErrorBoundary>
				</div>

			</div>
		);
	}
}

export default Landing