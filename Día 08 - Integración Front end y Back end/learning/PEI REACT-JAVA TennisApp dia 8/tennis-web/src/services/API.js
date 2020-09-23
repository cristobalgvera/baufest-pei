import Axios from "axios";

const instance = Axios.create({
    baseURL: `http://localhost:8080/springtennis/api/v1/`
});

//get
const get = async (url) => {
    return instance.get(url)
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            throw error;
        })
}

//save
const save = async (url, body = null) => {
    return instance.post(url, body)
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            throw error;
        })
}

//update
const update = async (url, body = null) => {
    instance.put(url, body)
        .catch(error => {
            throw error;
        })
}

//remove
const remove = async (url) => {
    await instance.delete(url)
        .catch(error => {
            throw error;
        })
}

const marcar = async (url, modoJugador = null) => {
    instance.post(url,null,{params: {modoJugador: modoJugador}})
        .catch(error => {
            throw error;
        })
}

const iniciar = async (url) => {
    instance.put(url)
        .catch(error => {
            throw error;
        })
}

export default {get, save, update, remove, marcar, iniciar};
// export default {get, save};