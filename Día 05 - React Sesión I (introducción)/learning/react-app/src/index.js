import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
import './assets/css/app.css';

const greeting = <p>Hello World from React!</p>;

function myFunction() {
    return <p>Functional component</p>;
}

const myFunctionVariable = () => {
    return <p>Functional component variable</p>;
};

const myArrowFunction = () => <p>Arrow func</p>;

ReactDOM.render(<App/>, document.getElementById('app'));
