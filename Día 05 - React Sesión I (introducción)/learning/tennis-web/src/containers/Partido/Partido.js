import React, {Component} from 'react';
import PartidoList from "../../components/Partido/PartidoList";

//Mientras no haya conexion con el back se usaran estos datos
let datosPartidos = [
    {
        id: 1,
        fechaComienzo: '01/09/2020 16:00',
        estado: 'NO_INICIADO',
        jugadorLocal: {id: 1, nombre: 'Federer', puntos: 1000},
        jugadorVisitante: {id: 2, nombre: 'Nadal', puntos: 1200},
    }
]
let datosJugadores = [
    {id: 1, nombre: 'Federer', puntos: 1000},
    {id: 2, nombre: 'Nadal', puntos: 1200}
]


class Partido extends Component {
    constructor(props) {
        super(props);
        this.state = {
            partidos: []
        };
    }

    componentDidMount() {
        this.getPartidos();
    }

    getPartidos() {
        this.setState({partidos: datosPartidos});
    }

    render() {
        return (
            <div className={"container mt-4"}>
                <PartidoList
                    partidos={this.state.partidos}
                />
            </div>
        );
    }
}

export default Partido;