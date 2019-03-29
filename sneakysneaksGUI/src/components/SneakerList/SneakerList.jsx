import React, { Component } from 'react';
import Sneaker from '../Sneaker/Sneaker'
import ReactDOM from 'react-dom'

class SneakerList extends Component {
    constructor(props) {
        super(props)

        this.handleNavFirst = this.handleNavFirst.bind(this);
		this.handleNavPrev = this.handleNavPrev.bind(this);
		this.handleNavNext = this.handleNavNext.bind(this);
		this.handleNavLast = this.handleNavLast.bind(this);
        this.handleInput = this.handleInput.bind(this);
        
        this.state = {
            sneakers: [], 
            attributes: [], 
            pageSize: 3, 
            links: {
                first: "null"
            }
        }
    }
    handleNavFirst(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }

    handleInput(event) {
        event.preventDefault();
        const pageSize = event.target.value;
        if (/^[0-9]+$/.test(pageSize)) {
            this.props.updatePageSize(pageSize);
        } else {
            ReactDOM.findDOMNode(this.refs.pageSize).value = pageSize.substring(0, pageSize.length - 1);
        }
    }

    render() {
        const sneakers = this.props.sneakers.map(sneaker =>
            <Sneaker key={sneaker._links.self.href} key1={sneaker._links.self.href} name={sneaker.name} brand={sneaker.brand} size={sneaker.size} sneaker={sneaker} onDelete={this.props.onDelete}/>
        );
        const listName = this.props.listName;

        const navLinks = [];
        if ("first" in this.props.links) {
            navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
        }
        if ("prev" in this.props.links) {
            navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
        }
        if ("next" in this.props.links) {
            navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
        }
        if ("last" in this.props.links) {
            navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
        }

        return (
            <div>
                <h1>{listName} Sneakers</h1>
                <input ref="pageSize" value={this.state.value} defaultValue={this.props.pageSize} onInput={this.handleInput}/>
			

                {sneakers}
                <div>
                    {navLinks}
                </div>

            </div>
        )
    }
}


export default SneakerList