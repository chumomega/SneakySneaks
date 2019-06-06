import React, { Component } from 'react';
import Sneaker from '../Sneaker/Sneaker'
import ReactDOM from 'react-dom'
import "./SneakerList.css"

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
        const pageInfo = this.props.page.hasOwnProperty("number") ?
            <h3>Sneakers - Page {this.props.page.number + 1} of {this.props.page.totalPages}</h3> : null;

        const sneakers = this.props.sneakers.map(sneaker =>
            <Sneaker 
                key={sneaker.entity._links.self.href}
                sneaker={sneaker} 
                attributes={this.props.attributes}
                onDelete={this.props.onDelete} 
                onUpdate={this.props.onUpdate}
                />
        );
        const listName = this.props.listName;

        const navLinks = [];
        if ("first" in this.props.links) {
            navLinks.push(<button key="first" onClick={this.handleNavFirst} type="button" className="btn btn-info btn-secondary">First</button>);
        }
        if ("prev" in this.props.links) {
            navLinks.push(<button key="prev" onClick={this.handleNavPrev} type="button" className="btn btn-info btn-secondary">Prev</button>);
        }
        if ("next" in this.props.links) {
            navLinks.push(<button key="next" onClick={this.handleNavNext} type="button" className="btn btn-info btn-secondary">Next</button>);
        }
        if ("last" in this.props.links) {
            navLinks.push(<button key="last" onClick={this.handleNavLast} type="button" className="btn btn-info btn-secondary">Last</button>);
        }

        return (
            <div>
                {pageInfo}
                <h1 className="page-header">{listName} Sneakers</h1>
                <div className="input-group mb-3">
                    <div className="input-group-prepend">
                        <span className="input-group-text" id="basic-addon1"># items</span>
                    </div>
                        <input ref="pageSize" className="form-control" value={this.state.value} defaultValue={this.props.pageSize} onInput={this.handleInput} />
                    </div>
                    {sneakers}
                    <div className="btn-group-wrap">
                        <div className="btn-group buttons" role="group" aria-label="Basic example">
                            {navLinks}
                        </div>
                    </div>


                </div>
                )
            }
        }
        
        
export default SneakerList