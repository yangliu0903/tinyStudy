//是否为日期字串格式为2006-02-02
function isDateString(str) {
	var reg=/^\d{4}-[0-1]?\d{1}-[0-3]?\d{1}$/;
	return reg.test(str);
}

//日期字串转换为日期型
function stringToDate(sDate, bIgnore)
{
	var bValidDate, year, month, day;
	var iaDate = new Array(3);
	
	if (bIgnore) {
		bValidDate = true;
	} else {
		bValidDate = isDateString(sDate);
	}
	
	if (bValidDate) {  iaDate = sDate.toString().split("-");
		year = parseFloat(iaDate[0]);
		month = parseFloat(iaDate[1]) - 1;
		day=parseFloat(iaDate[2]);
		return (new Date(year,month,day));
	} else {
		return (new Date(1900,1,1));
	}
}

//比较日期
function compareToday(strDate2,serverDate) {
	var today=new Date();
	//var strDate1 = today.getFullYear() + "-" + (today.getMonth()+1) + "-" + today.getDate();
	var strDate1 = trim(serverDate);
	var tmp1 = serverDate.split('/');
	if (tmp1.length > 1)
	{ 
	   strDate1 = trim(tmp1[2]) + '-'+tmp1[0] + '-' + tmp1[1];
	}
	strDate2 = trim(strDate2);
	var tmp2 = strDate2.split('/');
	if (tmp2.length > 1)
	{ 
	   strDate2 = trim(tmp2[2]) + '-'+tmp2[0] + '-' + tmp2[1];
	}
	return(stringToDate(strDate1, true)<=stringToDate(strDate2, true))	;
}

//去空格
function trim(str)
{
	var SubStr;
	SubStr=str;
	while (SubStr.length>0) {
   		if (SubStr.charAt(0)==" "){
			SubStr=SubStr.slice(1);
		}else{
			break;
		}
  	}
	while (SubStr.length>0) {
   		if (SubStr.charAt(SubStr.length-1)==" "){
			SubStr=SubStr.substr(0,SubStr.length-1);
		}else{
			break;
		}
  	}
  	return SubStr;
}


//检测手机号码
function CheckMobile(Mobile) {
    var filter=/^13[0123456789]{1}[0-9]{8}$/;
    if (!filter.test(Mobile)) {
    	return false;
    }
    return true;
}

//是否为中文
function CheckChinese(str)
{
   	if (str.search(/[^\x00-\xff]/) != -1) {
		return false;
	} else {
		return true;
	}
}



//检测小灵通号码
function CheckPas(Pas) {
    var ArryPas = Pas.split("-");
    var Len = ArryPas.length;
    
    if (Len > 3 || Len < 2){
    	return false;
    }
    
    for (var k =0;k <Len;k+=1)
    {
      if (ArryPas[k].replace(/\d/gi,"")!="") {
      	return false;
      }
    }
    
    if (Len == 2)
    {
		if (ArryPas[0].length < 2 || ArryPas[0].length > 4)return false;
		if (ArryPas[1].length < 5 || ArryPas[1].length > 8)return false;
    }   
   
    if (Len == 3)
    {
          if (ArryPas[0].length > 4||ArryPas[0].length < 2)return false;
          if (ArryPas[1].length > 4||ArryPas[1].length < 2)return false;
          if (ArryPas[2].length < 5||ArryPas[2].length > 8)return false;    
    } 
    return true;
}

//检测字母及数字
function CheckNumAndChar(str) {
	var patrn = /^[a-zA-Z0-9_ ]{0,}$/
	
	if(!patrn.exec(str)) {
	  return true;
	} else { 
	  return false; 
	}
}

//检测字母
function CheckChar(str) {
	var patrn = /^[a-zA-Z]{0,}$/
	
	if(!patrn.exec(str)) {
	  return true;
	} else { 
	  return false; 
	}
}

//检测大写字母
function CheckUpperCase(str) {
	var patrn =/^[A-Z]{0,}$/
	
	if(!patrn.exec(str)) {
	  return true;
	} else { 
	  return false; 
	}
}


function urlOpen(url)
{
	window.open(url);
}


//身份证号
function isChinaIDCard(sNo)
{
    sNo = sNo.toString()
    if (sNo.length==18)
    {
       var a,b,c
       if (!checkSum(sNo.substr(0,17))) {return false}
       a=parseInt(sNo.substr(0,1))*7+parseInt(sNo.substr(1,1))*9+parseInt(sNo.substr(2,1))*10;
       a=a+parseInt(sNo.substr(3,1))*5+parseInt(sNo.substr(4,1))*8+parseInt(sNo.substr(5,1))*4;
       a=a+parseInt(sNo.substr(6,1))*2+parseInt(sNo.substr(7,1))*1+parseInt(sNo.substr(8,1))*6; 
       a=a+parseInt(sNo.substr(9,1))*3+parseInt(sNo.substr(10,1))*7+parseInt(sNo.substr(11,1))*9; 
       a=a+parseInt(sNo.substr(12,1))*10+parseInt(sNo.substr(13,1))*5+parseInt(sNo.substr(14,1))*8; 
       a=a+parseInt(sNo.substr(15,1))*4+parseInt(sNo.substr(16,1))*2;
       b=a%11;
       if (b==2)
       {
          c=sNo.substr(17,1).toUpperCase();
       }
       else
       {
         c=parseInt(sNo.substr(17,1));
       }
       
       switch(b)
       {
         case 0: if ( c!=1 ) {return false;}break;
         case 1: if ( c!=0 ) {return false;}break;
         case 2: if ( c!="X") {return false;}break;
         case 3: if ( c!=9 ) {return false;}break;
         case 4: if ( c!=8 ) {return false;}break;
         case 5: if ( c!=7 ) {return false;}break;
         case 6: if ( c!=6 ) {return false;}break;
         case 7: if ( c!=5 ) {return false;}break;
         case 8: if ( c!=4 ) {return false;}break;
         case 9: if ( c!=3 ) {return false;}break;
         case 10: if ( c!=2 ){return false}
      }
   }
   else
   {
       if (!checkSum(sNo)) {return false} 
   }

   switch(sNo.length)
   {
      case 15: if (isValidDate( parseInt(sNo.substr(6,2),10),parseInt(sNo.substr(8,2),10),parseInt(sNo.substr(10,2),10))) {return true}
      case 18: if (isValidDate( parseInt(sNo.substr(6,2),10),parseInt(sNo.substr(8,2),10),parseInt(sNo.substr(10,2),10))) {return true}
   }
   return false
}




function isValidDate(iY, iM, iD) 
{ 
   if ( iY != undefined && !isNaN(iY) && iY >=0 && iY<=9999 && iM != undefined && !isNaN(iM) && iM >=1 && iM<=12 && iD != undefined && !isNaN(iD) && iD >=1   && iD<=31  )  
   { 
       if (iY<50) 
       {
          iY = 2000+iY;
       }
       else if (iY<100) 
       {
          iY=1900+iY; 
       }
       if (iM == 2  && (isLeapYear(iY)  && iD > 29 || !isLeapYear(iY) && iD>28) || iD == 31 && (iM<7 && iM%2==0 || iM>7 && iM%2==1) ) 
       { 
           return false ; 
       }
       else
       {
           return true;
       }
   }
   else
   { 
      return true;
   }
} 

//是否闰年
function isLeapYear(y)
{
    if(0==y%4&&((y%100!=0)||(y%400==0))) return true;else return false;
}




//校验number类型
function CheckNumber(str)
{
 var patrn = /^[0-9]{1,14}\.{0,1}[0-9]{0,4}$/;

 if(!patrn.exec(str)) {
  return true;
 } else { 
        return false; 
    }
}


//校验number类型
function CheckNumbers(str)
{
 var patrn = /^[0-9]{1,2}\.{0,1}[0-9]{0,4}$/;

 if(!patrn.exec(str)) {
  return true;
 } else { 
        return false; 
    }
}

//校验邮政编码
function isPostalCode(s)
{
	//var patrn=/^[a-zA-Z0-9]{3,12}$/;
	var patrn=/^[a-zA-Z0-9 ]{3,12}$/;
	if (!patrn.exec(s)) return false
	return true
}

//校验是否为整形
function CheckInteger(str)
{
	var patrn = /^[0-9]{1,}$/;

	if(!patrn.exec(str)) {
		return true;
	} else { 
		return false; 
    }
}

/**
 *公用日期效驗
 */
function isDate(checktext){
var datetime;
var year,month,day;
var gone,gtwo;
if(checktext !=""){
 datetime=checktext;
 if(datetime.length==8){
  year=datetime.substring(0,4);
  if(isNaN(year)==true){
   alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
///   checktext.focus();
   return false;
  }
//  gone=datetime.substring(4,5);
  month=datetime.substring(4,6);
  if(isNaN(month)==true){
   alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
//   checktext.focus();
   return false;
  }
//  gtwo=datetime.substring(7,8);
  day=datetime.substring(6,8);
  if(isNaN(day)==true){
   alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
//   checktext.focus();
   return false;
  }
//  if((gone=="-")&&(gtwo=="-")){
   if((1*month) < 1 || (1*month) > 12) { 
    alert("月份必须在01和12之间!"); 
 //   checktext.focus();
    return false; 
    } 
   if((1 * day)<1||(1 * day)>31){ 
    alert("日期必须在01和31之间!");
 //   checktext.focus(); 
    return false; 
   }else{
    if(1 * month==2){  
     if(isLeapYear(year)&&day>29){ 
       alert("二月份日期必须在01到29之间!"); 
 //      checktext.focus();
       return false; 
     }       
     if(!isLeapYear(year)&&day>28){ 
       alert("二月份日期必须在01到28之间!");
 //      checktext.focus(); 
       return false; 
     } 
    } 
    if(((1 * month)==4||(1 * month)==6||(1 * month)==9||(1 * month)==11)&&(1 * day>30)){ 
     alert("在四，六，九，十一月份 \n日期必须在01到30之间!");
 //    checktext.focus(); 
     return false; 
    } 
   }
 
 }else{
  alert("请输入日期!格式为(yyyymmdd) \n例(20010101)");
//  checktext.focus();
  return false;
 }
}else{
 return true;
}
return true;
}

function isDateNew(yearObj, monthObj, dayObj){
	var datetime;
	var year = yearObj.value.trim();
	var month = monthObj.value.trim();
	var day = dayObj.value.trim();
	var checktext = year + month + day;
	if(checktext != ""){
		if (yearObj.value.trim().length != 4) {
			alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
			yearObj.style.backgroundColor = "#fff2e9";
			yearObj.style.border = "1px solid #ff6600";
			return false;
		}
		if (monthObj.value.trim().length != 2) {
			alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
			monthObj.style.backgroundColor = "#fff2e9";
			monthObj.style.border = "1px solid #ff6600";
			return false;
		}
		if (dayObj.value.trim().length != 2) {
			alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
			dayObj.style.backgroundColor = "#fff2e9";
			dayObj.style.border = "1px solid #ff6600";
			return false;
		} 
		datetime=checktext;
		if(datetime.length==8){
			year=datetime.substring(0,4);
			if(isNaN(year)==true){
				alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
				yearObj.style.backgroundColor = "#fff2e9";
				yearObj.style.border = "1px solid #ff6600";
				return false;
			}
			month=datetime.substring(4,6);
			if(isNaN(month)==true){
				alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
				monthObj.style.backgroundColor = "#fff2e9";
				monthObj.style.border = "1px solid #ff6600";
				return false;
			}
			day=datetime.substring(6,8);
			if(isNaN(day)==true){
				alert("请输入日期!格式为(yyyymmdd) \n例(20010101)！");
				dayObj.style.backgroundColor = "#fff2e9";
				dayObj.style.border = "1px solid #ff6600";
				return false;
			}
			if((1*month) < 1 || (1*month) > 12) { 
    			alert("月份必须在01和12之间!");
				monthObj.style.backgroundColor = "#fff2e9";
				monthObj.style.border = "1px solid #ff6600";
			    return false; 
			} 
			if((1 * day)<1||(1 * day)>31){ 
				alert("日期必须在01和31之间!");
				dayObj.style.backgroundColor = "#fff2e9";
				dayObj.style.border = "1px solid #ff6600";
				return false; 
			}else{
				if(1 * month==2){  
					if(isLeapYear(year)&&day>29){ 
						alert("二月份日期必须在01到29之间!"); 
						dayObj.style.backgroundColor = "#fff2e9";
						dayObj.style.border = "1px solid #ff6600";
						return false; 
					}       
				if(!isLeapYear(year)&&day>28){ 
					alert("二月份日期必须在01到28之间!");
					dayObj.style.backgroundColor = "#fff2e9";
					dayObj.style.border = "1px solid #ff6600";
					return false; 
				} 
			} 
			if(((1 * month)==4||(1 * month)==6||(1 * month)==9||(1 * month)==11)&&(1 * day>30)){ 
				alert("在四，六，九，十一月份 \n日期必须在01到30之间!");
				dayObj.style.backgroundColor = "#fff2e9";
				dayObj.style.border = "1px solid #ff6600";
				return false; 
			} 
		}
 
	}else{
		alert("请输入日期!格式为(yyyymmdd) \n例(20010101)");
		return false;
	}
}else{
 return true;
}
return true;
}


function isDate_right(checktext){
var datetime;
var year,month,day;
var gone,gtwo;
if(checktext.value !=""){
 datetime=checktext.value;
 if(datetime.length==10){
  year=datetime.substring(0,4);
  if(isNaN(year)==true){
   alert("请输入日期!格式为(yyyy-mm-dd) \n例(2001-01-01)！");
   checktext.focus();
   return false;
  }
  gone=datetime.substring(4,5);
  month=datetime.substring(5,7);
  if(isNaN(month)==true){
   alert("请输入日期!格式为(yyyy-mm-dd) \n例(2001-01-01)！");
   checktext.focus();
   return false;
  }
  gtwo=datetime.substring(7,8);
  day=datetime.substring(8,10);
  if(isNaN(day)==true){
   alert("请输入日期!格式为(yyyy-mm-dd) \n例(2001-01-01)！");
   checktext.focus();
   return false;
  }
  if((gone=="-")&&(gtwo=="-")){
   if(month<1||month>12) { 
    alert("月份必须在01和12之间!"); 
    checktext.focus();
    return false; 
    } 
   if(day<1||day>31){ 
    alert("日期必须在01和31之间!");
    checktext.focus(); 
    return false; 
   }else{
    if(month==2){  
     if(isLeapYear(year)&&day>29){ 
       alert("二月份日期必须在01到29之间!"); 
       checktext.focus();
       return false; 
     }       
     if(!isLeapYear(year)&&day>28){ 
       alert("二月份日期必须在01到28之间!");
       checktext.focus(); 
       return false; 
     } 
    } 
    if((month==4||month==6||month==9||month==11)&&(day>30)){ 
     alert("在四，六，九，十一月份 \n日期必须在01到30之间!");
     checktext.focus(); 
     return false; 
    } 
   }
  }else{
   alert("请输入日期!格式为(yyyy-mm-dd) \n例(2001-01-01)");
   checktext.focus();
   return false;
  }
 }else{
  alert("请输入日期!格式为(yyyy-mm-dd) \n例(2001-01-01)");
  checktext.focus();
  return false;
 }
}else{
 return true;
}
return true;
}


//判断是否是空
function isEmpty(pObj,errMsg){
 var obj = eval(pObj);
 if( obj == null || trim(obj.value) == ""){
  if (errMsg == null || errMsg =="")
   alert("输入为空!");
  else
   alert(errMsg); 
  obj.focus(); 
  return false;
 }
 return true;
}


//判断是否是数字
function isNumber(pObj,errMsg){
 var obj = eval(pObj);
 strRef = "1234567890";
 if(!isEmpty(pObj,errMsg))return false;
 for (i=0;i<obj.value.length;i+=1) {
  tempChar= obj.value.substring(i,i+1);
  if (strRef.indexOf(tempChar,0)==-1) {
   if (errMsg == null || errMsg =="")
    alert("数据不符合要求,请检查");
   else
    alert(errMsg);
   if(obj.type=="text") 
    obj.focus(); 
   return false; 
  }
 }
 return true;
}

//判断是否是数字,数字可以为负数
function isNegative(pObj,errMsg){
 var obj = eval(pObj);
 strRef = "1234567890-";
 if(!isEmpty(pObj,errMsg))return false;
 for (i=0;i<obj.value.length;i+=1) {
  tempChar= obj.value.substring(i,i+1);
  if (strRef.indexOf(tempChar,0)==-1) {
   if (errMsg == null || errMsg =="")
    alert("数据不符合要求,请检查");
   else
    alert(errMsg);
   if(obj.type=="text") 
    obj.focus(); 
   return false; 
  }else{
   if(i>0){
    if(obj.value.substring(i,i+1)=="-"){
     if (errMsg == null || errMsg =="")
      alert("数据不符合要求,请检查");
     else
      alert(errMsg);   
     if(obj.type=="text") 
     obj.focus(); 
     return false; 
    }
   }
  }
 }
 return true;
}

//判断是否是钱的形式
function isMoney(pObj,errMsg){
 var obj = eval(pObj);
 strRef = "1234567890.";
 if(!isEmpty(pObj,errMsg)) return false;
 for (i=0;i<obj.value.length;i+=1) {
  tempChar= obj.value.substring(i,i+1);
  if (strRef.indexOf(tempChar,0)==-1) {
   if (errMsg == null || errMsg =="")
    alert("数据不符合要求,请检查");
   else
    alert(errMsg);   
   if(obj.type=="text") 
    obj.focus(); 
   return false; 
  }else{
   tempLen=obj.value.indexOf(".");
   if(tempLen!=-1){
    strLen=obj.value.substring(tempLen+1,obj.value.length);
    if(strLen.length>2){
     if (errMsg == null || errMsg =="")
      alert("数据不符合要求,请检查");
     else
      alert(errMsg);   
     if(obj.type=="text") 
     obj.focus(); 
     return false; 
    }
   }
  }
 }
 return true;
}


/**
 * author xkxmud.li
 * 判断是否是符合规则的电话号码或传真
 * 数字、“+”、“-”、“(”、“)”
 */
function isTelNo(str) {
	var reg = /^[0-9\+\-\(\)]{3,15}$/;
	if (!reg.exec(trim(str))) return false;
	return true;
}

/**
 * author xkxmud.li
 * 比较两个日期大小
 */
function compareDate(strDate1, strDate2) {
	strDate1 = trim(strDate1);
	strDate2 = trim(strDate2);
	var Date1 = strDate1.split('-');
	var Date2 = strDate2.split('-');
	if (Date1.length > 1)
	{ 
	   strDate1 = "" + Date1[0] + Date1[1] + Date1[2];
	}
	if (Date2.length > 1)
	{ 
	   strDate2 = "" + Date2[0] + Date2[1] + Date2[2];
	}
	if ((strDate1 * 1) == (strDate2 * 1)) {
		return 0;
	} else if ((strDate1 * 1) > (strDate2 * 1)) {
		return 1;
	} else {
		return -1;
	} 
}

/**
 * author xkxmud.li
 * 比较两个日期大小
 */
function compareDateNew(strDate1, strDate2) {
	strDate1 = trim(strDate1);
	strDate2 = trim(strDate2);
	
	if ((strDate1 * 1) == (strDate2 * 1)) {
		return 0;
	} else if ((strDate1 * 1) < (strDate2 * 1)) {
		return 1;
	} else {
		return -1;
	} 
}

//检测邮箱地址
function CheckEmail(Email)
{
	var patrn = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(!patrn.exec(Email)) {
		return true;
	} else {
		return false;
	}
}

/*
 *使用方法：首先将此js库文件引入到所需页面的head标签里，
 *例如：<head><script language="JavaScript" type="text/javascript" src="./check.js"></script></head>
 *然后通过button类型的按钮中的属性onclick来调用相应的js验证方法，
 *例如：<input type='button' value='test' onclick='methodName(formName.inputTextName.value)' />
 *其中的methodName为js方法名，formName为此按钮所在form表单name属性名，inputTextName为需要验证的文本输入框的name属性名,
 *如果您所调用的js验证方法需要传入多个参数，可以用逗号进行分隔。
 */

/*
 *身份证号码验证，目前可实现：
 1.在身份证号码和生日全填入的情况下：身份证号码为15位时，身份证号码只能为数字并且它和生日相对应的部
 分要一致；身份证号码为18位时，它和生日相对应的部分也要一致，并且要符合18位身份证的特定算法。
 2.只填入身份证号码的情况下：身份证
 号码为15位时只能为数字；身份证号码为18位时需要满足18位身份证的特定算法。两种情况的前提是必须保证身份证号码只能为15位或18位。
 其中生日的形式为'19800128'。
 3.性别
*/
function checkIDCardNo(strIDCardNo, strBirthday, strSex, obj) {
	var patrn = /\d{15}/;
	var pattern = /^\d{17}(\d|X|x)$/;
	var patrnstrSex = /[0,1]/;
	
	/*检查身份证长度：15或18位*/
	if (strIDCardNo.length != 15 && strIDCardNo.length != 18) {
		alert("身份证号码只能为15位或18位 ！");
		return "0";
	} else if (strIDCardNo.length == 15) { /* 15位身份证 */
		/* 身份证号码字串检查 */
		if (!patrn.test(strIDCardNo)) {
			alert("15位身份证号码只能是数字 ！");
			return "0";
		}
		/* 身份证号码与出生日期匹配检查 */
		if (strBirthday.length == 8) {
			var i = strIDCardNo.substr(6,6);
			var j = strBirthday.substr(2,6);
			if (i != j) {
				alert("身份证号码与出生日期不匹配 ！");
				return "0";
			}
		} else if (strBirthday.length !== 0) {
			alert("请按照指定格式输入出生日期，如：19800128");
			return "0";
		}
		/* 身份证号码与性别匹配检查 */
		if (strSex.length == 1) {
			if (!patrnstrSex.test(strSex)) {
				alert("性别只能是 0(女) 或 1(男) ！");
				return "0";
			} 
			if (strIDCardNo.length == 15) {							
				if (strSex === '0' && strIDCardNo.substr(14,1) % 2 !== 0 ) {				
					alert("身份证和性别 0(女) 不匹配 ！");
					return "0";				
				} else if (strSex == '1' && strIDCardNo.substr(14,1) % 2 === 0 ) {
					alert("身份证和性别 1(男) 不匹配 ！");
					return "0";		
				}
			} else if (strIDCardNo.length == 18) {
				if (strSex === '0' && strIDCardNo.substr(16,1) % 2 !== 0 ) {				
					alert("身份证和性别 0(女) 不匹配 ！");
					return "0";					
				} else if (strSex == '1' && strIDCardNo.substr(16,1) % 2 === 0 ) {
					alert("身份证和性别 1(男) 不匹配 ！");
					return "0";		
				}
			}
	   } else if (strSex.length > 1) {
			alert("性别只能是 0(女) 或 1(男) ！");
			return "0";
	   }

	} else if (strIDCardNo.length == 18) {/* 18位身份证 */
		/* 身份证号码字串检查 */
		if (!pattern.test(strIDCardNo)) {
			alert("身份证号码为18位时最后一位可以为X，其他各位只能为数字 ！");
			return "0";
		} 
		/* 18位身份证号码校验检查 */
		var a, b, c;
		a = parseInt(strIDCardNo.substr(0, 1)) * 7 + parseInt(strIDCardNo.substr(1, 1)) * 9 + parseInt(strIDCardNo.substr(2, 1)) * 10;
		a = a + parseInt(strIDCardNo.substr(3, 1)) * 5 + parseInt(strIDCardNo.substr(4, 1)) * 8 + parseInt(strIDCardNo.substr(5, 1)) * 4;
		a = a + parseInt(strIDCardNo.substr(6, 1)) * 2 + parseInt(strIDCardNo.substr(7, 1)) * 1 + parseInt(strIDCardNo.substr(8, 1)) * 6; 
		a = a + parseInt(strIDCardNo.substr(9, 1)) * 3 + parseInt(strIDCardNo.substr(10, 1)) * 7 + parseInt(strIDCardNo.substr(11, 1)) * 9; 
		a = a + parseInt(strIDCardNo.substr(12, 1)) * 10 + parseInt(strIDCardNo.substr(13, 1)) * 5 + parseInt(strIDCardNo.substr(14, 1)) * 8; 
		a = a + parseInt(strIDCardNo.substr(15, 1)) * 4 + parseInt(strIDCardNo.substr(16, 1)) * 2;
		b = a % 11;
		if (b == 2) {
			c = strIDCardNo.substr(17,1).toUpperCase();
	    } else {
			c = parseInt(strIDCardNo.substr(17,1));
		}
	    switch (b) {
			case 0: if (c != 1) {alert("不是有效身份证号!");return "0";}break;
			case 1: if (c !== 0) {alert("不是有效身份证号!");return "0";}break;
			case 2: if (c != "X") {alert("不是有效身份证号!");return "0";}break;
			case 3: if (c != 9) {alert("不是有效身份证号!");return "0";}break;
			case 4: if (c != 8) {alert("不是有效身份证号!");return "0";}break;
			case 5: if (c != 7) {alert("不是有效身份证号!");return "0";}break;
			case 6: if (c != 6) {alert("不是有效身份证号!");return "0";}break;
			case 7: if (c != 5) {alert("不是有效身份证号!");return "0";}break;
			case 8: if (c != 4) {alert("不是有效身份证号!");return "0";}break;
			case 9: if (c != 3) {alert("不是有效身份证号!");return "0";}break;
			case 10: if (c != 2) {alert("不是有效身份证号!");return "0";}break;
      	}    
	    
		/* 身份证号码与出生日期匹配检查 */
		if (strBirthday.length == 8) {
			var m = strIDCardNo.substr(6, 8);
			if (m != strBirthday) {
				alert("身份证号码与出生日期不匹配 ！");
				return "0";
			}
		} else if (strBirthday.length !== 0) {
			alert("请按照指定格式输入出生日期，如：19800128");
			return "0";
		}
		
		/* 身份证号码与性别匹配检查 */
		if (strSex.length == 1) {
			if (!patrnstrSex.test(strSex)) {
				alert("性别只能是 0(女) 或 1(男) ！");
				return "0";
			} 
			if (strIDCardNo.length == 15) {							
				if (strSex === '0' && strIDCardNo.substr(14,1) % 2 !== 0 ) {				
					alert("身份证和性别 0(女) 不匹配 ！");
					return "0";					
				} else if (strSex == '1' && strIDCardNo.substr(14,1) % 2 === 0 ) {
					alert("身份证和性别 1(男) 不匹配 ！");
					return "0";		
				}
			} else if (strIDCardNo.length == 18) {
				if (strSex === '0' && strIDCardNo.substr(16,1) % 2 !== 0 ) {				
					alert("身份证和性别 0(女) 不匹配 ！");
					return "0";					
				} else if (strSex == '1' && strIDCardNo.substr(16,1) % 2 === 0 ) {
					alert("身份证和性别 1(男) 不匹配 ！");
					return "0";		
				}
				
/*				if (strIDCardNo.substr(17,1)=='x') {
					alert(strIDCardNo.substr(17,1));
					document.getElementById("certCode").value=strIDCardNo.substr(0,17)+'X';
				}
*/			}
	   } else if (strSex.length > 1) {
			alert("性别只能是 0(女) 或 1(男) ！");
			return "0";
	   }
	   if (obj != null) {
			obj.value = obj.value.toUpperCase();
	   }
	}
	return "1";
}

/*
 *简单密码验证，目前可实现：密码只能为至少6位的数字，密码不能为一个重复的数字、不允许为按照升序或降序排列的数字
 *组合，当第二个参数和密码全填入的情况下密码不能为第二个参数的一部分。
*/
function checkPasswd(pwd, other, otherDesc)
{
	var patrn = new RegExp(/^[0-9]{6,}$/);
	

	/* 密码字串检查 */
	if (!patrn.test(pwd))
	{
		alert("密码必须为大于或等于6位的数字 ！");
		return "0";
	}

	/* 重复数字判断 */
	var a,b,c,d;
	var count = 0;
	a=pwd.substr(0,1);	
	for (i=1;i<pwd.length;i+=1)
	{
		b=pwd.substr(i,1);
		if (a == b)
		{
			count+=1;
		}else
		{
			break;
		}
	}
	if (count+1 == pwd.length)
	{
		alert("密码不能重复为一个数字 ！");
		return "0";
	} 
	
	/* 升序判断 */
	var counter=0;
	for (j=1;j<pwd.length-1;j+=1)
	{
		c=pwd.substr(j,1);
		d=pwd.substr(j+1,1);
		if ((parseInt(a) == parseInt(pwd.substr(1,1))-1)&&(parseInt(c) == parseInt(d)-1))
		{
			counter+=1;
		}else
		{
			break;
		}
	}
	if (counter+1 ==pwd.length-1)
	{
		alert("密码不允许为按照升序排列的数字组合 ！");
		return "0";
	}
	
	/* 降序判断 */
	var counterr=0;
	for (j=1;j<pwd.length-1;j+=1)
	{
		c=pwd.substr(j,1);
		d=pwd.substr(j+1,1);
		if ((parseInt(a) == parseInt(pwd.substr(1,1))+1)&&(parseInt(c) == parseInt(d)+1))
		{
			counterr+=1;
		}else
		{
			break;
		}
	}
	if (counterr+1 ==pwd.length-1)
	{
		alert("密码不允许为按照降序排列的数字组合 ！");
		return "0";
	}
	
	/* 子串判断 */
	if (pwd.length <= other.length)
	{
		var e=other.length-pwd.length;
		for (m=0;m<e+1;m+=1)
		{
			if (pwd == other.substr(m,pwd.length))
			{
				if (otherDesc.length === 0)
				{
					alert("作为第二个参数连带的另一个参数也需要填写 ！");
					return "0";
				}
				alert("密码不能为 "+otherDesc+" 的一部分 ！");
				return "0";
			}	
		}
	}
	if (other.length !== 0 && other.length < pwd.length)
	{
		if (otherDesc.length === 0)
		{
			alert("作为第二个参数连带的另一个参数也需要填写 ！");
			return "0";
		}
		alert(otherDesc+" 的长度不能小于密码的长度，请重新填写 ！");
		return "0";
	}
	return "1";
}

/**
 * 联系人控件效验
 */	
function linkmanEfficacy(obj) {

var PHONE = new RegExp(/^[0-9]{1,11}$/);
var LINKMANTEL = new RegExp(/^[0-9,-]{1,13}$/);

//var i = obj.flag.value.trim();
var i = document.getElementById("flagCount").value.trim();
var j=0;

for(j; j < i; j++) {

if (document.getElementById("linkman"+j) != null) {
	if (document.getElementById("linkman"+j).value.trim() == "") {
			alert("请输入联系人姓名");
			document.getElementById("linkman"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkman"+j).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanDepartment"+j).value.trim() == "") {
			alert("请输入联系人部门");
			document.getElementById("linkmanDepartment"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanDepartment"+j).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanBusiness"+j).value.trim() == "") {
			alert("请输入联系人职务");
			document.getElementById("linkmanBusiness"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanBusiness"+j).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanTel"+j).value.trim() == "") {
			alert("请输入联系人电话,且小于13位");
			document.getElementById("linkmanTel"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanTel"+j).style.border = "1px solid #ff6600";
			return false;
		}
		if (!LINKMANTEL.test(document.getElementById("linkmanTel"+j).value.trim())) {
			alert("联系人电话只能为数字,且小于13位");
			document.getElementById("linkmanTel"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanTel"+j).style.border = "1px solid #ff6600";
			return false;
		}
		
		if (document.getElementById("linkmanphone"+j).value.trim() == "") {
			alert("请输入联系人手机");
			document.getElementById("linkmanphone"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanphone"+j).style.border = "1px solid #ff6600";
			return false;
		}
		if (!PHONE.test(document.getElementById("linkmanphone"+j).value.trim())) {
			alert("联系人手机只能为数字,且小于11位的");
			document.getElementById("linkmanphone"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanphone"+j).style.border = "1px solid #ff6600";
			return false;
		}
		
		
		if (document.getElementById("linkmanEmail"+j).value.trim() == "") {
			alert("请输入联系人电子邮件");
			document.getElementById("linkmanEmail"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanEmail"+j).style.border = "1px solid #ff6600";
			return false;
		}
		if (CheckEmail(document.getElementById("linkmanEmail"+j).value.trim())) {
			alert("邮箱格式为xxxx@xxx.xxx");
			document.getElementById("linkmanEmail"+j).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanEmail"+j).style.border = "1px solid #ff6600";
			return false;
		}
		
	}
}	
	var k = obj.flagEditCount.value.trim();
	var m = 0;

	for(m; m < k; m++) {

		if (document.getElementById("linkmanEdit" + m).value.trim() == "") {
			alert("请输入联系人姓名");
			document.getElementById("linkmanEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanDepartmentEdit"+m).value.trim() == "") {
			alert("请输入联系人部门");
			document.getElementById("linkmanDepartmentEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanDepartmentEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanBusinessEdit"+m).value.trim() == "") {
			alert("请输入联系人职务");
			document.getElementById("linkmanBusinessEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanBusinessEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanTelEdit"+m).value.trim() == "") {
			alert("请输入联系人电话,且小于13位");
			document.getElementById("linkmanTelEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanTelEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (!LINKMANTEL.test(document.getElementById("linkmanTelEdit"+m).value.trim())) {
			alert("联系人电话只能为数字,且小于13位");
			document.getElementById("linkmanTelEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanTelEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		
		if (document.getElementById("linkmanphoneEdit"+m).value.trim() == "") {
			alert("请输入联系人手机");
			document.getElementById("linkmanphoneEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanphoneEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (!PHONE.test(document.getElementById("linkmanphoneEdit"+m).value.trim())) {
			alert("联系人手机只能为数字,且小于11位的");
			document.getElementById("linkmanphoneEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanphoneEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (document.getElementById("linkmanEmailEdit"+m).value.trim() == "") {
			alert("请输入联系人电子邮件");
			document.getElementById("linkmanEmailEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanEmailEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
		if (CheckEmail(document.getElementById("linkmanEmailEdit"+m).value.trim())) {
			alert("邮箱格式为xxxx@xxx.xxx");
			document.getElementById("linkmanEmailEdit"+m).style.backgroundColor = "#fff2e9";
			document.getElementById("linkmanEmailEdit"+m).style.border = "1px solid #ff6600";
			return false;
		}
	}
	
	

	return true;

}
