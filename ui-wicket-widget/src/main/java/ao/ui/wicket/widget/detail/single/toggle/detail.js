var imgShow = new Image(9,9);
var imgHide = new Image(9,9);

	imgShow.src="layout/detail/u.gif";
	imgHide.src="layout/detail/d.gif";

//this switches expand collapse icons
function filter(imagename,objectsrc){
	if (document.images){
		document.images[imagename].src=eval(objectsrc+".src");
	}
}

function isSourceEqual(imageName, objectSrc){
	if (document.images) {
		return document.images[ imageName ].src ==
                 eval(objectSrc + ".src");
	}
    return false;
}

//show OR hide funtion depends on if element is shown or hidden
function toggleDetail(id) {
//    if (isSourceEqual("img_"+id, 'imgShow')) {
//        filter(("img_"+id),'imgHide');
//    } else {
//        filter(("img_"+id),'imgShow');
//    }
	if (document.getElementById) { // DOM3 = IE5, NS6
		if (document.getElementById(id).style.display == "none"){
			document.getElementById(id).style.display = 'block';
			filter(("img_"+id),'imgHide');
		} else {
			filter(("img_"+id),'imgShow');
			document.getElementById(id).style.display = 'none';
		}
	} else {
		if (document.layers) {
			if (document.id.display == "none"){
				document.id.display = 'block';
				filter(("img_"+id),'imgHide');
			} else {
				filter(("img_"+id),'imgShow');
				document.id.display = 'none';
			}
		} else {
			if (document.all.id.style.visibility == "none"){
				document.all.id.style.display = 'block';
			} else {
				filter(("img_"+id),'imgShow');
				document.all.id.style.display = 'none';
			}
		}
	}
}