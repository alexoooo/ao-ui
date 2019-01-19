
/**
* Delay for a number of milliseconds
*/
function sleep(delay)
{
    var start = new Date().getTime();
    while (new Date().getTime() < (start + delay)) {};
}


function ajaxFetchUrl(fullUrl)
{
    var xmlHttpObject = null;

    try
    {
        // Firefox, Opera 8.0+, Safari...
        xmlHttpObject = new XMLHttpRequest();
    }
    catch(ex)
    {
        // Internet Explorer...

        try
        {
            xmlHttpObject = new ActiveXObject('Msxml2.XMLHTTP');
        }
        catch(ex)
        {
            xmlHttpObject = new ActiveXObject('Microsoft.XMLHTTP');
        }
    }

    if (xmlHttpObject == null)
    {
        window.alert('AJAX is not available in this browser');
        return null;
    }

    try {
        xmlHttpObject.open("GET", fullUrl, false);
        xmlHttpObject.send();

        return xmlHttpObject.responseText;
    } catch(ex) {
        return null;
    }
}