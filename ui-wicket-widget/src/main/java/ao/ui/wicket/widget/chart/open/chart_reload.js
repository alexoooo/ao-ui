
var timerId_${chartId}  = 0;
var fails_${chartId}    = 0;
var prevHash_${chartId} = null;

function reload_${chartId}() {
    if (timerId_${chartId}) {
        clearTimeout(timerId_${chartId});
    }

    var chart = findSWF("${chartId}");
    if (chart != null) {
        try {
            var hash = ajaxFetchUrl("${chartHashUrl}");
            if (hash == null) {
                //alert("null delta: " + fails_${chartId});
                fails_${chartId}++;
            } else {
                if (prevHash_${chartId} != null &&
                        prevHash_${chartId} != hash) {
                    chart.reload("${chartJsonUrl}");
                }
                prevHash_${chartId} = hash;
            }
        } catch(e) {
            fails_${chartId}++;
//            alert(e);
        }
    }

    if (fails_${chartId} <= 3) {
            timerId_${chartId} = setTimeout(
                    "reload_${chartId}()", ${timeout});
    }
}

timerId_${chartId} = setTimeout(
            "reload_${chartId}()", ${timeout});