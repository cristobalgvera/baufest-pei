import React from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from "react-router-dom";

const Menu = () => {
    return (
        <Navbar collapseOnSelect expand="md" bg="dark" variant="dark">
            <Navbar.Brand as={Link} to={"/"}>Baufest blog</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className={"mr-auto"}>
                    <Nav.Link as={Link} to={"/"}>Home</Nav.Link>
                    <Nav.Link as={Link} to={"/about"}>About</Nav.Link>
                    <Nav.Link as={Link} to={"/contact"}>Contact</Nav.Link>
                    <Nav.Link as={Link} to={"/blog"}>Blog</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
};

export default Menu;