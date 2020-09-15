console.log("Writed on NVim");

console.log("This is executed with NodeJS");

function niceAlert() {
    alert("This is a nice alert");
}

let totalCost = 3 * 21.15;
console.log(totalCost);

console.log(sum(5, 6));

function sum(a, b) {
    return a + b;
}

function doSomeStuff(a) {
    let letVariable = a * 4;
    console.log("Inside of doSomeStuff(): " + letVariable);
    return letVariable, a;
}

let a = 3;

let b, c = doSomeStuff(3);

console.log(a);
console.log(b);
console.log(c);

function nullParams(a = 0, b = 9) {
    console.log("a = " + a + " | b = " + b);
}

nullParams(1, 3);

let prettyFunction = () => {
    console.log("Inside a function defined on a variable");
}

prettyFunction();
