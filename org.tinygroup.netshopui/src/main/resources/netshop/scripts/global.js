/* This function is used to change the style class of an element */
function swapClass(obj, newStyle) {
    obj.className = newStyle;
}

function isUndefined(value) {
    var undef;
    return value == undef;
}

function checkAll(theForm) { // check all the checkboxes in the list
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
        var eName = e.name;
        if (eName != 'allbox' &&
            (e.type.indexOf("checkbox") == 0)) {
            e.checked = theForm.allbox.checked;
        }
    }
}

function checkAllWithName(theForm, allCheckName) { // check all the checkboxes in the list with the name of the all check box
  var allcheck;
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
        var eName = e.name;
        if(eName==allCheckName){
        	allcheck=e;
        }
    }
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
        var eName = e.name;
        if (eName != allCheckName &&
            (e.type.indexOf("checkbox") == 0)) {
            e.checked = allcheck.checked;
        }
    }
}

/* Function to clear a form of all it's values */
function clearForm(frmObj) {
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if(element.type.indexOf("text") == 0 ||
                element.type.indexOf("password") == 0) {
                    element.value="";
        } else if (element.type.indexOf("radio") == 0) {
            element.checked=false;
        } else if (element.type.indexOf("checkbox") == 0) {
            element.checked = false;
        } else if (element.type.indexOf("select") == 0) {
            for(var j = 0; j < element.length ; j++) {
                element.options[j].selected=false;
            }
            element.options[0].selected=true;
        }
    }
}

/* Function to get a form's values in a string */
function getFormAsString(frmObj) {
    var query = "";
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if (element.type.indexOf("checkbox") == 0 ||
            element.type.indexOf("radio") == 0) {
            if (element.checked) {
                query += element.name + '=' + escape(element.value) + "&";
            }
        } else if (element.type.indexOf("select") == 0) {
            for (var j = 0; j < element.length ; j++) {
                if (element.options[j].selected) {
                    query += element.name + '=' + escape(element.value) + "&";
                }
            }
        } else {
            query += element.name + '='
                  + escape(element.value) + "&";
        }
    }
    return query;
}

/* Function to hide form elements that show through
   the search form when it is visible */
function toggleForm(frmObj, iState) // 1 visible, 0 hidden
{
    for(var i = 0; i < frmObj.length; i++) {
        if (frmObj.elements[i].type.indexOf("select") == 0 || frmObj.elements[i].type.indexOf("checkbox") == 0) {
            frmObj.elements[i].style.visibility = iState ? "visible" : "hidden";
        }
    }
}

/* Helper function for re-ordering options in a select */
function opt(txt,val,sel) {
    this.txt=txt;
    this.val=val;
    this.sel=sel;
}

/* Function for re-ordering <option>'s in a <select> */
function move(list,to) {
    var total=list.options.length;
    index = list.selectedIndex;
    if (index == -1) return false;
    if (to == +1 && index == total-1) return false;
    if (to == -1 && index == 0) return false;
    to = index+to;
    var opts = new Array();
    for (i=0; i<total; i++) {
        opts[i]=new opt(list.options[i].text,list.options[i].value,list.options[i].selected);
    }
    tempOpt = opts[to];
    opts[to] = opts[index];
    opts[index] = tempOpt
    list.options.length=0; // clear

    for (i=0;i<opts.length;i++) {
        list.options[i] = new Option(opts[i].txt,opts[i].val);
        list.options[i].selected = opts[i].sel;
    }

    list.focus();
}

/*  This function is to select all options in a multi-valued <select> */
function selectAll(elementId) {
    var element = document.getElementById(elementId);
    len = element.length;
    if (len != 0) {
        for (i = 0; i < len; i++) {
            element.options[i].selected = true;
        }
    }
}

/* This function is used to select a checkbox by passing
 * in the checkbox id
 */
function toggleChoice(elementId) {
    var element = document.getElementById(elementId);
    if (element.checked) {
        element.checked = false;
    } else {
        element.checked = true;
    }
}

/* This function is used to select a radio button by passing
 * in the radio button id and index you want to select
 */
function toggleRadio(elementId, index) {
    var element = document.getElementsByName(elementId)[index];
    element.checked = true;
}

/* This function is used to open a pop-up window */
function openWindow(url, winTitle, winParams) {
    winName = window.open(url, winTitle, winParams);
    winName.focus();
}


/* This function is to open search results in a pop-up window */
function openSearch(url, winTitle) {
    var screenWidth = parseInt(screen.availWidth);
    var screenHeight = parseInt(screen.availHeight);

    var winParams = "width=" + screenWidth + ",height=" + screenHeight;
        winParams += ",left=0,top=0,toolbar,scrollbars,resizable,status=yes";

    openWindow(url, winTitle, winParams);
}

/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
    var prefix = name + "="
    var start = document.cookie.indexOf(prefix)

    if (start==-1) {
        return null;
    }

    var end = document.cookie.indexOf(";", start+prefix.length)
    if (end==-1) {
        end=document.cookie.length;
    }

    var value=document.cookie.substring(start+prefix.length, end)
    return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}

// This function is for stripping leading and trailing spaces
String.prototype.trim = function () {
    return this.replace(/^\s*(\S*(\s+\S+)*)\s*$/, "$1");
};

// This function is used by the login screen to validate user/pass
// are entered.
function validateRequired(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oRequired = new required();

    for (x in oRequired) {
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]];

           fields[i++] = oRequired[x][1];

           bValid = false;
        }
    }

    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));
    }

    return bValid;
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(obj) {
    var msg = "Are you sure you want to delete this " + obj + "?";
    ans = confirm(msg);
    if (ans) {
        return true;
    } else {
        return false;
    }
}

function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId);
    var startRow = 0;
    // workaround for Tapestry not using thead
    if (!table.getElementsByTagName("thead")[0]) {
	    startRow = 1;
    }
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    for (i=startRow; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.className+=' over' };
        rows[i].onmouseout = function() { this.className=previousClass };
        rows[i].onclick = function() {
            var cell = this.getElementsByTagName("td")[0];
            var link = cell.getElementsByTagName("a")[0];
            if (link.onclick) {
                call = link.getAttribute("onclick");
                if (call.indexOf("return ") == 0) {
                    call = call.substring(7);
                }
                // this will not work for links with onclick handlers that return false
                eval(call);
            } else {
                location.href = link.getAttribute("href");
            }
            this.style.cursor="wait";
            return false;
        }
    }
}

function highlightFormElements() {
    // add input box highlighting
    addFocusHandlers(document.getElementsByTagName("input"));
    addFocusHandlers(document.getElementsByTagName("textarea"));
}

function addFocusHandlers(elements) {
    for (i=0; i < elements.length; i++) {
        if (elements[i].type != "button" && elements[i].type != "submit" &&
            elements[i].type != "reset" && elements[i].type != "checkbox" && elements[i].type != "radio") {
            if (!elements[i].getAttribute('readonly') && !elements[i].getAttribute('disabled')) {
                elements[i].onfocus=function() {this.style.backgroundColor='#ffd';this.select()};
                elements[i].onmouseover=function() {this.style.backgroundColor='#ffd'};
                elements[i].onblur=function() {this.style.backgroundColor='';}
                elements[i].onmouseout=function() {this.style.backgroundColor='';}
            }
        }
    }
}

function radio(clicked){
    var form = clicked.form;
    var checkboxes = form.elements[clicked.name];
    if (!clicked.checked || !checkboxes.length) {
        clicked.parentNode.parentNode.className="";
        return false;
    }

    for (i=0; i<checkboxes.length; i++) {
        if (checkboxes[i] != clicked) {
            checkboxes[i].checked=false;
            checkboxes[i].parentNode.parentNode.className="";
        }
    }

    // highlight the row
    clicked.parentNode.parentNode.className="over";
}

window.onload = function() {
    highlightFormElements();
    if ($('successMessages')) {
        new Effect.Highlight('successMessages');
        // causes webtest exception on OS X : http://lists.canoo.com/pipermail/webtest/2006q1/005214.html
        // window.setTimeout("Effect.DropOut('successMessages')", 3000);
    }
    if ($('errorMessages')) {
        new Effect.Highlight('errorMessages');
    }

    /* Initialize menus for IE */
    if ($("primary-nav")) {
        var navItems = $("primary-nav").getElementsByTagName("li");

        for (var i=0; i<navItems.length; i++) {
            if (navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }
    }
}

// Show the document's title on the status bar
window.defaultStatus=document.title;






// --------------------------------------------------------------------------------------- //









var $w = window;
var $d = document;
var $l = location;
function $i(s){return $d.getElementById(s);}
function $t(s){return $d.getElementsByTagName(s);}
function $n(s){return $d.getElementsByName(s);}

var _jsc = {};
if(!_jschome)var _jschome='http://www.alimama.com/jsc/';

_jsc.loaded = false;

_jsc.client = (function(){	
	var t = {};
	var b = navigator.userAgent.toLowerCase();
	t.isOpera = (b.indexOf('opera') > -1);
	t.isIE = (!t.isOpera && b.indexOf('msie') > -1);
	t.isFF = (!t.isOpera &&!t.isIE&&b.indexOf('firefox') > -1);
	return t;
})();

_jsc.util = (function(){
	var t = {};
	t.addEvent = function(o,c,h){
		if(_jsc.client.isIE){
			o.attachEvent('on'+c,h);
		}else{
			o.addEventListener(c,h,false);
		}
		return true;
	};
	t.delEvent = function(o,c,h){
		if(_jsc.client.isIE){
			o.detachEvent('on'+c,h);
		}else{
			o.removeEventListener(c,h,false);
		}
		return true;
	};
	t.ga = function(o,s){
		return o.getAttribute(s);
	};
	t.sa = function(o,k,v){
		return o.setAttribute(k,v);
	};
	t.s2d = function(s){
		var sa = s.split('-');
		var d =  new Date(sa[0],(sa[1]-1),sa[2]);
		return d;
	};
	t.d2s = function(d,b){
		var ye = d.getFullYear();
		var me = (parseInt(d.getMonth())+1).toString()
		var de = d.getDate();
		if(me.length==1&&b)me='0'+me;
		if(de.length==1&&b)de='0'+de;
		return ye+me+de;
	};
	return t;
})();

_jsc.mgr = (function(){
	var t = {};
	t.s = [];
	t.addC = function(o){
		this.s.push(o);
	};
	t.getC = function(bid){
		for(var i=0;i<this.s.length;i++){
			if(this.s[i].bid==bid){
				return this.s[i];
			}
		}
		return null;
	};
	return t;
})();

_jsc.ajax = (function(){
	t={};
	t.getAjax = function(){
		try{
			return new XMLHttpRequest();
		}catch(e){
			try{
				return new ActiveXObject('Msxml2.XMLHTTP');
			}catch(e){
				return new ActiveXObject('Microsoft.XMLHTTP')
			}
		}
		return null;
	};
	return t;
})();

_jsc.dom = (function(){
	var t = {};
	t.gNxtSib = function(o){
		var co = o;
		do{
			if(co.nextSibling==null || co.nextSibling.nodeType==1){
				return co.nextSibling;
			}else{
				co = co.nextSibling;
			}			
		}while(true)
	};
	
	return t;
})();

_jsc.evt = (function(){
	var t = {};
	t.gTar = function(oe){
		if(_jsc.client.isIE){
			return oe.srcElement;
		}else{
			return oe.target;
		}
	};
	t.gJsc = function(o){
		var ot = o
		do{
			if(ot.getAttribute("c_type")){
				return ot;
			}
			if(ot.parentNode){
				ot = ot.parentNode;
			}else{
				return null;
			}
		}while(true);
	};
	t.evtHandler = function(){
		var eo = window.event?window.event:arguments[0];
		var tar = _jsc.evt.gTar(eo);
		var jsc = _jsc.evt.gJsc(tar).jsc;
		et = eo.type;
		eval("var h = jsc.jsc"+et);
		h(tar,jsc);
	};
	t.fire = function(jsc,etype,evt){
		eval("var h = jsc.c_on"+etype);
		eval("var t = typeof "+h);
		if(t == "function"){
			eval(h+"(evt)");
		}
	};
	return t;
})();

_jsc.his = (function(){
	var t={};
	t.srvPath = _jschome+'htm/hisSrv.html';
	t.srvFrame = $i("hisSrvFrame");
	t.ahMap = {};
	t.setAH = function(act,handler){
		var m = this.ahMap;
		eval('m.'+act+'="'+handler+'";');
	};
	t.doGet = function(act,para){
	    var appSch = $n("jscAppendSearch");
	    var as = "";
	    for(var i=0;i<appSch.length;i++){
	        var appAct = appSch[i].getAttribute("act");
	        if(appAct == act){
	            as += appSch[i].value;
	        }
	    }
		if(!this.srvFrame)this.srvFrame = $i("hisSrvFrame");
		this.srvFrame.src = this.srvPath+'?act='+act+as+para;
	};
	t.actionSwitch = function(act,para){
		var m = this.ahMap;
		eval('var h = this.ahMap.'+act+';');
		eval(h+'(para);');
	};
	t.setRefCookie = function(pagename){
	    try{
	        var refCookie = []; 
			refCookie.push(pagename);
            refCookie.push($i("hisSrvFrame").contentWindow.location.search);
            if(typeof addRefCookie == "function"){
                addRefCookie(refCookie);
            }
            var refreshCookie = refCookie.join("@jsc@");
            _jsc.cookies.setCookie("jscref",refreshCookie,6/60/60/24);
        }catch(e){}
	};
	t.getRefCookie = function(){
	    var refCookie = []; 
	    try{
	       var refreshCookie = _jsc.cookies.getCookie("jscref");
	       refCookie = refreshCookie.split("@jsc@");
	    }catch(e){}
	    return refCookie;
	};
	return t;
})();

_jsc.state = (function(){
	var t = {};
	t.eleList = [];
	t.chkList = [];
	t.loaded = false;
	t.addEle = function(o){
		if(o instanceof JscStateElement){
			this.eleList.push(o);
			if(o.check==true){
				this.chkList.push(o);
			}
		}
	};	
	t.setState = function(para){
		var u = para;
		for(var i = 0;i < this.eleList.length;i++){
			var ele = this.eleList[i];
			var ukey = ele.urlKey;
			var uvalue = this.getUrlValue(para,ukey);
			ele.setState(uvalue);
		}
	};
	t.getUrlValue = function(para,ukey){
		var andpara = '&'+para;
		var lowerpara = andpara.toLowerCase();
		if(lowerpara.indexOf('&'+ukey+'=')!=-1){
			var s = andpara.substr(lowerpara.indexOf('&'+ukey+'=')+1);
			var sa = s.split('&');
			var sav = sa[0].split('=');
			if(sav.length==2){
				return sav[1];
			}
		}
		return '';
	};
	t.getState = function(ukey){
		var u='';
		for(var i = 0;i < this.eleList.length;i++){
			var ele = this.eleList[i];
			if(ele.urlKey==ukey||ele.check==true){
				ele.getState();
			}
		}
		for(var i = 0;i < this.eleList.length;i++){
			var ele = this.eleList[i];
			u+='&'+ele.urlKey+'='+ele.currentState;
		}
		_jsc.his.doGet('sch',u);
	};
	t.chgPage = function(pgno){
		var u = '&p='+pgno;
		for(var i = 0;i < this.eleList.length;i++){
			var ele = this.eleList[i];
			ele.getState();
			u+='&'+ele.urlKey+'='+ele.currentState;
		}
		_jsc.his.doGet('sch',u);
		return false;
	};
	t.toDefaultState = function(){
		
	};
	return t;
})();

_jsc.pos = (function(){
	var t = {};
	t.getX = function (obj){
        var curleft = 0;
        if (obj.offsetParent)
        {
          while (obj.offsetParent)
        {
             curleft += obj.offsetLeft;
             obj = obj.offsetParent;
          }
        }
        else if (obj.x) {
          curleft += obj.x;
        }
        return curleft;
    };
    t.getY = function (obj){
    var curtop = 0;
        if (obj.offsetParent)
        {
          while (obj.offsetParent)
          {
             curtop += obj.offsetTop;
             obj = obj.offsetParent;
          }
        }
        else if (obj.y)
          curtop += obj.y;
        return curtop;
    };
	return t;
})();

_jsc.cookies = (function(){
	var t = {};
	t.setCookie = function(name,value,days)
	{
		if(days){
	  	var exp  = new Date(); 
	  	exp.setTime(exp.getTime() + days*24*60*60*1000);
	  	document.cookie = name + "="+ escape(value) +";expires="+ exp.toGMTString()+";path=/;";
		}else{
			document.cookie = name + "="+ escape(value)+";path=/;";
		}
	};
	
	t.getCookie = function(name)
	{
	  var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	  if(arr != null) return unescape(arr[2]); return null;
	};
	
	t.setCookie2 = function(name,value,days)
	{
		if(days){
	  	var exp  = new Date(); 
	  	exp.setTime(exp.getTime() + days*24*60*60*1000);
	  	document.cookie = name + "="+ encodeURIComponent(value) +";expires="+ exp.toGMTString()+";path=/;";
		}else{
			document.cookie = name + "="+ encodeURIComponent(value)+";path=/;";
		}
	};
	
	t.getCookie2 = function(name)
	{
	  var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	  if(arr != null) return decodeURIComponent(arr[2]); return null;
	};
	
	t.delCookie = function(name)
	{
	  var exp = new Date();
	  exp.setTime(exp.getTime() - 1);
	  var cval=getCookie(name);
	  if(cval!=null) document.cookie=name +"="+cval+";expires="+exp.toGMTString();
	};
	
	t.delCookie2 = function(name)
	{
	  var exp = new Date();
	  exp.setTime(exp.getTime() - 1);
	  var cval=getCookie2(name);
	  if(cval!=null) document.cookie=name +"="+cval+";expires="+exp.toGMTString();
	};
	
	return t;
})();

_jsc.jscload = (function(){
	var t = function(){
	    if(!_jsc.quickload){
    		var cs = $t('label');
    		for(var i=0;i<cs.length;i++){		
    			var ct = _jsc.util.ga(cs[i],'c_type');
    			if(ct){
    				var pe = _jsc.dom.gNxtSib(cs[i]);
    				if(pe){
    					eval("new "+ct+"(pe)");
    				}
    			}
    		}
		}
		if(typeof jsconload == 'function'){
			jsconload();
		}
		_jsc.loaded = true;
	};
	return t;
})();

_jsc.jscunload = (function(){
	var t = function(){
		if(true){
		    try{
				if(typeof jsconload == 'function'){
					jsconunload();
				}		                       		
            }catch(e){}
		}
	};
	return t;
})();

_jsc.init = (function(){
	_jsc.util.addEvent(window,'load',_jsc.jscload);
	_jsc.util.addEvent(window,'unload',_jsc.jscunload);
	return true;
})();

function Jsc(){
	this.chkPropName = function(s){
		if(s == 'c_type'){
			return false;
		}
		return true;
	};		
	
	this.getAttr = function(k){
		return eval("this."+k);
	};
	
	this.setAttr = function(k,v,n){
		if(!n){
			if(typeof v == "string"){
				eval("this."+k+" = \""+v+"\"");
			}else{
				eval("this."+k+" = "+v);
			}
		}
	};
	
	this.initBase = function(){
		_jsc.mgr.addC(this);
		for(var i = 0;i<this.doc.attributes.length;i++){
			var nn = this.doc.attributes[i].nodeName;
			if(nn.indexOf("c_") == 0){
				if(this.chkPropName(nn)){
					var nv = this.doc.attributes[i].nodeValue;
					eval("this."+nn+" = '"+nv+"'");
				}
			}
		}
	};
}

function JscStateElement(ele,dft,ukey){
	this.getState = null;//y
	this.setState = null;//y
	this.defaultState = dft;
	this.currentState = dft;
	this.ele = ele;
	this.eid = ele.id;
	this.must = true;	
	this.ischanged = false;
	this.check = false;//o
	this.urlKey = ukey;
}

function JscSet(){
	this.arr = [];
	this.idx = {};
	this.cp = -1;
	this.al = 0;
	this.add = function(oid,obj){
		this.arr.push([oid,obj]);
		this.cp++;
		this.idx[oid] = this.cp;
		this.al++;
	};
	this.del = function(oid){
		var add = this.idx[oid];
		delete this.arr[add];
		this.al--;
		delete this.idx[oid];
	};
		
	this.getFirst = function(){
		if(this.al<1)return null;
		for(var i=0;i<this.arr.length;i++){
			if(this.arr[i]){
				return this.arr[i][1];
			}
		}
		return null;
	};
}



// ---------------------------------------------------------------------------------------------- //




function $defined(obj){
	return (obj != undefined);
};

function $type(obj){
	if (!$defined(obj)) return false;
	if (obj.htmlElement) return 'element';
	var type = typeof obj;
	if (type == 'object' && obj.nodeName){
		switch(obj.nodeType){
			case 1: return 'element';
			case 3: return /\S/.test(obj.nodeValue) ? 'textnode' : 'whitespace';
		}
	}
	if (type == 'object' || type == 'function'){
		switch(obj.constructor){
			case Array: return 'array';
			case RegExp: return 'regexp';
			case Class: return 'class';
		}
		if (typeof obj.length == 'number'){
			if (obj.item) return 'collection';
			if (obj.callee) return 'arguments';
		}
	}
	return type;
};

var Class = function(properties){
	var klass = function(){
		return (arguments[0] !== null && this.initialize && $type(this.initialize) == 'function') ? this.initialize.apply(this, arguments) : this;
	};
	$extend(klass, this);
	klass.prototype = properties;
	klass.constructor = Class;
	return klass;
};

Class.empty = function(){};
Class.prototype = {
	extend: function(properties){
		var proto = new this(null);
		for (var property in properties){
			var pp = proto[property];
			proto[property] = Class.Merge(pp, properties[property]);
		}
		return new Class(proto);
	},

	implement: function(){
		for (var i = 0, l = arguments.length; i < l; i++) $extend(this.prototype, arguments[i]);
	}

};
Class.Merge = function(previous, current){
	if (previous && previous != current){
		var type = $type(current);
		if (type != $type(previous)) return current;
		switch(type){
			case 'function':
				var merged = function(){
					this.parent = arguments.callee.parent;
					return current.apply(this, arguments);
				};
				merged.parent = previous;
				return merged;
			case 'object': return $merge(previous, current);
		}
	}
	return current;
};

function $(obj){
	return $i(obj);
}

String.prototype.test= function(regex, params){
	return (($type(regex) == 'string') ? new RegExp(regex, params) : regex).test(this);
};

String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, '');
};

Array.prototype.forEach = function(fn, bind){
	for (var i = 0, j = this.length; i < j; i++) fn.call(bind, this[i], i, this);
};

Array.prototype.extend = function(array){
	for (var i = 0, j = array.length; i < j; i++) this.push(array[i]);
	return this;
};

Array.prototype.indexOf = function(item, from){
	var len = this.length;
	for (var i = (from < 0) ? Math.max(0, len + from) : from || 0; i < len; i++){
		if (this[i] === item) return i;
	}
	return -1;
};

Array.prototype.contains = function(item, from){
	return this.indexOf(item, from) != -1;
};

Array.prototype.each = Array.prototype.forEach;
Array.prototype.test = Array.prototype.contains;
document.getElementsBySelector = document.getElementsByTagName;

var $extend = Object.extend = function(){
	var args = arguments;
	if (!args[1]) args = [this, args[0]];
	for (var property in args[1]) args[0][property] = args[1][property];
	return args[0];
};

function $$(par, tag){
	var elements = [];
	var container = $(par);
	elements = container.getElementsByTagName(tag, true);
	return elements;
}

// 表单验证
// by nowa 2007-04-30
// last updated: nowa 2007-08-13
var Validator = new Class({

	// 提示信息
	msgs: {},

	// 需要验证的input类型
	types: [],

	// 不需要验证必填的elements
	blank_check_excepts: [],

	valid_r: true,

	input_elements: [],

	depends: {},

	// 初始化
	initialize: function(f, c, m, t, be, d){
		this.c = c;
		this.msgs = m;
		this.types = t;
		this.blank_check_excepts = be;
		this.form = $(f);
		this.container = $(c);
		this.depends = d;
		var _this = this;

		var inputs = this.container.getElementsByTagName('input');
		var elements = [];
		for (i=0; i < inputs.length; i++){
			elements.push(inputs[i]);
		}
		this.input_elements = elements;

		elements.each(function(input) {
			if (_this.types.test(input.type)) {
				var _msg = $(input.id + '_inf');
				// 获得焦点
				_jsc.util.addEvent(input, 'focus', function() {
					_this.setActiveStyle(input);
				});
				// 失去焦点
				_jsc.util.addEvent(input, 'blur', function() {
					if (input.value == '' && !_this.blank_check_excepts.test(input.id)) {
						_this.setBlankFailedStyle(input);
					} else{
						_this.clearStyle(input);
						try {
							eval('_this.valid_' + input.id + '(input)');
						} catch(e) {
							// to-do
						}
					};
				})
			};
		});

		this.setFormOnSubmit();
	},

	valid_input: function(o){
		if (o.value.trim() == '') {
			this.setFailedStyle(o, false);
		};
	},

	setFormOnSubmit: function(){
		var _this = this;
		this.form.onsubmit = function() {
			_this.valid_r = true;
			_this.input_elements.each(function(input) {
				if (_this.types.test(input.type)) {
					try {
						eval('_this.valid_' + input.id + '(input)');
					} catch(e) {
						//_this.valid_r = false;
					}

					if(_this.valid_r || _this.valid_r == 1){
						try {
							$('li_' + input.id).style.display = "none";
						}catch(e){
							// to-do
						}
					}else{
						try{
							eval('$("li_' + _this.depends[input.id] + '").style.display="block"');
						}catch(e){

						}
					}
				};
			});
			return (_this.valid_r == 1) ? true : false;
		};
	},

	setActiveStyle: function(o){
		o.style.border = '1px solid #666';
		var _msg = $(o.id + '_inf');
		_msg.className = 'WarningMsg';
		_msg.innerHTML = (this.msgs[o.id]) ? this.msgs[o.id] : '';
	},

	setFailedStyle: function(o, _ajax){
		o.style.border = '1px solid red';
		var _msg = $(o.id + '_inf');
		_msg.className = 'FailedMsg';
		if (_ajax) {
			_msg.innerHTML = this.msgs[o.id + '_ajax_invalid'];
		} else{
			if(o.value.trim()==''){
				_msg.innerHTML = (this.msgs[o.id + '_invalid']) ? this.msgs[o.id + '_invalid'] : ((this.msgs[o.id]) ? this.msgs[o.id] : '');
			}
			else{
				_msg.innerHTML = (this.msgs[o.id + '_invalid_format']) ? this.msgs[o.id + '_invalid_format'] : ((this.msgs[o.id]) ? this.msgs[o.id] : '');
			}
		};
	},

	setBlankFailedStyle: function(o){
		o.style.border = '1px solid red';
		var _msg = $(o.id + '_inf');
		_msg.className = 'FailedMsg';
		_msg.innerHTML = this.msgs[o.id + '_invalid'] ? this.msgs[o.id + '_invalid'] : this.msgs[o.id];
	},

	setSucceedStyle: function(o, _ajax){
		o.style.border = '1px solid green';
		var _msg = $(o.id + '_inf');
		_msg.className = 'SucceedMsg';
		if (_ajax) {
			_msg.innerHTML = this.msgs[o.id + '_ajax_valid'];
		} else{
			_msg.innerHTML = (this.msgs[o.id + '_valid']) ? this.msgs[o.id + '_valid'] : '';
		};
	},

	clearStyle: function(o){
		o.style.border = '1px solid #A7A6AA';
		var _msg = $(o.id + '_inf');
		_msg.className = 'default';
	}

});
