const fileSystem = require("fs");
let directoryName = "created-dir";

// Sync call to stop app and check existence of folder
if (fileSystem.existsSync(directoryName)) {
    console.log("Directory already exist");
} else {
    // Async call to create folder on current directory
    fileSystem.mkdir(directoryName, err => {
        if (err) console.log(err.message);
        console.log("Successfully created directory");
    })
}
