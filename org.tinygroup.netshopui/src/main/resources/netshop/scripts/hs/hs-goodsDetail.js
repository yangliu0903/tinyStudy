/*var prefix = path.split("_s.");
var newPath = prefix[0]+"_l."+prefix[1];*/
   function isUnsignedInteger(s) {
		return (s.toString().search(/^[0-9]+$/) == 0);
	}
   function addGoodsToShoppingCart(){
            var goodsNum=document.getElementById("goodsNum").value;	
            var goodsNumsc=document.getElementById("goodsNumsc").value;	
            var saleNumBegin=document.getElementById("saleNumBegin").value;	
        	if(parseInt(hs_goodsNum)<1){
        		alert('商品的库存数量不足，您现在不能加入购物车！') ;
        	}else if(!isUnsignedInteger(goodsNum)||parseInt(goodsNum)<1){
            	alert('商品的数量必须是整数！并且商品的数量必须大于0！') ;
            }else if(parseInt(saleNumBegin)>parseInt(goodsNum)){
            	alert('商品的购买数量不能小于起售数量！') ;
            }else if(parseInt(goodsNum)>parseInt(hs_goodsNum)){
            	alert('商品的购买数量不能大于库存数量！') ;
            }
            else{
                document.getElementById("goodsNum").value=parseInt(goodsNum);
                goodsNum=parseInt(goodsNum);
				var goodsAttrIds = new Array(); 
				for(i=1;i<20;i++){
					var ids=document.getElementsByName("goodsAttr_"+i);
					if(null==ids){break;}
					var noSelect= true;
					for(var j=0;j<ids.length;j++){
						 if(ids[j].checked==true){
							goodsAttrIds[i-1]=ids[j].value;
							noSelect=false;
							break;
						 }
					}
					if(noSelect==true){
						var name=document.getElementById("goodsAttrName_"+i);
						if(null==name){break;}
						var alertString="请选择"+name.value;
						alert(alertString);
						return;
					}
				}
				goodsAttrIds.sort();
            	location.href='add.html?goodsId='+hs_goodsId+'&goodsNum='+goodsNum+'&goodsAttrIds='+goodsAttrIds;
            }
    }
                
                    
                    function checkFeedback(){
                    var msgType = document.getElementsByName("feedback.msgType");
                    var check=0;
                    for(var i=0;i<msgType.length;i++){
                        if(msgType[i].checked){
                        check=1;
                        }
                        }
                        if( check == 0){
                        alert("请选择留言类型!");
                        return false;
                        }
                        var msgTitle=document.getElementById("msgTitle");
                        var ct=msgTitle.value;
                        var s=ct.trim();
                        if(s==null||s.length==0){
                        alert("请输入主题!");
                        return false;
                        }
                        if(s.length>30){
                        alert("您输入的主题内容过长，超出字数限制!");
                        return false;
                        }
                        var msgContent=document.getElementById("msgContent");
                        var ctv=msgContent.value;
                        var ss=ctv.trim();
                        if(ss==null||ss.length==0){
                        alert("请输入留言内容!");
                        return false;
                        }
                        if(ss.length>800){
                        alert("您输入的留言内容过长，超出字数限制!");
                        return false;
                        }
                        document.getElementById("saveFeedback").submit();
                        }

                    
                    function addBunglingGoodsToShoppingCart(){
                        var goodsNum=document.getElementById("goodsNum").value;	
                        var goodsNumsc=document.getElementById("goodsNumsc").value;	
                        var saleNumBegin=document.getElementById("saleNumBegin").value;	
                    	if(parseInt(hs_goodsNum)<1){
                    		alert('商品的库存数量不足，您现在不能加入购物车！') ;
                    	}else if(parseInt(saleNumBegin)>parseInt(hs_goodsNum)){
                        	alert('商品的库存数量不足，您现在不能加入购物车！') ;
                        }
                        else{
                            document.getElementById("goodsNum").value=parseInt(goodsNum);
                            goodsNum=parseInt(saleNumBegin);
            				var goodsAttrIds = new Array(); 
            				for(i=1;i<20;i++){
            					var ids=document.getElementsByName("goodsAttr_"+i);
            					if(null==ids){break;}
            					var noSelect= true;
            					for(var j=0;j<ids.length;j++){
            						 if(ids[j].checked==true){
            							goodsAttrIds[i-1]=ids[j].value;
            							noSelect=false;
            							break;
            						 }
            					}
            					if(noSelect==true){
            						var name=document.getElementById("goodsAttrName_"+i);
            						if(null==name){break;}
            						var alertString="请选择"+name.value;
            						alert(alertString);
            						return;
            					}
            				}
            				goodsAttrIds.sort();
                        	location.href='add.html?goodsId='+hs_goodsId+'&goodsNum='+goodsNum+'&goodsAttrIds='+goodsAttrIds+'&bundlingTag=yes';
                        }
                }