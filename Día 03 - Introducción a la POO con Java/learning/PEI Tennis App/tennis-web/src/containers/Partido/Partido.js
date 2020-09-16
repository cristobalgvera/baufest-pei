import React, { Component } from 'react';

//Mientras no haya conexion con el back se usaran estos datos
let datosPartidos = [
  {
    id:1,
    fechaComienzo: '01/09/2020 16:00',
    estado: 'NO_INICIADO',
    jugadorLocal: {id: 1, nombre:'Federer', puntos:1000},
    jugadorVisitante: {id: 2, nombre:'Nadal'  , puntos:1200},
  }
]
let datosJugadores = [
  {id: 1, nombre:'Federer', puntos:1000},
  {id: 2, nombre:'Nadal'  , puntos:1200}
]


class Partido extends Component{

}

export default Partido;