import React from 'react'
import {Route, Switch} from 'react-router-dom';
import Jugador from '../containers/Jugador/Jugador';
import Partido from '../containers/Partido/Partido';
import PartidoTablero from '../components/PartidoTablero/PartidoTablero'
import Cancha from "../containers/Cancha/Cancha";

export default function Routes() {

    return (
        <Switch>
            <Route path="/jugadores" component={Jugador} exact/>
            <Route path="/partidos" component={Partido} exact/>
            <Route path="/partidos/jugar-partido" component={PartidoTablero} exact/>
            <Route path={"/canchas"} component={Cancha} exact/>
        </Switch>
    )
}
