import React, {useState} from "react";
import {Table} from "react-bootstrap";
import Marcador from "../../components/Marcador/Marcador";


const Tablero = (props) => {
    // No ocupado
    const [partido, setPartido] = useState(props.location.state.partido);

    // Simplificar envío de info
    const marcador = [
        {
            nombre: partido.jugadorLocal.nombre,
            puntos: 0,
            games: 0
        },
        {
            nombre: partido.jugadorVisitante.nombre,
            puntos: 0,
            games: 0
        }
    ]

    return (
        <Table>
            <thead>
            <tr>
                <th>Jugador</th>
                <th>Puntos</th>
                <th>Games</th>
                <th>Acción</th>
            </tr>
            </thead>
            <tbody>
            <Marcador
                marcador={marcador[0]}
            />
            <Marcador
                marcador={marcador[1]}
            />
            </tbody>
        </Table>
    )
};

export default Tablero;