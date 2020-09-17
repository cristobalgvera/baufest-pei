import React, { Component } from 'react';
import JugadorList from '../../components/Jugador/JugadorList';

//Mientras no haya conexion con el back se usaran estos datos
let datosJugadores = [
  {id: 1, nombre:'Federer', puntos:1000},
  {id: 2, nombre:'Nadal'  , puntos:1200}
]

class Jugador extends Component {

  constructor(props) {
    super(props);

    this.state = {
      jugadores : []
    };

    this.borrarJugador = this.borrarJugador.bind(this);
  }

  componentDidMount(){
    this.getJugadores();
  }

  getJugadores() {
    this.setState({ jugadores : datosJugadores });
  }

  borrarJugador(id) {
    if (window.confirm("Estas seguro?")) {
      datosJugadores = this.state.jugadores.filter(x => x.id !== id);
      this.setState({ jugadores : datosJugadores });
    }
  }

  render(){
    return (
      <div className="container mt-4">
        <h1>Jugadores</h1>
        <JugadorList
          jugadores={this.state.jugadores}
          borrarJugador={this.borrarJugador}
        />
      </div>
    );
  }
  
}

export default Jugador;