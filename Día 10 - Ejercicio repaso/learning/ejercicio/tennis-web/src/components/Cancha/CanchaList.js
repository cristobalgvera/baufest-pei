import React from "react";
import {Table, Button} from 'react-bootstrap';

const CanchaList = props => {
    const {borrarCancha, canchas, editarCancha, showTodayMatches} = props;

    const listaCanchas = canchas.map(cancha => {
        const {id, nombre, direccion} = cancha;
        return (
            <tr key={id}>
                <td>{id}</td>
                <td>{nombre}</td>
                <td>{direccion}</td>
                <td>
                    <Button variant={"info"} className={"mr-2"} disabled={cancha.partidos.length === 0}
                            onClick={() => showTodayMatches(cancha)}>Ver</Button>
                </td>
                <td>
                    <Button variant="success" className="mr-2"
                            onClick={() => editarCancha(true, cancha)}> Editar </Button>
                    <Button variant="danger" onClick={() => borrarCancha(id)}>Eliminar</Button>
                </td>
            </tr>
        );
    });

    return (
        <Table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Partidos del día</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            {listaCanchas}
            </tbody>
        </Table>
    );
}

export default CanchaList;