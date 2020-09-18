import React, {Fragment} from "react";
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import Menu from "./Menu/Menu";
import Home from "../containers/Home";
import About from "../containers/About";
import Contact from "../containers/Contact";
import Blog from "../containers/Blog";
import NotFound from "./not-found/NotFound";
import Post from "../containers/Post";

class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <Menu/>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/about" component={About}/>
                    <Route exact path="/contact" component={Contact}/>
                    <Route exact path="/blog" component={Blog}/>
                    <Route exact path="/post/:id" component={Post}/>
                    <Route component={NotFound}/>
                </Switch>
            </BrowserRouter>
        )
    }
}

export default App;