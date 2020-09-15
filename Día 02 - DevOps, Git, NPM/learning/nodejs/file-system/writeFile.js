const fileSystem = require("fs");
let fileName = "README.md";

// Async way to write a file inside of a directory
fileSystem.writeFile("./assets/" + fileName, "This is a test message wrote whit writeFile() function", err => {
    if (err) throw err;
    console.log("Successful created file");
});

// Async way to read previously created file. Note 'utf8' param helps to read content properly
fileSystem.readFile("./assets/" + fileName, "utf8",(err, data) => {
    if (err) throw err;
    console.log(data);
});