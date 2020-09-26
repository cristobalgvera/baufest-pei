import React, {useRef} from 'react';
import {Modal} from 'react-bootstrap'
import CanchaPartidoList from "./CanchaPartidoList";

const CanchaPartidosModal = props => {
    const {cancha, show, handleClose} = props;
    const {partidos, nombre} = cancha;

    const title = nombre + ": " + partidos.length + (partidos.length !== 1 ? " partidos" : " partido");

    return (
        <Modal
            show={show}
            onHide={handleClose}
        >
            <Modal.Header closeButton>
                <Modal.Title>{nombre}: {partidos.length} partido</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <CanchaPartidoList
                    partidos={partidos}
                />
            </Modal.Body>
            <Modal.Footer>

            </Modal.Footer>
        </Modal>
    );
}

export default CanchaPartidosModal;