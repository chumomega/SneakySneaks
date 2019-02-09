import React, { Component } from 'react';
import Sneaker from '../Sneaker/Sneaker'

class SneakerList extends Component{
	render() {
		const sneakers = this.props.sneakers.map(sneaker =>
			<Sneaker key={sneaker.product_number} name={sneaker.name} brand={sneaker.brand} size={sneaker.size}  />
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