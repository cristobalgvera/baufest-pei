import React from 'react';
import {BrowserRouter, Route} from 'react-router-dom';
import {Switch} from 'react-router-dom';
import './App.css';
import Menu from './components/Menu';
import Jugador from './containers/Jugador/Jugador';
import Partido from "./containers/Partido/Partido";

function App() {

    return (
        <div className="App">
            <BrowserRouter>
                <Menu/>
                <Switch>
                    <Route exact path="/jugadores" component={Jugador}/>
                    <Route exact path={"/partidos"} component={Partido}/>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;