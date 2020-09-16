// Kind of import
let Persona = require("./persona"); // Persona instance represents Persona class itself

// Instantiate Persona class
let personaObj = new Persona();
console.log("Persona:");
personaObj.walk(); // Use methods

// Kind of static import
let doggy = require("./dog"); // doggy instance represents Dog class instance
console.log("\nDog:");
doggy.bark(); // Simply use methods, instantiation is "already done"
doggy.eat(); // Another method defined inside of Dog class
