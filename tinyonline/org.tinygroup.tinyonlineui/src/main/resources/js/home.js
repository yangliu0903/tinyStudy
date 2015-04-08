//author:cying

$(document).ready(function(){
    
     var g_obj;  //缓存章节详情信息

     //直接先进行章节目录的请求
     getAllChapters();
     //章节选中事件
	 $("#chapter").change(function(){

		 //获取章节号
		 var chNum=$("#chapter").val();
            if(chNum==0){  //如果选中的是"请选择"选项，删掉这个请选择选项
                 $("#chapter option[value='0']").remove();
                 return false;
            }
		  $.ajax({
                    type: "post",
                    url: "${TINY_CONTEXT_PATH}/queryItemByChapId.servicejson",
                    data:"chapId="+chNum,    // data: "name=" + $("#Text1").val(),
                    success: function (result) {
                        // alert(result);
                     //将json字符串转化为对象
                      g_obj= $.parseJSON(result);
		             //列出所有itemName条目
		             listItem(result);
                     //设置第一个item数据到textarea
                     $("#example_id").val(g_obj[0].example);
                     $("#explanation_id").html(g_obj[0].explanation);
                    }
                });
	 });

    //item条目的选中事件,用click事件而不用change
    $("#item").click(function(){
          var selectValue=$("#item").val();    //获取选中的option的值
          $("#example_id").val(g_obj[selectValue].example);    //更改div的内容
          $("#explanation_id").html(g_obj[selectValue].explanation);
    	});

    //列出所有的item
    function listItem(result){
     //清除已经有的内容
     $("#item").empty();

     //遍历数组 设置新的内容
     $.each(g_obj, function(i, value) {
     $("#item").append("<option value=" +i+ ">" + value.itemName+"</option>");  //添加一项option
      });
    }
    //发送请求获取所有的章节
    function getAllChapters(){
    	$.ajax({
                    type: "post",
                    url: "${TINY_CONTEXT_PATH}/queryChaptersTiny.servicejson?@beantype=Chapter",
                    success: function (result) {
                        // alert("正在请求章节");
                        // alert(result);
                     //将json字符串转化为对象
                      var obj= $.parseJSON(result);
		             //列出所有itemName条目
		             listChapter(obj);
                    }
                });
    }
    //把请求的信息显示出来
    function listChapter(chap){
         $.each(chap, function(i, value) {
     $("#chapter").append("<option value=" +value.chapId+ ">" + value.chapName+"</option>");  //添加一项option
      });
    }
	
   //响应重置按钮，重置textarea里面的内容
   $("#reset_id").click(function(){
      $("#example_id").val("");
   });

});
