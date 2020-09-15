// Getting all module
const utilityFunctions = require("./utility");

console.log("Before increment: " + utilityFunctions.getCount());

for (let i = 0; i < 3; i++) {
    utilityFunctions.plusOne();
}

console.log("After increment: " + utilityFunctions.getCount());

// Destructuring module
const {getCount, minusOne} = require("./utility");

console.log("Before decrement destructured: " + getCount());

for (let i = 0; i < 3; i++) {
    minusOne();
}

console.log("After decrement destructured: " + getCount());

// Note: here variable 'count', defined on './utility.nodejs', is used in both cases in a "singleton" way