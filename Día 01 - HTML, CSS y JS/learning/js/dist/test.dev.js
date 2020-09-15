"use strict";

console.log("Writed on NVim");
console.log("This is executed with NodeJS");

function niceAlert() {
  alert("This is a nice alert");
}

var totalCost = 3 * 21.15;
console.log(totalCost);
console.log(sum(5, 6));

function sum(a, b) {
  return a + b;
}

function doSomeStuff(a) {
  var letVariable = a * 4;
  console.log("Inside of doSomeStuff(): " + letVariable);
  return letVariable, a;
}

var a = 3;
var b,
    c = doSomeStuff(3);
console.log(a);
console.log(b);
console.log(c);

function nullParams() {
  var a = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 0;
  var b = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 9;
  console.log("a = " + a + " | b = " + b);
}

nullParams(1, 3);

var prettyFunction = function prettyFunction() {
  console.log("Inside a function defined on a variable");
};

prettyFunction();