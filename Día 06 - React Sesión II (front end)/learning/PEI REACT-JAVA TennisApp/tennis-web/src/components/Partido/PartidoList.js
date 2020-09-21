import React from 'react'
import { Table, Button } from 'react-bootstrap';

const PartidoList = props => {

  const {partidos, borrarPartido } = props;

  const listaPartidos = partidos.map((partido) => {

    const {id,fechaComienzo,jugadorLocal,jugadorVisitante,estado} = partido;
    return (
      <tr key={id}>
        <td>{id}</td>
        <td>{fechaComienzo}</td>
        <td>{jugadorLocal.nombre}</td>
        <td>{jugadorVisitante.nombre}</td>
        <td>{estado}</td>
        <td className="text-right">
          <Button variant="danger" onClick={()=>borrarPartido(id)}>Eliminar</Button>
        </td>
      </tr>
    )
  });

  return (
    <Table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Fecha Comienzo</th>
          <th>Local</th>
          <th>Visitante</th>
          <th>Estado</th>
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