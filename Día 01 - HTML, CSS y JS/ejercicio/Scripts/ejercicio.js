var ListaComentarios = new Array();
var highlightCommitsIds = new Array();
var maxCommitsNumber = 10;

var usersList = [
    { id: 1, name: 'Eduardo Gatica' },
    { id: 2, name: 'Ximena Ojeda' },
    { id: 3, name: 'Juan Urrea' },
    { id: 4, name: 'Daniela Saldivia' }
];

var users = document.getElementById("users-list");

usersList.forEach(user => {
    console.log(user.name);
    let opt = document.createElement('option');
    opt.appendChild(document.createTextNode(user.name));
    opt.value = user.id;
    users.appendChild(opt);
});

document.getElementById("commit-form").onsubmit = (event) => {
    event.preventDefault;
    let userId = document.getElementById("users-list").value;
    let user = usersList.find(element => element.id == userId);
    let highlight = document.getElementById("highlight").checked;
    let commit = document.getElementById("commit").value;
    if (!validate(user)) {
        return false;
    }
    agregarComentario(user.id, user.name, commit, highlight);
    document.getElementById("commit-form").reset();
    const claseError = document.querySelectorAll('.error');
    OcultarElementos(claseError);
};

function validate(user) {
    if (maxUserCommitNumberReached(user.id)) {
        console.log("Inside user commit max");
        document.getElementById("errorUsuarioCantidad").style.display = 'block';
        return false;
    }
    if (maxTotalCommitNumberReached()) {
        console.log("Inside total commit max");
        // document.getElementById("errorComentariosCantidad").style.display = 'block';
        alert(`Se alcanzó el número máximo de comentarios (${maxCommitsNumber})`);
        return false;
    }
    return true;
};

//Crear constructor de Comentario con propiedades necesarias
class Comentario {
    constructor(userId, commit, highlight) {
        this._userId = userId;
        this._commit = commit;
        this._highlight = highlight;
    }

    get userId() {
        return this._userId;
    }

    get commit() {
        return this._commit;
    }

    get highlight() {
        return this._highlight;
    }

    set highlight(value) {
        this._highlight = value;
    }
};

//Función a llamar para ocultar comentarios
function OcultarElementos(elementos) {
    elementos.forEach((item) => {
        item.style.display = 'none';
    });
};

//Evento a ejecutarse al cargar la página 
//Tiene que llamar al OcultarElementos, validar los inputs, grabar y limpiar los controles
document.addEventListener('DOMContentLoaded', (event) => {
    const claseComentario = document.querySelectorAll('.comentario');
    const claseError = document.querySelectorAll('.error');
    const botonGrabar = document.querySelector('#grabar');

    OcultarElementos(claseComentario);
    OcultarElementos(claseError);

    // botonGrabar.addEventListener('click', () => {
    //     return false;
    // });
});

function agregarComentario(idUsuario, usuario, comentario, destacado) {
    if (destacado) {
        clearClasses(idUsuario);
    }

    ListaComentarios.push(new Comentario(idUsuario, comentario, destacado));

    let nombreDiv = `comentario${ListaComentarios.length}`;
    let divNuevoComentario = document.querySelector('#comentarioBase').cloneNode(true);
    divNuevoComentario.id = nombreDiv;

    let labelUsuario = divNuevoComentario.querySelectorAll('.usuario')[0];
    let divComentario = divNuevoComentario.querySelectorAll('.textoComentario')[0];

    labelUsuario.innerText = usuario;
    divComentario.innerHTML = comentario;
    if (destacado) {
        divComentario.classList.add("destacado");
        highlightCommitsIds.push({ 'userId': idUsuario, 'commitId': divNuevoComentario.id });
    }
    document.querySelector("#comentarios").appendChild(divNuevoComentario);

    document.querySelector("#" + nombreDiv).style.display = 'block';

    document.querySelector("#cantidadComentarios").innerText = ListaComentarios.length;
};

//Crear funcion de limpiar clases
function clearClasses(userId) {
    ListaComentarios.filter(commit => commit._userId == userId)
        .forEach(commit => commit._highlight = false);

    console.log(highlightCommitsIds);

    highlightCommitsIds.filter(commit => commit.userId == userId)
        .forEach(commit => {
            document.getElementById(commit.commitId)
                .querySelectorAll('.textoComentario')[0]
                .classList.remove("destacado");
            highlightCommitsIds.splice(highlightCommitsIds.indexOf(commit));
        });
};

//Crear funciones para las distintas validaciones (que llamen a las funciones auxiliares) y llamarlas desde una sola función
function maxTotalCommitNumberReached() {
    console.log("Total commits: " + cantidadComentariosTotal());
    return cantidadComentariosTotal() >= maxCommitsNumber;
};

function maxUserCommitNumberReached(userId) {
    console.log("User ID: " + userId);
    console.log("User total commits: " + cantidadComentariosPorUsuario(userId));
    return cantidadComentariosPorUsuario(userId) >= 3;
};

//Auxiliar validar cantidad de comentarios por usuario
function cantidadComentariosPorUsuario(idUsuario) {
    let comentariosUsuario = ListaComentarios.filter((c) => {
        return (c._userId == idUsuario);
    });
    return comentariosUsuario.length;
};

//Auxiliar validar cantidad de comentarios destacados por usuario
function cantidadComentariosDestacadosPorUsuario(idUsuario) {
    let comentariosUsuario = ListaComentarios.filter((c) => {
        return (c._userId == idUsuario && c._highlight);
    });
    return comentariosUsuario.length;
};

//Auxiliar validar cantidad de comentarios total
function cantidadComentariosTotal() {
    return ListaComentarios.length;
};

