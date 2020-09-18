import React from "react";
import {Table, Button} from "react-bootstrap";

const PartidoList = (props) => {
    const {partidos} = props;

    const listaPartidos = partidos.map(partido => {
        const {id, fechaComienzo, estado, jugadorLocal, jugadorVisitante} = partido;
        return (
            <tr key={id}>
                <td>{id}</td>
                <td>{fechaComienzo}</td>
                <td>{estado}</td>
                <td>{jugadorLocal.nombre}</td>
                <td>{jugadorVisitante.nombrjugadorLocale}</td>
                <td>
                    <Button variant={"danger"}>Eliminar</Button>
                </td>
            </tr>
        )
    });

    return (
        <Table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Fecha comienzo</th>
                <th>Estado</th>
                <th>Jugador local</th>
                <th>Jugador visita</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            {listaPartidos}
            </tbody>
        </Table>
    );
}

export default PartidoList;