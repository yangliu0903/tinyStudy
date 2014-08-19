
//Global vars & functions
var $w = window;
var $d = document;
var $l = location;
function $i(s){return $d.getElementById(s);}
function $t(s){return $d.getElementsByTagName(s);}
function $n(s){return $d.getElementsByName(s);}

var _jsc = {};
_jsc.client = (function(){	
	var t = {};
	var b = navigator.userAgent.toLowerCase();
	t.isOpera = (b.indexOf('opera') > -1);
	t.isIE = (!t.isOpera && b.indexOf('msie') > -1);
	t.isFF = (!t.isOpera &&!t.isIE&&b.indexOf('firefox') > -1);
	return t;
})();

function getWidth(){
	//if (this.webkit419) return this.innerWidth;
	//if (this.opera) return document.body.clientWidth;
	return document.documentElement.clientWidth;
}

function getHeight(){
	//if (this.webkit419) return this.innerHeight;
	//if (this.opera) return document.body.clientHeight;
	return document.documentElement.clientHeight;
}

function getScrollHeight(){
	if (this.ie) return Math.max(document.documentElement.offsetHeight, document.documentElement.scrollHeight);
	//if (this.webkit) return document.body.scrollHeight;
	return document.documentElement.scrollHeight;
}

function getViewportWidth(){
    var width = self.innerWidth;  // Safari
    var mode = document.compatMode;
    
    if (mode || _jsc.client.isIE) { // IE, Gecko, Opera
        width = (mode == 'CSS1Compat') ?
                document.documentElement.clientWidth : // Standards
                document.body.clientWidth; // Quirks
    }
    return width;
}

function getViewportHeight() {
    var height = self.innerHeight; // Safari, Opera
    var mode = document.compatMode;

    if ( (mode || _jsc.client.isIE) && !_jsc.client.isOpera ) { // IE, Gecko
        height = (mode == 'CSS1Compat') ?
                document.documentElement.clientHeight : // Standards
                document.body.clientHeight; // Quirks
    }
    return height;
}

_jsc.widget = {};
_jsc.widget.lightbox = function(){
	this.show = function(o, t){
		this.render_shadow();
		var box = o;
		
		box.style.left = document.body.clientWidth/2-box.offsetWidth/2+'px';
		box.style.top = document.documentElement.scrollTop + getViewportHeight()/2-box.offsetHeight/2 +'px';
		box.style.visibility='visible';
		var lb = document.getElementById('lightbox');
		var as = lb.getElementsByTagName('a');
		if(as != null && as.length > 0)
		{
			as[0].focus();
			for(var i=0;i<as.length;i++){
				as[i].onfocus = "this.blur();";
			}
		}
			
	};
	
	this.render_shadow = function(){
		if($i('shadow')!='object'){
		   
		   var ShadowDiv = document.createElement('div');
		   ShadowDiv.id = 'shadow';
		   var els=document.getElementsByTagName( "select"); 
     	   for(var i=0;i <els.length;i++){ 
              els[i].style.display= "none"; 
      	   }
		   document.body.appendChild(ShadowDiv);
		   
		}
		//ShadowDiv.style.width = getWidth()+'px';
		ShadowDiv.style.width = '100%';
		ShadowDiv.style.height = getScrollHeight()+'px';
	};
	
	this.hide = function(){
		var els=document.getElementsByTagName( "select"); 
     	for(var i=0;i <els.length;i++){ 
           els[i].style.display= ""; 
      	}
		if($i('shadow')!= false ) document.body.removeChild($i('shadow'));
		document.body.removeChild($i('lightbox'));
	};
}

var lightbox = new _jsc.widget.lightbox();

function confirmbox(url,oper){
		var _inner ='';
		var remind='您确认要'+oper+'吗？';
		if(oper == '' || typeof(oper)=='undefined')
		{
			remind = '您确认要此项操作吗？';
		}
		_inner += '<div class="comfirm_box"><i></i><h2>'+remind+'</h2>';
		_inner += ' <div class="button_area"><a onclick="lightbox.hide();return true;" href="'+url+'" class="button_grey"><span>确定</span></a> <a onclick="lightbox.hide();return false;" href="#" class="button_grey"><span>取消</span></a></div>';
		var lbox = document.createElement('div');
		lbox.id = 'lightbox';
		lbox.style.width='450px';
		lbox.innerHTML = _inner;
		document.body.appendChild(lbox);
		lightbox.show(lbox);	
}

function confirmsave(fromId,oper){
		var _inner ='';
		var remind='您确认要'+oper+'吗？';
		if(oper == '' || typeof(oper)=='undefined')
		{
			remind = '您确认要此项操作吗？';
		}
		_inner += '<div class="comfirm_box"><i></i><h2>'+remind+'</h2>';
		_inner += ' <div class="button_area"><a onclick="document.getElementById(\'' + fromId + '\').submit();lightbox.hide();return true;" class="button_grey"><span>确定</span></a> <a onclick="lightbox.hide();return false;" href="#" class="button_grey"><span>取消</span></a></div>';
		var lbox = document.createElement('div');
		lbox.id = 'lightbox';
		lbox.style.width='450px';
		lbox.innerHTML = _inner;
		document.body.appendChild(lbox);
		lightbox.show(lbox);	
}
function loading(fromId,url){
		var _inner ='';
		var remind='请稍候...';
		_inner += '<div class="loading_box"><i></i><h2>'+remind+'</h2>';
		var lbox = document.createElement('div');
		lbox.id = 'lightbox';
		lbox.style.width='100px';
		lbox.innerHTML = _inner;
		document.body.appendChild(lbox);
		lightbox.show(lbox);
		if(url){
			setTimeout("window.location.href='"+ url +"';",3000);
			return;	
		}else setTimeout("document.getElementById(fromId).submit()",3000);
		
}


//add by wuyb
function loadingReq(message,fromId,url){
		var _inner ='';
		var remind=message;
		_inner += '<div class="loading_box"><i></i><h2>'+remind+'</h2>';
		var lbox = document.createElement('div');
		lbox.id = 'lightbox';
		lbox.style.width='300px';
		lbox.innerHTML = _inner;
		document.body.appendChild(lbox);
		lightbox.show(lbox);
		if(url){
			setTimeout("window.location.href='"+ url +"';",2000);
			setTimeout("lightbox.hide();",2000);
			return;	
		}else setTimeout("document.getElementById(fromId).submit()",2000);
}