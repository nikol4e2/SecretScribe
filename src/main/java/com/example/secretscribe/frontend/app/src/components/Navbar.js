import React from 'react';
import {Link} from "react-router-dom";
import './navbar.css';
const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark ">
            <Link to="/" className="navbar-brand ms-3" >Secret Scribe</Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse " id="navbarNav">
                <ul className="navbar-nav ms-auto">
                    <li className="nav-item">
                        <Link to="/" className="nav-link" >New Confessions</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/popular" className="nav-link" >Popular Confessions</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/add" className="nav-link">Add Confession</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/login" className="nav-link" >Login as Admin</Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default Navbar;