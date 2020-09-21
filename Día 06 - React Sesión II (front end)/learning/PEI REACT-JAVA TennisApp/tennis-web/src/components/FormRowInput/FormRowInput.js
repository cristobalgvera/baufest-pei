import React from "react";

var datosJugadores = [
    {id: 1, nombre: 'Federer', puntos: 1000},
    {id: 2, nombre: 'Nadal', puntos: 1200}
]

const FormRowInput = (props) => {
    const {handleChange, label, placeholder, property, type, value} = props;

    const inputType = () => {
        if (type != "select") {
            return (
                <input
                    type={type}
                    value={value}
                    placeholder={placeholder}
                    property={property}
                    onChange={(e) => handleChange(property, e.target.value)}
                />
            )
        } else {
            const options = datosJugadores.map(jugador => <option key={jugador.id} value={jugador.id}>{jugador.nombre}</option>);

            return (
                <select onChange={(e) => handleChange(property, e.target.value)}>
                    <option disabled={true} value={"Choose one"} selected={true}>Elige un jugador</option>
                    {options}
                </select>
            )
        }
    }

    return (
        <>
            <label>{label}</label>
            {inputType()}
            <br/>
        </>
    );
}

export default FormRowInput;