import React, { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap';
import JugadorList from '../../components/Jugador/JugadorList'
import JugadorModal from '../../components/Jugador/JugadorModal';

//Mientras no haya conexion con el back se usaran estos datos
var datosJugadores = [
  {id: 1, nombre:'Federer', puntos:1000},
  {id: 2, nombre:'Nadal'  , puntos:1200}
]

const Jugador = props => {

  const [jugadores,setJugadores] = useState([]);

  const initialJugadorData = { 
    nombre: '',
    puntos: 0
  }

  const [newJugadorData,setNewJugadorData] = useState(initialJugadorData);

  const [showModal,setShowModal] = useState(false);
  const [isEdit,setIsEdit] = useState(false);
  const [validateForm, setValidateForm] = useState(false);
  const [errorMsg,setErrorMsg] = useState('');

  useEffect(() => {
    getJugadores();
  },[]);

  const getJugadores = () => {
    setJugadores(datosJugadores);
  }

  const borrarJugador = (id) => {
    if (window.confirm("Estas seguro?")) {
      datosJugadores = jugadores.filter(x => x.id != id);
      setJugadores(datosJugadores);
    }
  }

  const agregarJugador = () => {
    //Copia los valores de 'newJugadorData' en un nuevo objeto 'model'
    let model = Object.assign({}, newJugadorData);
    model.id = datosJugadores.length +1;
    datosJugadores.push(model);
    setJugadores(datosJugadores);
    resetModal();
  }

  const editarJugador = (id) => {
    //Copia los valores de 'newJugadorData' en un nuevo objeto 'model'
    let model = Object.assign({}, newJugadorData);
    model.id = id;
    datosJugadores = jugadores.filter(x => x.id != id);
    datosJugadores.push(model);
    setJugadores(datosJugadores);
    resetModal();
  }

  const handleFormChange = (tipo,value) => {
    if(value === '')
      setValidateForm(true);

    setNewJugadorData({...newJugadorData, [tipo]:value});
  }  

  const handleFormSubmit = (form, isEdit) => {
    setValidateForm(true);

    if(form.checkValidity())
      isEdit ? editarJugador(newJugadorData.id) : agregarJugador();
  };
 
  const resetModal = () =>{
    setShowModal(false);
    setIsEdit(false);
    setNewJugadorData(initialJugadorData);
    setValidateForm(false);
    setErrorMsg('');
  }

  const handleOpenModal = (editar = false, jugadorToEdit = null) =>{
    if(editar)
    {
      setIsEdit(true);
      setNewJugadorData(jugadorToEdit);
    }
    setShowModal(true);
  }

  const handleCloseModal = () =>{
    resetModal();
  }

  return (
    <div className="container mt-4">
      <h1>Jugadores</h1>
      <Button variant="info mb-3" onClick={()=> handleOpenModal()}> Agregar Jugador </Button> 
      <JugadorModal
        show={showModal}
        handleClose={handleCloseModal}
        handleChange={handleFormChange}
        handleSubmit={handleFormSubmit}
        isEdit={isEdit}
        validate={validateForm}
        errorMsg={errorMsg}
        jugador={newJugadorData}
      />
      <JugadorList
        jugadores={jugadores}
        borrarJugador={borrarJugador}
        editarJugador={handleOpenModal}
      />
    </div>
  );
  
}

export default Jugador;