const fileSystem = require("fs");

// Sync way to read files
let syncFile = fileSystem.readFileSync("./assets/text.txt", "utf8");
console.log(syncFile);

// Async way to read files
let asyncFile = fileSystem.readFile("./assets/os_pop.png", (err, image) => {
    if (err) console.log(err.message);
    console.log(image);
})