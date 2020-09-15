const fileSystem = require("fs");

let files = fileSystem.readdirSync("./assets"); // Note: this methods execute in a sync way
files.forEach(file => console.log(file));

// Same as above, but asynchronous
fileSystem.readdir("./assets", (error, readFiles) => {
    if (error) throw error;
    readFiles.forEach(file => console.log(file));
    console.log("Ending async readdir() function");
});

// Note this, when code is executed, will be shown first above function. That's cos async call, it means code keep executing
console.log("Starting async directory read");