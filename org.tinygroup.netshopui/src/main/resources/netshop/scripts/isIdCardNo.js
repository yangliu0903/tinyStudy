
function isIdCardNo(num) {
	 num = num.toUpperCase();

if (!(/(^\d{15}$)|(^\d{18}$)|(^\d{17}([0-9]|X)$)/.test(num))){
alert("身份证号:\'"+num+"\'错误，请正确输入");
	return false;}

var len, re;
len = num.length;
if (len == 15)
	{ re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
var arrSplit = num.match(re);

var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
var bGoodDay;
bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
if (!bGoodDay) {
	alert("身份证号:\'"+num+"\'错误，请正确输入");
	return false;
	} else {

var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
 var nTemp = 0, i;
		 num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
		 for(i = 0; i < 17; i ++)
			 {
			 nTemp += num.substr(i, 1) * arrInt[i];
			 }num += arrCh[nTemp % 11];
			 return num; } }
			 if (len == 18) { re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
			 var arrSplit = num.match(re);

var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
var bGoodDay;
bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
if (!bGoodDay) {

alert("身份证号:\'"+num+"\'错误，请正确输入");
return false;
} else {

var valnum;
var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
var nTemp = 0, i;
for(i = 0; i < 17; i ++)
	{ nTemp += num.substr(i, 1) * arrInt[i]; }
valnum = arrCh[nTemp % 11];
if (valnum != num.substr(17, 1)) {
	alert("身份证号:\'"+num+"\'错误，请正确输入");
return false;
} return num;
} }
return false;
}