import React, {useRef} from "react";
import {Form, Button, Modal} from 'react-bootstrap'
import FormRowInput from "../FormRowInput/FormRowInput";

const PartidoModal = (props) => {
    const formRef = useRef(null);
    const {show, handleClose, handleChange, handleSubmit, isEdit, validate, errorMsg, partido} = props;

    return (
        <Modal
            show={show}
            onHide={handleClose}
            backdrop="static" //Si se hace click fuera del modal este no se cerrara
            keyboard={false}  //Si se presiona la tecla ESC tampoco se cierra
        >
            <Modal.Header closeButton>
                <Modal.Title>{isEdit ? 'Editar ' : 'Agregar '} partido</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form className="Form" noValidate validated={validate} ref={formRef}>

                    <FormRowInput
                        label={"Fecha comienzo"}
                        type={"dateTime"}
                        placeholder={"Ingrese una fecha"}
                        value={partido.fechaComienzo}
                        handleChange={handleChange}
                        property={"fecha"}
                    />
                    <FormRowInput
                        label={"Estado"}
                        type={"text"}
                        placeholder={"Ingrese el estado del partido"}
                        value={partido.estado}
                        handleChange={handleChange}
                        property={"estado"}
                    />
                    <FormRowInput
                        label={"Jugador local"}
                        type={"select"}
                        placeholder={"Ingrese jugador local"}
                        value={partido.jugadorLocal.nombre}
                        handleChange={handleChange}
                        property={"jugadorLocal"}
                    />
                    <FormRowInput
                        label={"Jugador visita"}
                        type={"select"}
                        placeholder={"Ingrese jugador visita"}
                        value={partido.jugadorVisitante.nombre}
                        handleChange={handleChange}
                        property={"jugadorVisitante"}
                    />

                    {errorMsg !== '' &&
                    <Form.Group className="alert-danger">
                        {errorMsg}
                    </Form.Group>
                    }

                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="success"
                        onClick={() => handleSubmit(formRef.current, isEdit)}>{isEdit ? 'Editar' : 'Agregar'}</Button>
                <Button variant="danger" className="mr-2" onClick={() => handleClose()}>Cancelar</Button>
            </Modal.Footer>
        </Modal>
    );

}

export default PartidoModal;