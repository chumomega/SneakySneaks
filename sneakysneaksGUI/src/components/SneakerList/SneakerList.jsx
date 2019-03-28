import React, { Component } from 'react';
import Sneaker from '../Sneaker/Sneaker'

class SneakerList extends Component{
	render() {
		const sneakers = this.props.sneakers.map(sneaker =>
			<Sneaker key={sneaker._links.self.href} key1={sneaker._links.self.href} name={sneaker.name} brand={sneaker.brand} size={sneaker.size}  />
    );
    const listName = this.props.listName;

		return (
			<div>
        <h1>{listName} Sneakers</h1>

        {sneakers}

      </div>
		)
	}
}


export default SneakerList