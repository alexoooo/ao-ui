
function findSWF(movieName) {
    if (navigator.appName.indexOf("Microsoft") != -1) {
        return window["ie_" + movieName];
        //return document.getElementById("ie_" + movieName);
    } else {
        return document[movieName];
        //return document.getElementById(movieName);
    }
}