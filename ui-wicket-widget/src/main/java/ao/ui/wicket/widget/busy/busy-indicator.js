
//------------------------------------------------------------------------
window.onload = setupFunc;
var isBusy = false;


//------------------------------------------------------------------------
function setupFunc() {
    document.getElementsByTagName('body')[0].onclick = clickHandler;
    hideBusySign();
    Wicket.Ajax.registerPreCallHandler(showBusySign);
    Wicket.Ajax.registerPostCallHandler(hideBusySign);
    Wicket.Ajax.registerFailureHandler(hideBusySign);
}


//------------------------------------------------------------------------
function hideBusySign() {
    isBusy = false;
    document.getElementById('busy_indicator').style.display ='none';
}

function showBusySign() {
    isBusy = true;
    setTimeout("doShowBusySign();", 500);
}
function doShowBusySign() {
    if (!isBusy) return;
    document.getElementById('busy_indicator').style.display ='inline';
}


//------------------------------------------------------------------------
function clickHandler(eventData) {
    var clickedElement = (window.event) ? event.srcElement : eventData.target;
    if (//clickedElement.tagName.toUpperCase() == 'BUTTON' ||
        clickedElement.tagName.toUpperCase() == 'A' ||
        clickedElement.parentNode.tagName.toUpperCase() == 'A' ||
        (clickedElement.tagName.toUpperCase() == 'INPUT' &&
         (//clickedElement.type.toUpperCase() == 'BUTTON' ||
          clickedElement.type.toUpperCase() == 'SUBMIT'))) {
        showBusySign();
    }
}