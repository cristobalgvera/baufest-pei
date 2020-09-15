"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

var ListaComentarios = new Array();
var highlightCommitsIds = new Array();
var maxCommitsNumber = 10;
var usersList = [{
  id: 1,
  name: 'Eduardo Gatica'
}, {
  id: 2,
  name: 'Ximena Ojeda'
}, {
  id: 3,
  name: 'Juan Urrea'
}, {
  id: 4,
  name: 'Daniela Saldivia'
}];
var users = document.getElementById("users-list");
usersList.forEach(function (user) {
  console.log(user.name);
  var opt = document.createElement('option');
  opt.appendChild(document.createTextNode(user.name));
  opt.value = user.id;
  users.appendChild(opt);
});

document.getElementById("commit-form").onsubmit = function (event) {
  event.preventDefault;
  var userId = document.getElementById("users-list").value;
  var user = usersList.find(function (element) {
    return element.id == userId;
  });
  var highlight = document.getElementById("highlight").checked;
  var commit = document.getElementById("commit").value;

  if (!validate(user)) {
    return false;
  }

  agregarComentario(user.id, user.name, commit, highlight);
  document.getElementById("commit-form").reset();
  var claseError = document.querySelectorAll('.error');
  OcultarElementos(claseError);
};

function validate(user) {
  if (maxUserCommitNumberReached(user.id)) {
    console.log("Inside user commit max");
    document.getElementById("errorUsuarioCantidad").style.display = 'block';
    return false;
  }

  if (maxTotalCommitNumberReached()) {
    console.log("Inside total commit max"); // document.getElementById("errorComentariosCantidad").style.display = 'block';

    alert("Se alcanz\xF3 el n\xFAmero m\xE1ximo de comentarios (".concat(maxCommitsNumber, ")"));
    return false;
  }

  return true;
}

; //Crear constructor de Comentario con propiedades necesarias

var Comentario =
/*#__PURE__*/
function () {
  function Comentario(userId, commit, highlight) {
    _classCallCheck(this, Comentario);

    this._userId = userId;
    this._commit = commit;
    this._highlight = highlight;
  }

  _createClass(Comentario, [{
    key: "userId",
    get: function get() {
      return this._userId;
    }
  }, {
    key: "commit",
    get: function get() {
      return this._commit;
    }
  }, {
    key: "highlight",
    get: function get() {
      return this._highlight;
    },
    set: function set(value) {
      this._highlight = value;
    }
  }]);

  return Comentario;
}();

; //Función a llamar para ocultar comentarios

function OcultarElementos(elementos) {
  elementos.forEach(function (item) {
    item.style.display = 'none';
  });
}

; //Evento a ejecutarse al cargar la página 
//Tiene que llamar al OcultarElementos, validar los inputs, grabar y limpiar los controles

document.addEventListener('DOMContentLoaded', function (event) {
  var claseComentario = document.querySelectorAll('.comentario');
  var claseError = document.querySelectorAll('.error');
  var botonGrabar = document.querySelector('#grabar');
  OcultarElementos(claseComentario);
  OcultarElementos(claseError); // botonGrabar.addEventListener('click', () => {
  //     return false;
  // });
});

function agregarComentario(idUsuario, usuario, comentario, destacado) {
  if (destacado) {
    clearClasses(idUsuario);
  }

  ListaComentarios.push(new Comentario(idUsuario, comentario, destacado));
  var nombreDiv = "comentario".concat(ListaComentarios.length);
  var divNuevoComentario = document.querySelector('#comentarioBase').cloneNode(true);
  divNuevoComentario.id = nombreDiv;
  var labelUsuario = divNuevoComentario.querySelectorAll('.usuario')[0];
  var divComentario = divNuevoComentario.querySelectorAll('.textoComentario')[0];
  labelUsuario.innerText = usuario;
  divComentario.innerHTML = comentario;

  if (destacado) {
    divComentario.classList.add("destacado");
    highlightCommitsIds.push({
      'userId': idUsuario,
      'commitId': divNuevoComentario.id
    });
  }

  document.querySelector("#comentarios").appendChild(divNuevoComentario);
  document.querySelector("#" + nombreDiv).style.display = 'block';
  document.querySelector("#cantidadComentarios").innerText = ListaComentarios.length;
}

; //Crear funcion de limpiar clases

function clearClasses(userId) {
  ListaComentarios.filter(function (commit) {
    return commit._userId == userId;
  }).forEach(function (commit) {
    return commit._highlight = false;
  });
  console.log(highlightCommitsIds);
  highlightCommitsIds.filter(function (commit) {
    return commit.userId == userId;
  }).forEach(function (commit) {
    document.getElementById(commit.commitId).querySelectorAll('.textoComentario')[0].classList.remove("destacado");
    highlightCommitsIds.splice(highlightCommitsIds.indexOf(commit));
  });
}

; //Crear funciones para las distintas validaciones (que llamen a las funciones auxiliares) y llamarlas desde una sola función

function maxTotalCommitNumberReached() {
  console.log("Total commits: " + cantidadComentariosTotal());
  return cantidadComentariosTotal() >= maxCommitsNumber;
}

;

function maxUserCommitNumberReached(userId) {
  console.log("User ID: " + userId);
  console.log("User total commits: " + cantidadComentariosPorUsuario(userId));
  return cantidadComentariosPorUsuario(userId) >= 3;
}

; //Auxiliar validar cantidad de comentarios por usuario

function cantidadComentariosPorUsuario(idUsuario) {
  var comentariosUsuario = ListaComentarios.filter(function (c) {
    return c._userId == idUsuario;
  });
  return comentariosUsuario.length;
}

; //Auxiliar validar cantidad de comentarios destacados por usuario

function cantidadComentariosDestacadosPorUsuario(idUsuario) {
  var comentariosUsuario = ListaComentarios.filter(function (c) {
    return c._userId == idUsuario && c._highlight;
  });
  return comentariosUsuario.length;
}

; //Auxiliar validar cantidad de comentarios total

function cantidadComentariosTotal() {
  return ListaComentarios.length;
}

;