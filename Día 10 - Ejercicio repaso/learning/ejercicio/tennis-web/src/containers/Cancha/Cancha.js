import React, {useEffect, useState} from "react";
import API from "../../services/API";
import {Button} from "react-bootstrap";
import CanchaModal from "../../components/Cancha/CanchaModal";
import JugadorModal from "../../components/Jugador/JugadorModal";
import CanchaList from "../../components/Cancha/CanchaList";
import CanchaPartidosModal from "../../components/Cancha/CanchaPartidosModal";

const Cancha = props => {
    const [canchas, setCanchas] = useState([]);

    const initialCanchaData = {
        id: 0,
        nombre: '',
        direccion: '',
        partidos: []
    }

    const [newCanchaData, setNewCanchaData] = useState(initialCanchaData);

    const [showModal, setShowModal] = useState(false);
    const [isEdit, setIsEdit] = useState(false);
    const [validateForm, setValidateForm] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    const [showMatchesModal, setShowMatchesModal] = useState(false);

    useEffect(() => {
        getCanchas();
    }, []);

    const getCanchas = async () => {
        try {
            let response = await API.get('/canchas');
            setCanchas(await response);
        } catch (e) {
            console.log(e);
        }
    }

    const borrarCancha = async (id) => {
        try {
            await API.remove(`/canchas/${id}`);
            await getCanchas();
        } catch (e) {
            console.log(e);
        }
    }

    const agregarCancha = async () => {
        try {
            console.log(newCanchaData);
            await API.save('/canchas', newCanchaData);
            resetModal();
            await getCanchas();
        } catch (e) {
            setErrorMsg(JSON.stringify(e));
        }
    }

    const editarCancha = async (id) => {
        try {
            await API.update(`/canchas/${id}`, newCanchaData);
            resetModal();
            await getCanchas();
        } catch (e) {
            setErrorMsg(JSON.stringify(e));
        }
    }

    const handleFormChange = (tipo, value) => {
        if (value === '')
            setValidateForm(true);

        setNewCanchaData({...newCanchaData, [tipo]: value});
    }

    const handleFormSubmit = (form, isEdit) => {
        setValidateForm(true);

        if (form.checkValidity())
            isEdit ? editarCancha(newCanchaData.id) : agregarCancha();
    }

    const handleOpenModal = (editar = false, canchaToEdit = null) => {
        if (editar) {
            setIsEdit(true);
            setNewCanchaData(canchaToEdit);
        }
        setShowModal(true);
    }

    const handleOpenMatchesModal = (canchaToShow) => {
        console.log(canchaToShow)
        setNewCanchaData(canchaToShow)
        setShowMatchesModal(true);
    }

    const handleCloseModal = () => {
        resetModal();
    }

    const resetModal = () => {
        setShowModal(false);
        setShowMatchesModal(false);
        setIsEdit(false);
        setNewCanchaData(initialCanchaData);
        setValidateForm(false);
        setErrorMsg('');
    }

    return (
        <div className={"container mt-4"}>
            <h1>Canchas</h1>
            <Button variant={"info mb-3"} onClick={() => handleOpenModal()}>
                Agregar cancha
            </Button>
            <CanchaModal
                show={showModal}
                handleClose={handleCloseModal}
                handleChange={handleFormChange}
                handleSubmit={handleFormSubmit}
                isEdit={isEdit}
                validate={validateForm}
                errorMsg={errorMsg}
                cancha={newCanchaData}
            />
            <CanchaPartidosModal
                show={showMatchesModal}
                handleClose={handleCloseModal}
                cancha={newCanchaData}
            />
            <CanchaList
                canchas={canchas}
                borrarCancha={borrarCancha}
                editarCancha={handleOpenModal}
                showTodayMatches={handleOpenMatchesModal}
            />
        </div>
    );
}

export default Cancha;