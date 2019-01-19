YAHOO.widget.WicketDataSource = function(callbackUrl) {
    this.callbackUrl = callbackUrl;
    this.responseArray = [];
    this.transactionId = 0;
};

YAHOO.widget.WicketDataSource.prototype = new YAHOO.util.LocalDataSource();

YAHOO.widget.WicketDataSource.prototype.makeConnection =
function(oRequest, oCallback, oCaller) {
    var tId = this.transactionId++;
    this.fireEvent("requestEvent", {
        tId: tId,
        request: oRequest,
        callback: oCallback,
        caller: oCaller});

    var _this = this;
    var onWicketSuccessFn = function() {
        _this.handleResponse(
                oRequest, _this.responseArray, oCallback, oCaller, tId);
    };
    wicketAjaxGet(this.callbackUrl + '&q=' + oRequest, onWicketSuccessFn);
};

YAHOO.widget.WicketAutoComplete =
function(inputId, toggleId, callbackUrl, containerId) {
    this.dataSource   = new YAHOO.widget.WicketDataSource(callbackUrl);
    this.autoComplete = new YAHOO.widget.AutoComplete(
            inputId, containerId, this.dataSource);
    this.autoComplete.prehighlightClassName = "yui-ac-prehighlight";
    this.autoComplete.useShadow = true;
    this.autoComplete.formatResult =
        function(oResultData, sQuery, sResultMatch) {
            return oResultData;
        };

    var toggleElement = YAHOO.util.Dom.get(toggleId);
    if (toggleElement == null) {
        this.autoComplete.minQueryLength = 1;
    } else {
        this.autoComplete.minQueryLength = 0;

        var toggleButton = new YAHOO.widget.Button({
                container:toggleElement});

        var ac = this.autoComplete;
        toggleButton.on("click", function(e) {
            if(!YAHOO.util.Dom.hasClass(toggleElement, "toggle-open")) {
                YAHOO.util.Dom.addClass(toggleElement, "toggle-open");
            }

            if (ac.isContainerOpen()) {
                ac.collapseContainer();
            } else {
                ac.getInputEl().focus();
                setTimeout(function() { // For IE
                    ac.sendQuery("");
                }, 0);
            }
        });

        ac.containerCollapseEvent.subscribe(
                function(){YAHOO.util.Dom.removeClass(
                        toggleElement, "toggle-open");});
    }
};

function autoCompleteSelectSendback(
        autoComplete, callbackUrl)
{
    autoComplete.autoComplete.itemSelectEvent.subscribe(
            function(sType, aArgs) {
                wicketAjaxGet(callbackUrl + '&s=' + aArgs[2]);
            });
}