/**
 * jQuery :  城市联动插件
 * @author   XiaoDong <cssrain@gmail.com>
 *			 http://www.cssrain.cn
 * @example  $("#test").ProvinceCity();
 * @params   暂无
 */
//分别获取3个下拉框
	var $sel1;
	var $sel2;
	var $sel3;
	var _self;
$.fn.ProvinceCity = function(){
	_self = this;
	//定义3个默认值
	_self.data("province",["省（市）", ""]);
	_self.data("city1",["市（区）", ""]);
	_self.data("city2",["网点名称", ""]);
	//插入3个空的下拉框
	_self.append("<select id='mysanprovince' name='sanprovince' onchange='mySheng(this)'></select>");
	_self.append("<select id='mysancity' name='sancity' onchange='myCity()'></select>");
	_self.append("<select id='mysanarea' name='sanarea' style='margin-left:2px;width:250px'></select>");
	//分别获取3个下拉框
	$sel1 = _self.find("select").eq(0);
	$sel2 = _self.find("select").eq(1);
	$sel3 = _self.find("select").eq(2);
	//默认省级下拉
	if(_self.data("province")){
		$sel1.append("<option value='"+_self.data("province")[1]+"'>"+_self.data("province")[0]+"</option>");
	}
	$.each( GP , function(index,data){
		$sel1.append("<option value='"+data+"'>"+data+"</option>");
	});
	//默认的1级城市下拉
	if(_self.data("city1")){
		$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
	}
	//默认的2级城市下拉
	if(_self.data("city2")){
		$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
	}

	return _self;
};

var index1 = "" ;
function mySheng(){
	//省级联动 控制
	//清空其它2个下拉框
	$sel1 = $("#mysanprovince");
	$sel2 = $("#mysancity");
	$sel3 = $("#mysanarea");
	$sel2[0].options.length=0;
	$sel3[0].options.length=0;
	index1 = $sel1[0].selectedIndex;
	if(index1==0){	//当选择的为 “请选择” 时

		$sel2.append("<option value=''>市（区）</option>");


		$sel3.append("<option value=''>网点名称</option>");

	}else{
		$.each( GT[index1-1] , function(index,data){
			$sel2.append("<option value='"+data+"'>"+data+"</option>");
		});
		$.each( GC[index1-1][0] , function(index,data){
			$sel3.append("<option value='"+data+"'>"+data+"</option>");
		});
	}


}

var index2 = "" ;
function myCity(){

	//1级城市联动 控制
	$sel3[0].options.length=0;
	index2 = $sel2[0].selectedIndex;
	$.each( GC[index1-1][index2] , function(index,data){
		$sel3.append("<option value='"+data+"'>"+data+"</option>");
	});
}