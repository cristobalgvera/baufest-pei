import React from 'react';
import './App.css';
import Menu from './components/Menu';
import {BrowserRouter} from 'react-router-dom';
import {Switch, Route} from 'react-router';
import Jugador from './containers/Jugador/Jugador';
import Partido from "./containers/Partido/Partido";
import Tablero from "./containers/Tablero/Tablero";

function App() {

  return (
    <BrowserRouter>
      <div className="App">
        <Menu />
        <Switch>
            <Route exact path={"/jugadores"} component={Jugador}/>
            <Route exact path={"/partidos"} component={Partido}/>
            <Route exact path={"/tablero/:id"} component={Tablero}/>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;