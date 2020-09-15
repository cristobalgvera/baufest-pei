"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

var Rectangle =
/*#__PURE__*/
function () {
  function Rectangle(base) {
    var height = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 0;

    _classCallCheck(this, Rectangle);

    this._base = base;
    this._height = height;
  }

  _createClass(Rectangle, [{
    key: "getArea",
    value: function getArea() {
      return this._base * this._height;
    }
  }, {
    key: "base",
    get: function get() {
      return this._base;
    },
    set: function set(base) {
      return this._base = base;
    }
  }], [{
    key: "getSomeNumber",
    value: function getSomeNumber(a) {
      return a * 4;
    }
  }]);

  return Rectangle;
}();

var figure = new Rectangle(3);
console.log(figure.getArea());
console.log(figure);
console.log(Rectangle.getSomeNumber(3));