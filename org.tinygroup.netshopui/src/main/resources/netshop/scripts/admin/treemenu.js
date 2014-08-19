/* -------------------------- 
 应用项目  ： 软件工程 管理平台
  
 测试浏览器： IE5 / IE6 / IE7       
            Mozilla FireFox 1.5 / 2.0
			0pera 9.51
 函数功能：菜单数的展开与闭合、菜单页面的隐藏与显示
 字符集  ： UTF8
 编写    ： Frankyli @ HG  
 EMail   : lixq@hundsun.com  
 -------------------------- */


function Ob(o){
 var o=document.getElementById(o)?document.getElementById(o):o;
 return o;
}
function Hd(o) {
// Ob(o).style.display="none";
}
function Sw(o) {
// Ob(o).style.display="";
}
function ExCls(o,a,b,n){
 var o=Ob(o);
 for(i=0;i<n;i++) {o=o.parentNode;}
 o.className=o.className==a?b:a;
}
function TreeMenu(id,TagName0) {
	//autoWidth();
  this.id=id;
  this.TagName0=TagName0==""?"li":TagName0;
  this.AllNodes = Ob(this.id).getElementsByTagName(TagName0);
  this.InitCss = function (ClassName0,ClassName1,ClassName2,ImgUrl) {
  this.ClassName0=ClassName0;
  this.ClassName1=ClassName1;
  this.ClassName2=ClassName2;
  this.ImgUrl=ImgUrl || "images/icn_blank.gif";
  this.ImgBlankA ="<img src=\""+this.ImgUrl+"\" class=\"s\" onclick=\"ExCls(this,'"+ClassName0+"','"+ClassName1+"',1);\" alt=\"icon\" />";
  this.ImgBlankB ="<img src=\""+this.ImgUrl+"\" class=\"s\" />";
  for (i=0;i<this.AllNodes.length;i++ ) {
   this.AllNodes[i].className==""?this.AllNodes[i].className=ClassName1:"";
   this.AllNodes[i].innerHTML=(this.AllNodes[i].className==ClassName2?this.ImgBlankB:this.ImgBlankA)+this.AllNodes[i].innerHTML;
   }
 }
 this.SetNodes = function (n) {
  var sClsName=n==0?this.ClassName0:this.ClassName1;
  for (i=0;i<this.AllNodes.length;i++ ) {
   this.AllNodes[i].className==this.ClassName2?"":this.AllNodes[i].className=sClsName;
  }
 }
}
//function autoWidth(){       //修正IE7下显示问题
	//	document.getElementById("AllOpen_3").style.width='180px';
	//	document.getElementById("AllClose_3").style.width='180px';
//}


var flag = false;
var open_close = false;

//隐藏菜单功能
function shift_status(button)
{
	//top.document.getElementsByTagName("frameset")[0].rows="0,*";
	var menu = self.parent.frames["content"].document.getElementsByName('menu')[0]; 
	var main = self.parent.frames["content"].document.getElementsByName('main')[0]; 
	if(menu.width == 191){
		menu.width = 1;
		main.width = Number(main.width) + Number(191);
		button.value="显示菜单";
		button.title="显示菜单";
	}
	else{
		menu.width = 191;
		main.width = Number(main.width) - Number(191);
		button.value="隐藏菜单";
		button.title="隐藏菜单";
	}
}

function menu_tree(button){
	if(!open_close){
		var op = self.parent.frames["content"].frames["menu"].document.getElementById('AllOpen_3');
		op.onclick();
		button.value="全部折叠";
		button.title="全部折叠";
		open_close = true;
	}else{
		var cl = self.parent.frames["content"].frames["menu"].document.getElementById('AllClose_3');
		cl.onclick();
		button.value="全部展开";
		button.title="全部展开";
		open_close = false;
	}
}