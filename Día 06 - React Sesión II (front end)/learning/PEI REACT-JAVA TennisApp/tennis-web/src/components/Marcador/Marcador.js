import React, {useState} from "react";
import {Button} from 'react-bootstrap';

const Marcador = (props) => {
    const {marcador} = props;
    const [nombre, setNombre] = useState(marcador.nombre);
    const [puntos, setPuntos] = useState(marcador.puntos);
    const [games, setGames] = useState(marcador.games);

    const marcar = () => {
        setPuntos(puntos + 15);
        if (puntos == 45) {
            game();
            resetPuntos();
        }
    }

    const game = () => {
        setGames(games + 1);
    }

    const resetPuntos = () => {
        setPuntos(0);
    }

    return (
        <tr>
            <td>{nombre}</td>
            <td>{puntos}</td>
            <td>{games}</td>
            <td>
                <Button
                    variant={"light"}
                    onClick={() => marcar()}>Sumar punto</Button>
            </td>
        </tr>
    )
}

export default Marcador;