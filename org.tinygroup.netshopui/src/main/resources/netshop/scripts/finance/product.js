jQuery(function(){
    jQuery("#fundBuy").dialog({
    			autoOpen: false,
    			height: 300,
    			width: 450,
    			modal: true,
    			buttons: {
    				"基金转换": function() {
    					var oFundCode = jQuery("#oFundCode").val();
    					getProductTrans(oFundCode);
    					jQuery("#fundTransfer").dialog("open");
    				},
					"分红选择": function() {
						jQuery("#bonusChoose").dialog("open");
    				},
    				"关闭": function() {
    					jQuery( this ).dialog( "close" );
    				}
    			}
    			});
	jQuery("#fundPlan").dialog({
    			autoOpen: false,
    			height: 350,
    			width: 550,
    			modal: true,
    			buttons: {
    				"基金转换": function() {
    					var oFundCode = jQuery("#oFundCode").val();
    					getProductTrans(oFundCode);
    					jQuery("#fundTransfer").dialog("open");
    				},
					"分红选择": function() {
						jQuery("#bonusChoose").dialog("open");
    				},
    				"关闭": function() {
    					jQuery( this ).dialog( "close" );
    				}
    			}
    			});
	jQuery("#fundTransfer").dialog({
    			autoOpen: false,
    			height: 300,
    			width: 350,
    			modal: true,
    			buttons: {
    				"确定": function() {
    					var oFundCode = jQuery("#oFundCode").val();
    					var oFundName = jQuery("#oFundName").val();
    					var iFundCode = jQuery("#iFundCode").val();
    					//var iFundName = jQuery("#iFundName").val();
    					var totalAmount = jQuery("#totalAmount").val();
    					if (iFundCode == "") {
    						alert("请选择目标基金！");
    						return false;
    					}
    					fundTransfer(oFundCode, oFundName, iFundCode, totalAmount);
    				},
    				"关闭": function() {
    					jQuery( this ).dialog( "close" );
    				}
    			}
    			});
	jQuery("#bonusChoose").dialog({
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"确定": function() {
				var opType = jQuery("#opType").val();
				var fundCode = jQuery("#oFundCode").val();
				var totalAmount = jQuery("#totalAmount").val();
				if (opType == "") {
					alert("请选择分红模式");
					return false;
				}
				bonusChoose(opType, fundCode, totalAmount);
			},
			"关闭": function() {
				jQuery( this ).dialog( "close" );
			}
		}
		});
	   
		jQuery("#redeemProduct").dialog({
    			autoOpen: false,
    			width: 500,
    			modal: true,
    			buttons: {
    				"确定": function() {
    					var amount = jQuery("#redeemAmount").val();
    					var productCode = jQuery("#productCode").val();
    					if(amount=='' ||amount==null|| jQuery.trim(amount)==''){
    					   alert("请输入赎回份额");
    					   return false;
    					}
    					var reg=/^\d+(\.\d{0,2})?$/
                        if(!reg.exec(amount)){
                           alert("赎回份额只允许输入2位小数");
    					   return false;
                        }
    					
    					var availableTotal = jQuery("#availableTotal").html();
    					if(parseFloat(amount)>parseFloat(availableTotal)){
    					   alert("赎回份额不能大于可用份数");
    					   return false;
    					}
    					if(parseFloat(amount)<=0){
    					   alert("赎回份额不能小于等于0");
    					   return false;
    					}
    					var orderId = jQuery("#orderId").val();
    					doRedeem(productCode, amount,orderId);
    				},
    				"关闭": function() {
    					jQuery( this ).dialog( "close" );
    				}
    			}
    	});
    })
    
function changeProductType(id) {
	var form = document.getElementById("productForm");
	if (id == 1) {
		jQuery("#productType").attr("value", "1");
		jQuery("#tradeApplyType").attr("value", "1");
		//查询基金
		form.submit();  
	} else if (id == 0) {
		jQuery("#productType").attr("value", "0");
		jQuery("#tradeApplyType").attr("value", "");
		//查询理财
		form.submit();  
	} else {
		return false;
	}
}
function changeBuyType(id) {
	var form = document.getElementById("productForm");
	if (id == 1) {
		//普通购买
		jQuery("#productType").attr("value", "1");
		jQuery("#tradeApplyType").attr("value", "1");
		form.submit();
	} else if (id == 0) {
		//定投
		jQuery("#productType").attr("value", "1");
		jQuery("#tradeApplyType").attr("value", "3");
		form.submit();
	} else {
		return false;
	}
}
function fundBuyDetail(id) {
	jQuery( "#fundBuy" ).dialog("open");
	var fundCode = jQuery("#fundCode_"+id).val();
	var fundName = jQuery("#fundName_"+id).val();
	var availableVol = jQuery("#availableVol_"+id).val();
	var frozeVol = jQuery("#frozeVol_"+id).val();
	var nav = jQuery("#nav_"+id).val();
	var applicationVol = jQuery("#applicationVol_"+id).val();
	var minredemptionVol = jQuery("#minredemptionVol_"+id).val();
	var buyinCost = jQuery("#buyinCost_"+id).val();
	var holdCost = jQuery("#holdCost_"+id).val();
	var fundStatus = jQuery("#fundStatus_"+id).val();
	var totalAmount = jQuery("#totalAmount_"+id).val();
	var totalNo = jQuery("#totalNo_"+id).val();
	jQuery("#fundCodeTrans").empty();
	jQuery("#fundNameTrans").empty();
	jQuery("#fundCodeTrans").append(fundCode);
	jQuery("#fundNameTrans").append(fundName);
	
	jQuery("#oFundCode").attr("value", fundCode);
	jQuery("#oFundName").attr("value", fundName);
	jQuery("#totalAmount").attr("value", totalAmount);
	
	jQuery("#fundCodeBonus").empty();
	jQuery("#fundNameBonus").empty();
	jQuery("#fundCodeBonus").append(fundCode);
	jQuery("#fundNameBonus").append(fundName);
	
	jQuery("#fundCodeValue").empty();
	jQuery("#fundNameValue").empty();
	jQuery("#availableVolValue").empty();
	jQuery("#frozeVolValue").empty();
	jQuery("#navValue").empty();
	jQuery("#applicationVolValue").empty();
	jQuery("#minredemptionVolValue").empty();
	jQuery("#buyinCostValue").empty();
	jQuery("#holdCostValue").empty();
	jQuery("#fundStatusValue").empty();
	jQuery("#totalAmountValue").empty();
	jQuery("#totalNoValue").empty();
	jQuery("#fundCodeValue").append(fundCode);
	jQuery("#fundNameValue").append(fundName);
	jQuery("#availableVolValue").append(availableVol);
	jQuery("#frozeVolValue").append(frozeVol);
	jQuery("#navValue").append(nav);
	jQuery("#applicationVolValue").append(applicationVol);
	jQuery("#minredemptionVolValue").append(minredemptionVol);
	jQuery("#buyinCostValue").append(buyinCost);
	jQuery("#holdCostValue").append(holdCost);
	jQuery("#fundStatusValue").append(fundStatus);
	jQuery("#totalAmountValue").append(totalAmount);
	jQuery("#totalNoValue").append(totalNo);
}

function fundPlanDetail(id) {
	jQuery( "#fundPlan" ).dialog("open");
	var fundCode2 = jQuery("#fundCode2_"+id).val();
	var fundName2 = jQuery("#fundName2_"+id).val();
	var investMode = jQuery("#investMode_"+id).val();
	var firstAmount = jQuery("#firstAmount_"+id).val();
	var continueAmount = jQuery("#continueAmount_"+id).val();
	var investPeriods = jQuery("#investPeriods_"+id).val();
	var investPeriodsValue = jQuery("#investPeriodsValue_"+id).val();
	var firstTransDate = jQuery("#firstTransDate_"+id).val();
	var investTime = jQuery("#investTime_"+id).val();
	var investTimeValue = jQuery("#investTimeValue_"+id).val();
	var totalExecuteTimes = jQuery("#totalExecuteTimes_"+id).val();
	var totalFailTimes = jQuery("#totalFailTimes_"+id).val();
	var totalAmount2 = jQuery("#totalAmount2_"+id).val();
	var continueEFailTimes = jQuery("#continueEFailTimes_"+id).val();
	var status2 = jQuery("#status2_"+id).val();
	jQuery("#fundCodeTrans").empty();
	jQuery("#fundNameTrans").empty();
	jQuery("#fundCodeTrans").append(fundCode2);
	jQuery("#fundNameTrans").append(fundName2);

	jQuery("#oFundCode").attr("value", fundCode2);
	jQuery("#oFundName").attr("value", fundName2);
	jQuery("#totalAmount").attr("value", totalAmount2);
	
	jQuery("#fundCodeBonus").empty();
	jQuery("#fundNameBonus").empty();
	jQuery("#fundCodeBonus").append(fundCode2);
	jQuery("#fundNameBonus").append(fundName2);
	
	jQuery("#fundCode2Value").empty();
	jQuery("#fundName2Value").empty();
	jQuery("#investModeValue").empty();
	jQuery("#firstAmountValue").empty();
	jQuery("#continueAmountValue").empty();
	jQuery("#investPeriodsValue").empty();
	//jQuery("#investPeriodsValueValue").empty();
	jQuery("#firstTransDateValue").empty();
	jQuery("#investTimeValue").empty();
	jQuery("#investTimeValueValue").empty();
	jQuery("#totalExecuteTimesValue").empty();
	jQuery("#totalFailTimesValue").empty();
	jQuery("#totalAmount2Value").empty();
	jQuery("#continueEFailTimesValue").empty();
	jQuery("#status2Value").empty();
	
	jQuery("#fundCode2Value").append(fundCode2);
	jQuery("#fundName2Value").append(fundName2);
	jQuery("#investModeValue").append(investMode);
	jQuery("#firstAmountValue").append(firstAmount);
	jQuery("#continueAmountValue").append(continueAmount);
	jQuery("#investPeriodsValue").append(investPeriodsValue+" ").append(investPeriods);
	//jQuery("#investPeriodsValueValue").append(investPeriodsValue);
	jQuery("#firstTransDateValue").append(firstTransDate);
	jQuery("#investTimeValue").append(investTime);
	jQuery("#investTimeValueValue").append(investTimeValue);
	jQuery("#totalExecuteTimesValue").append(totalExecuteTimes);
	jQuery("#totalFailTimesValue").append(totalFailTimes);
	jQuery("#totalAmount2Value").append(totalAmount2);
	jQuery("#continueEFailTimesValue").append(continueEFailTimes);
	jQuery("#status2Value").append(status2);
}

//DWR方式基金转换
function fundTransfer(oFundCode, oFundName, iFundCode, totalAmount) {
	ProductAction.fundTransfer(oFundCode, oFundName, iFundCode, totalAmount,
			function(msg){callBackClose(msg);});
}

//DWR方式基金分红选择
function bonusChoose(opType, fundCode, totalAmount) {
	ProductAction.bonusChoose(opType, fundCode, totalAmount,
			function(msg){callBackClose(msg);});
}
//产品赎回
function doRedeem(productCode,amount,orderId){
	ProductAction.doRedeem(productCode, amount,orderId, function(msg){callBackClose(msg);});
}

//DWR方式基金定投追加
function addFundPlan(savePlanNo) {
	if(confirm("当前每期投资金额将即时增加一期投资。确定追加？")){
		ProductAction.addFundPlan(savePlanNo, function(msg){callBackClose(msg);});
	}
}

function callBackClose(msg){
var msgType = msg.substring(msg.indexOf("\'") + 1, msg.indexOf("\',"));
var msgValue = msg.substring(msg.indexOf("\',\'") + 3, msg
				.lastIndexOf("\']"));
if (msgType == 'true') {
	alert(msgValue);
	window.location.reload();
  } else {
	alert(msgValue);
  }
}

function redeemProduct(productCode,availableVol,orderId){
	ProductAction.getProductInfo(productCode,function(data){	
	    if(data!=null){	       
			
			jQuery("#currency").html(data.currAbbr);
			jQuery("#nav").html(data.nav);
			jQuery("#redeem_lower_limit").html(data.minRedemptionVol);
			jQuery("#navDate").html(data.transactionDate);
			jQuery("#unit_limit").html("0.1");
			jQuery("#rate").html(data.redemptionFee);
			jQuery("#lower_limit").html(data.minAccoutBalance);
			jQuery("#payDays").html("");
			jQuery("#availableTotal").html(availableVol);
			jQuery("#redeemAmount").val(availableVol);
			jQuery("#productCode").val(productCode);
			jQuery("#orderId").val(orderId);
			 //理财
	        if(data.productType==0){
	           jQuery("#finName").html(data.productCode+" "+data.title);
	           jQuery("tr.fund").hide();
	           jQuery("tr.find").show();
	        }else if(data.productType==1){
	           //基金
	           jQuery("#fundName").html(data.productCode+" "+data.title);
	           jQuery("tr.fund").show();
	           jQuery("tr.find").hide();
	        }
		}
	 });
	jQuery( "#redeemProduct" ).dialog("open");
}

function doChanageFundStatus(savePlanNo,status){
  ProductAction.doChanageFundStatus(savePlanNo, status,function(msg){callBackClose(msg);});
}
function doChanagePlan(productCode){
   jQuery("#productForm").attr("action","/u/chanagePlanPage.html");
   jQuery("#productCode").val(productCode);
   jQuery("#productForm").submit();
}

/**
 * 基金转换下拉列表
 * @param oFundCodeUtil
 */
function getProductTrans(oFundCode){
	ProductAction.getProductTrans(oFundCode,function(data){	
	    if(data!=null){	       
	    	jQuery("#iFundCode").empty();
	    	jQuery("<option value=''>请选择</option>").appendTo("#iFundCode");//添加下拉框的option
	        for (var i=0; i<data.length; i++) {
	        	jQuery("<option value='"+data[i].productCode+"'>"+data[i].productCode+" "+data[i].title+"</option>").appendTo("#iFundCode");
	        }
		}
	 });
}
