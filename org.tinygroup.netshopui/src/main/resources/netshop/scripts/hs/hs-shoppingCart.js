var SHOPPINGCART_PRE = "sc_"; // Cookie中，购物车的前缀
var SHOPPINGCART_PATH = "/"; // Cookie中，购物车的Cookie的路径
function formatAsMoney(mnt) {
	mnt -= 0;
	mnt = (Math.round(mnt * 100)) / 100;
	return (mnt == Math.floor(mnt)) ? mnt + '.00' : ((mnt * 10 == Math
			.floor(mnt * 10)) ? mnt + '0' : mnt);
}
// ===========================================
// 使用方法如下：
// ===========================================
// -----------------------
// 第一步：在页面上添加以下代码
// -----------------------
// <script type='text/javascript' src='${ctx}/scripts/hs-cookie.js'></script>
// <script type='text/javascript'
// src='${ctx}/scripts/hs-shoppingCart.js'></script>
// -----------------------
// 第二步：函数调用方法：
// -----------------------
function checkInputNum(goodsId,cartId) {
	var cartGoodsNumObj;
	cartGoodsNumObj = document.getElementById('cartGoodsNumber_' + cartId);
	if (isNaN(cartGoodsNumObj.value) || cartGoodsNumObj.value < 1)
		cartGoodsNumObj.value = 1;
	cartGoodsNumObj.value = parseInt(cartGoodsNumObj.value);
	goodsNumber = document.getElementById('goodsNumber_' + cartId).value;
	var saleNumBegin = document.getElementById('saleNumBegin_' + cartId).value;
	if (parseInt(cartGoodsNumObj.value) >= parseInt(goodsNumber)) {
		// alert("此商品库存量不足，请修改商品数量购买数量！");
		// cartGoodsNumObj.focus();
		// return;
		cartGoodsNumObj.value = parseInt(goodsNumber);
	}else if (parseInt(cartGoodsNumObj.value) <= parseInt(saleNumBegin)){
		cartGoodsNumObj.value = saleNumBegin;
	}
	editDwrShoppingCartNum(goodsId,cartId);
	// calculate();
}

// 购物车的商品数量减1
function reduceOne(goodsId,cartId) {
	var cartGoodsNumObj;
	cartGoodsNumObj = document.getElementById('cartGoodsNumber_' + cartId);
	goodsNumber = document.getElementById('goodsNumber_' + cartId).value;
	var saleNumBegin = document.getElementById('saleNumBegin_' + cartId).value;
	if (isNaN(cartGoodsNumObj.value) || cartGoodsNumObj.value < 2){
		cartGoodsNumObj.value = saleNumBegin;
	}
	else if (parseInt(cartGoodsNumObj.value) >= parseInt(goodsNumber)){
		cartGoodsNumObj.value = goodsNumber - 1;
	}
	else if (parseInt(cartGoodsNumObj.value) <= parseInt(saleNumBegin)){
		cartGoodsNumObj.value = saleNumBegin;
	}
	else{
		cartGoodsNumObj.value = cartGoodsNumObj.value - 1;
	}
	editDwrShoppingCartNum(goodsId,cartId);
	// calculate();
}
// 购物车的商品数量加1
function increaseOne(goodsId,cartId) {
	var cartGoodsNumObj;
	cartGoodsNumObj = document.getElementById('cartGoodsNumber_' + cartId);
	if (isNaN(cartGoodsNumObj.value) || cartGoodsNumObj.value < 1)
		cartGoodsNumObj.value = 1;
	goodsNumber = document.getElementById('goodsNumber_' + cartId).value;
	var saleNumBegin = document.getElementById('saleNumBegin_' + cartId).value;
	if (parseInt(cartGoodsNumObj.value) >= parseInt(goodsNumber)) {
		// alert("此商品库存量不足，请修改商品数量购买数量！");
		// cartGoodsNumObj.focus();
		// return;
		cartGoodsNumObj.value = goodsNumber;
	}else if (parseInt(cartGoodsNumObj.value) < parseInt(saleNumBegin)){
		cartGoodsNumObj.value = saleNumBegin;
	}
	else {
		cartGoodsNumObj.value = parseInt(cartGoodsNumObj.value) + 1;
	}
	editDwrShoppingCartNum(goodsId,cartId);
	// calculate();
}
// 计算总价等。并且检查库存是否充足。
function calculate() {
	var goodsPrice, marketPrice, cartGoodsNumber, goodsNumber, goodsId,cartId;
	var goodsAcount;
	var goodsPriceAcount, marketPriceAcount;
	var promationPrice,promationMarketPrice;
	var promationPriceAcount,promationmarketPriceAcount;
	var isOKofSelectedGoods = true;
	goodsPriceAcount = 0;
	marketPriceAcount = 0;
	promationPriceAcount = 0;
	promationmarketPriceAcount = 0;
	// 遍历所有商品
	var cartIdIdObjs = document.getElementsByName('cartId');
	for (var i = 0; i < cartIdIdObjs.length; i++) {
		cartId = cartIdIdObjs[i].value;
		// 检查库存量
		var cartGoodsNumObj = document.getElementById('cartGoodsNumber_' + cartId);
		if (isNaN(cartGoodsNumObj.value) || cartGoodsNumObj.value < 1) {
			cartGoodsNumObj.value = 1;
			// alert("您选购的商品中,至少有一个商品数量格式不正确，请修改光标所在处的商品购买数量！");
			// document.getElementById('cartGoodsNumber_' + goodsId).focus();
			// return false;
		}
		cartGoodsNumber = document.getElementById('cartGoodsNumber_' + cartId).value;
		goodsNumber = document.getElementById('goodsNumber_' + cartId).value;
		goodsCheckBox = document.getElementById('goodsCartIdList_' + cartId);
		if (parseInt(cartGoodsNumber) > parseInt(goodsNumber)) {
			// continue;
			// alert("您选购的商品中,至少有一个商品库存量不足，请修改光标所在处的商品购买数量！");
			// document.getElementById('cartGoodsNumber_' + goodsId).focus();
			// return false;
			// 如果超过库存,就不让他结账.
			if (goodsCheckBox && goodsCheckBox.checked == true) {
				isOKofSelectedGoods = false;
			}
		}
		/*
		 * // 如果商品的数量没有变化,则隐藏保存按钮,如果有变化,就显示保存按钮 if
		 * (parseInt(document.getElementById('oldCartGoodsNumber_' +
		 * goodsId).value) == parseInt(document
		 * .getElementById('cartGoodsNumber_' + goodsId).value)) {
		 * document.getElementById('saveEditButton_' + goodsId).style.display =
		 * 'none'; } else { document.getElementById('saveEditButton_' +
		 * goodsId).style.display = ''; if (goodsCheckBox &&
		 * goodsCheckBox.checked == true) { isOKofSelectedGoods = false; } }
		 */
		if (!goodsCheckBox || goodsCheckBox.checked == false) {
			continue;
		}

		goodsPrice = document.getElementById('goodsPrice_' + cartId).value;
		//marketPrice = document.getElementById('marketPrice_' + cartId).value;
		// 计算
		goodsAcount = parseFloat(goodsPrice) * parseInt(cartGoodsNumber);// 单件商品的小计
		document.getElementById('goodsAcount_' + cartId).innerHTML = formatAsMoney(goodsAcount);
		//
		goodsPriceAcount = parseFloat(goodsPriceAcount)
				+ parseFloat(goodsAcount);
		//marketPriceAcount = parseFloat(marketPriceAcount)
				//+ parseFloat(marketPrice) * parseInt(cartGoodsNumber);
	}


	//songfy  套餐
	//var promationIdObjs = document.getElementsByName('promationId');
	//for (var i = 0; i < promationIdObjs.length; i++) {
		//var promationId = promationIdObjs[i].value;
		// 检查库存量
		//var cartPromationNumber = document.getElementById('cartPromationNumber_' + promationId).value;

		//var promationCheckBox = document.getElementById('promationIdList_' + promationId);
		/*
		 * // 如果商品的数量没有变化,则隐藏保存按钮,如果有变化,就显示保存按钮 if
		 * (parseInt(document.getElementById('oldCartGoodsNumber_' +
		 * goodsId).value) == parseInt(document
		 * .getElementById('cartGoodsNumber_' + goodsId).value)) {
		 * document.getElementById('saveEditButton_' + goodsId).style.display =
		 * 'none'; } else { document.getElementById('saveEditButton_' +
		 * goodsId).style.display = ''; if (goodsCheckBox &&
		 * goodsCheckBox.checked == true) { isOKofSelectedGoods = false; } }
		 */
		//if (!promationCheckBox || promationCheckBox.checked == false) {
		//	continue;
		//}

		//promationPrice = document.getElementById('promationPrice_' + promationId).value;
		//promationMarketPrice = document.getElementById('promationMarketPrice_' + promationId).value;
		// 计算
		//promationPriceAcount = parseFloat(promationPriceAcount) +parseFloat(promationPrice) * parseInt(cartPromationNumber);// 单件商品的小计
		//promationmarketPriceAcount = parseFloat(promationmarketPriceAcount)
		//		+ parseFloat(promationMarketPrice) * parseInt(cartPromationNumber);
	//}


	document.getElementById('goodsPriceAcount').innerHTML = formatAsMoney(goodsPriceAcount);
	//document.getElementById('marketPriceAcount').innerHTML = formatAsMoney(marketPriceAcount);
	//document.getElementById('jieshenPriceAcount').innerHTML = formatAsMoney(parseFloat(marketPriceAcount)
			//- parseFloat(goodsPriceAcount));

	//document.getElementById('promationPriceAcount').innerHTML = formatAsMoney(promationPriceAcount);
	//document.getElementById('promationmarketPriceAcount').innerHTML = formatAsMoney(promationmarketPriceAcount);
	//document.getElementById('jieshenpromationPriceAcount').innerHTML = formatAsMoney(parseFloat(promationmarketPriceAcount)
			//- parseFloat(promationPriceAcount));

	//var reducePrice = document.getElementById('reducePrice').value;
	//if(reducePrice==''){
		//reducePrice = 0;
	//}
	//document.getElementById('tadeTotalAmount').innerHTML = formatAsMoney((parseFloat(goodsPriceAcount)+parseFloat(promationPriceAcount)) - parseFloat(reducePrice));
	//document.getElementById('tradePoint').innerHTML = parseInt(formatAsMoney((parseFloat(goodsPriceAcount)+parseFloat(promationPriceAcount)) - parseFloat(reducePrice))/100);
	//document.getElementById('tradeAmount').value = formatAsMoney(parseFloat(goodsPriceAcount)+parseFloat(promationPriceAcount));


	//购物变化后重新判断选择的套餐是否符合条件
	//var selectFullGivePromationId = document.getElementById('selectFullGivePromationId').value;
	//if(selectFullGivePromationId !='' && selectFullGivePromationId>0){
		//fullGivePromationDwrShoppingCart(selectFullGivePromationId);
	//}

}

// 修改购物车中商品的数量
function editShoppingCartNum(goodsId,cartId) {
	var cartId, goodsNumber;
	cartId = document.getElementById('cartId_' + cartId).value;
	goodsNumber = document.getElementById('cartGoodsNumber_' + cartId).value;
	if (isNaN(goodsNumber) || goodsNumber < 1) {
		alert('商品的数量必须是数字！并且商品的数量必须大于0！');
		document.getElementById('cartGoodsNumber_' + goodsId).focus();
		// return false;
	}
	if (confirm('你确定要修改数量吗？'))
		location.href = 'editnum.html?id=' + cartId + '&goodsId='
				+ goodsId + '&goodsNumber=' + goodsNumber;
	// return false;
}

// 删除购物车
function removeShoppingCart(goodsId,cartId) {

	if (confirm('你确定要删除这个商品吗？'))
		location.href = 'remove.html?id=' + cartId + '&goodsId='
				+ goodsId;
	// return false;
}

// 清空购物车
function cleanShoppingCart() {
	if (confirm('你确定清空购物车吗？'))
		location.href = 'clean.html';
	// return false;
}

// 清除购物车中过期的商品
function removeOutShoppingCart() {
	//if (confirm('你确定要清除购物车中过期的商品吗？'))
		location.href = 'removeout.html';
	// return false;
}

// ======================================================
// DWR
// ======================================================
function removeDwrShoppingCart(goodsId,cartId) {
	if (confirm('你确定要删除这个产品吗？')) {
		ShoppingCartAction.removeDwrShoppingCart(cartId, goodsId,
				function(msg) {
					callbackRemoveDwrShoppingCart(msg, goodsId,cartId);
				});
		// 这个方法就是Action的方法，最后一个参数是这里额外加的参数，就是回调参数（在下面的这个）。
	}
}
// 回调函数，参数msg就是Action函数的返回值
function callbackRemoveDwrShoppingCart(msg, goodsId,cartId) {
	var msgType = msg.substring(msg.indexOf("\'") + 1, msg.indexOf("\',"));
	var msgValue = msg.substring(msg.indexOf("\',\'") + 3, msg
					.lastIndexOf("\']"));
	if (msgType == 'true') {
		document.getElementById("sc_tr_"+cartId).style.display="none";
	} else
		alert("删除购物车失败!");
}
// dwr 修改数量
function editDwrShoppingCartNum(goodsId,cartId) {
	goodsNumber = document.getElementById('cartGoodsNumber_' + cartId).value;
	if (isNaN(goodsNumber) || goodsNumber < 1) {
		alert('商品的数量必须是数字！并且商品的数量必须大于0！');
		document.getElementById('cartGoodsNumber_' + cartId).focus();
		return;
	}
	goodsNumberBD = document.getElementById('goodsNumber_' + cartId).value;
	if (parseInt(goodsNumber) > parseInt(goodsNumberBD)) {
		alert("此商品库存量不足，请修改商品数量购买数量！");
		document.getElementById('cartGoodsNumber_' + cartId).focus();
		return;
	}
	// if (confirm('你确定要修改数量吗？')) {
	ShoppingCartAction.editDwrShoppingCartNum(cartId, goodsId, goodsNumber,
			function(msg) {
				callbackeditDwrShoppingCart(msg, goodsId,cartId);
			});
	// }
}
// 回调函数，参数msg就是Action函数的返回值
function callbackeditDwrShoppingCart(msg, goodsId,cartId) {
	var msgType = msg.substring(msg.indexOf("\'") + 1, msg.indexOf("\',"));
	var msgValue = msg.substring(msg.indexOf("\',\'") + 3, msg
					.lastIndexOf("\']"));
	if (msgType == 'true') {
		// alert(msgValue);
		document.getElementById('oldCartGoodsNumber_' + cartId).value = document
				.getElementById('cartGoodsNumber_' + cartId).value;
		// document.getElementById('saveEditButton_' + goodsId).style.display =
		// 'none';
		calculate();
	} else
		alert("修改购物车失败!");
	// alert("edit false; [" + msgValue + "]");
}
// ==============================================
// 以下是操作购物车的Cookie
// ==============================================

/**
 * 如果存在相同的商品在Cookie中，先相加，再保存到Cookie中。
 *
 * @param {}
 *            goodsId
 * @param {}
 *            num
 */
function addShoppingCartCookie(goodsId, num) {
	var name, value, expires, path, domain, secure;
	path = SHOPPINGCART_PATH;
	name = SHOPPINGCART_PRE + goodsId;
	var numOld = getCookieValue(name)
	value = parseInt(num) + parseInt(numOld);
	setCookie(name, value, expires, path, domain, secure);
}

/**
 * 保存到Cookie中,如果已经存在相同的商品,则会覆盖.
 *
 * @param {}
 *            goodsId
 * @param {}
 *            num
 */
function saveShoppingCartCookie(goodsId, num) {
	var name, value, expires, path, domain, secure;
	name = SHOPPINGCART_PRE + goodsId;
	value = num;
	setCookie(name, value, expires, path, domain, secure);
}

/**
 * (没实现)重新保存购物车集,此操作会先清空Cookie中的购物车,再重新保存.
 *
 * @param {}
 *            ShoppingCartList
 */
function reSaveShoppingCartListCookie(ShoppingCartList) {
	// removeAllShoppingCart();
	alert("此功能还没开发完成.");
}

/**
 * 从Cookie中移除一个购物车
 *
 * @param {}
 *            goodsId
 */
function removeShoppingCartCookie(goodsId) {
	var name, path, domain;
	path = SHOPPINGCART_PATH;
	name = SHOPPINGCART_PRE + goodsId;
	deleteCookie(name, path, domain)
}

/**
 * 清空全部购物车
 *
 * @param {}
 *            goodsId
 */
function removeAllShoppingCartCookie() {

}

function selectAllCheckBox(obj, chk) {
	if (chk == null) {
		chk = 'goodsCartIdList';
	}

	var elems = obj.form.getElementsByTagName("INPUT");

	for (var i = 0; i < elems.length; i++) {
		if (elems[i].name == chk) {
			elems[i].checked = obj.checked;
		}
	}
	calculate();
}


//songfy 全场套餐活动
function fullGivePromationDwrShoppingCart(promationId) {
	// if (confirm('你确定要修改数量吗？')) {
	var amount = document.getElementById('tradeAmount').value;
	ShoppingCartAction.fullGivePromationDwrShoppingCart(promationId,amount,
			function(msg) {
				callbackFullGivePromationDwrShoppingCart(msg, promationId);
			});
}


function callbackFullGivePromationDwrShoppingCart(msg, promationId) {
	var msgType = msg.substring(msg.indexOf("\'") + 1, msg.indexOf("\',"));
	var msgValue = msg.substring(msg.indexOf("\',\'") + 3, msg
					.lastIndexOf("\']"));
	document.getElementById('fullGiveMsg').style.display = "";
	if (msgType == 'give') {
		document.getElementById('fullGiveMsg').innerHTML = "<ol><li>活动选择成功</li></ol><div class=\"clearing\"></div>";
		document.getElementById('selectFullGivePromationId').value = promationId;
		document.getElementById('fullGivePromationId_'+promationId).checked = true;
		document.getElementById('reducePrice').value = 0;
		var totalAmount = parseFloat(document.getElementById('tradeAmount').value);
		document.getElementById('tadeTotalAmount').innerHTML = formatAsMoney(totalAmount);
		document.getElementById('tradePoint').innerHTML = parseInt(totalAmount/100);
	}else if (msgType == 'reduce') {
		document.getElementById('fullGiveMsg').innerHTML = "<ol><li>活动选择成功</li></ol><div class=\"clearing\"></div>";
		document.getElementById('selectFullGivePromationId').value = promationId;
		document.getElementById('reducePrice').value = msgValue;
		var totalAmount = parseFloat(document.getElementById('tradeAmount').value)-parseFloat(msgValue);
		document.getElementById('tadeTotalAmount').innerHTML = formatAsMoney(totalAmount);
		document.getElementById('tradePoint').innerHTML = parseInt(totalAmount/100);
		document.getElementById('fullGivePromationId_'+promationId).checked = true;

	} else{
		document.getElementById('fullGiveMsg').innerHTML = "<ol><li>你当前的购物金额不能享受选择的活动</li></ol><div class=\"clearing\"></div>";
		document.getElementById('fullGivePromationId_'+promationId).checked = false;
		document.getElementById('reducePrice').value = 0;
		var totalAmount = parseFloat(document.getElementById('tradeAmount').value);
		document.getElementById('tadeTotalAmount').innerHTML = formatAsMoney(totalAmount);
		document.getElementById('tradePoint').innerHTML = parseInt(totalAmount/100);
	}
	// alert("edit false; [" + msgValue + "]");
}

//删除购物车套餐
function removeDwrShoppingCartPromation(promationId,timeTag) {
	if (confirm('你确定要删除这个套餐吗？')) {
		ShoppingCartAction.removeDwrShoppingCartPromation(promationId, timeTag,
				function(msg) {
					callbackRemoveDwrShoppingCartPromation(msg, promationId,timeTag);
				});
		// 这个方法就是Action的方法，最后一个参数是这里额外加的参数，就是回调参数（在下面的这个）。
	}
}
// 回调函数，参数msg就是Action函数的返回值
function callbackRemoveDwrShoppingCartPromation(msg, promationId,timeTag) {
	var msgType = msg.substring(msg.indexOf("\'") + 1, msg.indexOf("\',"));
	var msgValue = msg.substring(msg.indexOf("\',\'") + 3, msg
					.lastIndexOf("\']"));

	if (msgType == 'true') {
		var currRowIndex = document.getElementById("sc_tr_" + promationId+timeTag).rowIndex;
		// alert(msgValue);
		removeTableRow("list-table-promation", currRowIndex);
		calculate();
	} else
		alert("删除购物车失败!");
}

/**
 * 批量删除购物车
 *
 * @param
 *
 */
function removeDwrShoppingCartVolume() {
	var elems = document.getElementsByName("goodsCartIdList");
	var num = 0;
	for (var i = 0; i < elems.length; i++) {
		if (elems[i].checked) {
			num=1;
		}
	}

	if(num==0){
		alert("请先选择要删除的商品！");
	}else{
		if (confirm('你确定要批量删除选中的商品吗？')) {

			var cartIds = "";
			for (var i = 0; i < elems.length; i++) {
				if (elems[i].checked) {
					if(cartIds==""){
						cartIds = elems[i].value;
					}else{
						cartIds = cartIds+";"+elems[i].value;
					}
				}
			}
			ShoppingCartAction.removeDwrShoppingCartVolume(cartIds,
					function(msg) {
						callbackRemoveDwrShoppingCartVolume(msg, cartIds);
					});
			// 这个方法就是Action的方法，最后一个参数是这里额外加的参数，就是回调参数（在下面的这个）。
		}
	}
}
// 回调函数，参数msg就是Action函数的返回值
function callbackRemoveDwrShoppingCartVolume(msg, cartIds) {
	var card = "";
	card = cartIds.split(";");

	var msgType = msg.substring(msg.indexOf("\'") + 1, msg.indexOf("\',"));
	var msgValue = msg.substring(msg.indexOf("\',\'") + 3, msg
					.lastIndexOf("\']"));
	if (msgType == 'true') {
		for(var i=0 ; i<card.length ; i++){
			var cartId = card[i];
			var currRowIndex = document.getElementById("sc_tr_" + cartId).rowIndex;
			var shopId = document.getElementById("goodsShopId_" + cartId).value;
			var shopRowIndex = document.getElementById("sr_shop_" + shopId).rowIndex;
			var shopGoodsNum = document.getElementById("shopGoodsNum_" + shopId).value;
			document.getElementById("shopGoodsNum_" + shopId).value = shopGoodsNum-1;
			removeTableRow("list-table", currRowIndex);
			if((shopGoodsNum-1)==0){
				removeTableRow("list-table", shopRowIndex);
			}
		}

		var elems = document.getElementsByName("goodsCartIdList");
		var num = 0;
		for (var i = 0; i < elems.length; i++) {
				num=1;
		}

		if (num==0) {
			document.getElementById("hasgoods").style.display="none";
			document.getElementById("nogoods").style.display="";
		}

		calculate();
	} else
		alert("批量删除购物车失败!");
}
