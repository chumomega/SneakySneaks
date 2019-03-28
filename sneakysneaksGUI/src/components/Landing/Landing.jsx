import React, { Component } from 'react';
import SneakerList from '../SneakerList/SneakerList';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';
import client from '../../clientAndApi/client';

import follow from '../../clientAndAPi/follow'; // function to hop multiple links by "rel"

const root = '/api';

class Landing extends Component {
  constructor(props){
    super(props)
    this.state = {
      sneakers: []
    }
  }
  

  componentDidMount() {
      this.loadFromServer(this.state.pageSize)
  }

  loadFromServer(pageSize){
    follow(client, root, [
		{rel: 'sneakers', params: {size: pageSize}}]
	).then(sneakerCollection => {
        return client({
			method: 'GET',
			path: sneakerCollection.entity._links.profile.href,
			headers: {'Accept': 'application/schema+json'}
		}).then(schema => {
			this.schema = schema.entity;
			return sneakerCollection;
		});
	}).done(sneakerCollection => {
		this.setState({
			sneakers: sneakerCollection.entity._embedded.sneakers,
			attributes: Object.keys(this.schema.properties),
			pageSize: pageSize,
			links: sneakerCollection.entity._links});
    });
  }

  render() {
    return (
      <div className="">
        <h1>This is the landing page. Welcome Dana</h1>
        <ErrorBoundary>
          <SneakerList sneakers={this.state.sneakers} listName="Main"/>
        </ErrorBoundary>
        
      </div>
    );
  }
}

export default Landing