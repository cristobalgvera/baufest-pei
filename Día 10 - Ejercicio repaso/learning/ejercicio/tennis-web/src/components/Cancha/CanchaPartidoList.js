import React from 'react'
import {Table} from 'react-bootstrap';

const CanchaPartidoList = props => {

    const {partidos} = props;

    const listaPartidos = partidos.map((partido) => {

        const {id, jugadorLocal, jugadorVisitante, estado} = partido;
        return (
            <tr key={id}>
                <td>{id}</td>
                <td>{jugadorLocal.nombre}</td>
                <td>{jugadorVisitante.nombre}</td>
                <td>{estado}</td>
            </tr>
        )
    });

    return (
        <Table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Local</th>
                <th>Visitante</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tbody>
            {listaPartidos}
            </tbody>
        </Table>
    );

}

export default CanchaPartidoList;