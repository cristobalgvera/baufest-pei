import React, {useState, useEffect} from "react";
import PartidoList from "../../components/Partido/PartidoList";
import {Button} from 'react-bootstrap';
import PartidoModal from "../../components/Partido/PartidoModal";

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

const Partido = (props) => {
    const [partidos, setPartido] = useState(datosPartidos);

    useEffect(() => getPartidos(), []);

    const initialPartidoData = {
        id: partidos.reduce(((previousValue, currentValue) => previousValue > currentValue), []),
        fechaComienzo: Date.now(),
        estado: 'NO_INICIADO',
        jugadorLocal: '',
        jugadorVisitante: ''
    }

    const [newPartidoData, setNewPartidoData] = useState(initialPartidoData);
    const [showModal, setShowModal] = useState(false);
    const [isEdit, setIsEdit] = useState(false);
    const [validateForm, setValidateForm] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    const getPartidos = () => setPartido(datosPartidos);

    const borrarPartido = (id) => {
        if (window.confirm("¿Estás seguro?")) {
            datosPartidos = partidos.filter(partido => partido.id !== id);
            getPartidos();
        }
    };

    const agregarPartido = () => {
        //Copia los valores de 'newJugadorData' en un nuevo objeto 'model'
        let model = Object.assign({}, newPartidoData);
        model.id = datosPartidos.length + 1;
        model.jugadorLocal = datosJugadores.find(jugador => jugador.id == model.jugadorLocal);
        model.jugadorVisitante = datosJugadores.find(jugador => jugador.id == model.jugadorVisitante);
        datosPartidos.push(model);
        console.log(datosPartidos);
        setPartido(datosPartidos);
        resetModal();
    }

    const editarPartido = (id) => {
        let model = Object.assign({}, newPartidoData);
        model.id = id;
        datosPartidos = partidos.filter(p => p.id !== id);
        model.jugadorLocal = datosJugadores.find(jugador => jugador.id == model.jugadorLocal);
        model.jugadorVisitante = datosJugadores.find(jugador => jugador.id == model.jugadorVisitante);
        datosPartidos.push(model);
        getPartidos();
        resetModal();
    }

    const buscarPartidoPorId = (id) => (partidos.find(p => p.id === id));

    const handleFormChange = (tipo, value) => {
        if (value === '') {
            setValidateForm(true);
        }

        console.log(tipo, value);

        setNewPartidoData({...newPartidoData, [tipo]: value});
    }

    const handleFormSubmit = (form, isEdit) => {
        setValidateForm(true);

        if (form.checkValidity())
            isEdit ? editarPartido(newPartidoData.id) : agregarPartido();
    };

    const resetModal = () => {
        setShowModal(false);
        setIsEdit(false);
        setNewPartidoData(initialPartidoData);
        setValidateForm(false);
        setErrorMsg('');
    }

    const handleOpenModal = (editar = false, partidoToEdit = null) => {
        if (editar) {
            setIsEdit(true);
            setNewPartidoData(partidoToEdit);
        }
        setShowModal(true);
    }

    const handleCloseModal = () => {
        resetModal();
    }

    return (
        <div className="container mt-4">
            <h1>Partidos</h1>
            <Button variant="info mb-3" onClick={() => handleOpenModal()}>Agregar Partido</Button>
            <PartidoModal
                show={showModal}
                handleClose={handleCloseModal}
                handleChange={handleFormChange}
                handleSubmit={handleFormSubmit}
                isEdit={isEdit}
                validate={validateForm}
                errorMsg={errorMsg}
                partido={newPartidoData}
            />
            <PartidoList
                partidos={partidos}
                borrarPartido={borrarPartido}
                editarPartido={handleOpenModal}
                buscarPartidoPorId={buscarPartidoPorId}
            />
        </div>
    );

}

export default Partido;