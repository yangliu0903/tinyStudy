function toggleNext(el,t) {
	var next = el.nextSibling;
	while(next.nodeType != 1) next = next.nextSibling;
	if(t){next.style.display = ((next.style.display == "none")?"block":"none");return;}
	(el.className != "h4over")?(el.className = "h4over"):(el.className = " ");
	var	uls= document.getElementsByTagName("ul");	
	//for(var i=0;i<uls.length;i++)
	//{
		//if(uls[i] != next)
		//{
		  	//var h4 = uls[i].previousSibling;
			//while(h4.nodeType != 1) h4 = h4.previousSibling;
			//h4.className=" ";
			//uls[i].style.display="none";
		//}	
	//}
	next.style.display = ((next.style.display == "none")?"block":"none");
}
function toggleNextByTagName(tname) {
	var ccn="clicker";
	var clickers = document.getElementsByTagName(tname);
	for (i=0; i<clickers.length; i++) {
		clickers[i].className+=" "+ccn;
		clickers[i].onclick = function() { toggleNext(this) }
		toggleNext(clickers[i],true);
	}
}
window.onload = function(){ toggleNextByTagName('h4') }