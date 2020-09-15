document.getElementById("color-button").addEventListener("click", changeAllCellsColors);

// This function change color of table defined on html
function changeAllCellsColors() {
    document.querySelectorAll("td").forEach(element => {
        var eBgColor = element.style.backgroundColor;
        if (eBgColor == "red") {
            element.style.backgroundColor = "green";
        } else {
            element.style.backgroundColor = "red";
        }
    });
}