/* 横向滚动 */
$(function(){
	var page = 1;
	var i = 3; //每版放3个图片
	var len = $(".w_shop_a .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".w_shop_a .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".w_shop_a .prolist_content"); 
	//向右 按钮
	$(".w_shop_a .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 800); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 800);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".w_shop_a .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 800); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 800);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
});

$(function(){
	var page = 1;
	var i = 3; //每版放3个图片
	var len = $(".w_shop_b .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".w_shop_b .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".w_shop_b .prolist_content"); 
	//向右 按钮
	$(".w_shop_b .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 800); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 800);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".w_shop_b .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 800); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 800);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
});

$(function(){
	var page = 1;
	var i = 3; //每版放3个图片
	var len = $(".w_shop_c .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".w_shop_b .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".w_shop_c .prolist_content"); 
	//向右 按钮
	$(".w_shop_c .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 800); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 800);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".w_shop_c .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 800); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 800);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
});

$(function(){
	var page = 1;
	var i = 3; //每版放3个图片
	var len = $(".w_shop_d .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".w_shop_b .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".w_shop_d .prolist_content"); 
	//向右 按钮
	$(".w_shop_d .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 800); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 800);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".w_shop_d .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 800); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 800);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
});


$(function(){
	var page = 1;
	var i = 3; //每版放3个图片
	var len = $(".o_hotProducts .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".o_hotProducts .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".o_hotProducts .prolist_content"); 
	//向右 按钮
	$(".o_hotProducts .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 800); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 800);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".o_hotProducts .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 800); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 800);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
});



$(function(){
	var page = 1;
	var i = 6; //每版放6个图片
	var len = $(".goods-tui .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".goods-tui .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".goods-tui .prolist_content"); 
	//向右 按钮
	$(".goods-tui .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 670); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 670);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".goods-tui .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 670); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 670);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
	window.setInterval("$('.goods-tui .goRight').click();", 5000);
});
$(function(){
	var page = 1;
	var i = 6; //每版放6个图片
	var len = $(".goods-cai .prolist_content ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(".goods-cai .prolist").width(); //获取框架内容的宽度,不带单位
	var $parent = $(".goods-cai .prolist_content"); 
	//向右 按钮
	$(".goods-cai .goRight").click(function(){ 
		if( !$parent.is(":animated") ){
			if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$parent.animate({ left : 0}, 670); //通过改变left值，跳转到第一个版面
				page = 1;
			}else{
				$parent.animate({ left : '-='+none_unit_width}, 670);  //通过改变left值，达到每次换一个版面
				page++;
			}
		}
	});
	//往左 按钮
	$(".goods-cai .goLeft").click(function(){
		if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$parent.animate({ left : '-='+none_unit_width*(page_count-1)}, 670); //通过改变left值，跳转到最后一个版面
				page = page_count;
			}else{
				$parent.animate({ left : '+='+none_unit_width }, 670);  //通过改变left值，达到每次换一个版面
				page--;
			}
		}
	});
	
	window.setInterval("$('.goods-cai .goRight').click();", 5000);
});