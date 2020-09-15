const fileSystem = require("fs");

// Json file can be read thanks to NodeJS. Method set each property of .json file on variable
let data = require("./assets/colors.json");

// Destructured call on forEach()
data.colorList.forEach(({hex, name}) => {
    // appendFile() method create file if doesn't exist, else append content to
    fileSystem.appendFile("./created-dir/color.md", `${name} : ${hex}\n`, err => {
        if (err) throw err;
        console.log("Successful created file");
    });
});