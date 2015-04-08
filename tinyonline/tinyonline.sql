/*
Navicat MySQL Data Transfer

Source Server         : tiny
Source Server Version : 50532
Source Host           : localhost:3308
Source Database       : tinyonline

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2015-04-08 17:38:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `chap_id` int(11) NOT NULL,
  `chap_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`chap_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1', 'Tiny模板引擎基础语法及指令');
INSERT INTO `chapter` VALUES ('2', '控制结构语句');
INSERT INTO `chapter` VALUES ('3', '宏的定义以及调用');
INSERT INTO `chapter` VALUES ('4', '系统内嵌函数及自定义函数');
INSERT INTO `chapter` VALUES ('5', 'Tiny模板引擎之布局');
INSERT INTO `chapter` VALUES ('6', 'Tiny模板引擎综合示例');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `chap_id` int(11) DEFAULT NULL,
  `item_seqnum` int(11) DEFAULT NULL,
  `item_name` varchar(256) DEFAULT NULL,
  `example` text,
  `explanation` text,
  PRIMARY KEY (`item_id`),
  KEY `FK_Relationship_1` (`chap_id`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`chap_id`) REFERENCES `chapter` (`chap_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '1', '1', '取值表达式#{}', '#set(user={\"name\":\"程应\"})  ##赋值语句\r\n   我的名字：${user.name}', '${expression}：输出表达式的计算结果\r\n   $!{expression}：输出表达式的计算结果，并 escape 其中的 HTML 标签。\r\n   用法如下：\r\n  ${user.name}或$!{user.name}  表示读取user对象的name属性\r\n  ${user.books.size()}或$!{user.books.size()}  表示调用用户的books属性的size()方法\r\n  ${user.description}或$!{user.description} 表法显示用户的description属性');
INSERT INTO `item` VALUES ('2', '1', '2', '赋值语句#set', '#set(tips=\"hello, World\")\r\n   #set(numberArray=[1,2,3,4,5])\r\n   #set(dictMap={\"male\":1,\"female\":2})\r\n   tips值为${tips}\r\n   numberArray输出为：\r\n   #for(i:numberArray)\r\n     ${i}\r\n   #end\r\n   dictMap.male为${dictMap.male}\r\n   dictMap.female为${dictMap.female}', '    #set(name1=expression,name2=expression,[...])用于向当前上下文赋值\r\n     #!set(name1=expression,name2=expression,[...])用于向当前模板的上下文赋值');
INSERT INTO `item` VALUES ('3', '1', '3', '变量名', '##correct variable name\r\n_abc=${_abc}  #eol\r\nabc=${abc}  #eol\r\nA8=${A8}  #eol\r\nhello_=${hello_}  #eol\r\n\r\n##wrong variable name\r\n##?abc=${?abc}  #eol\r\n##9name=${9name}  #eol', '       [_a-zA-Z][_a-zA-Z$0-9]*\r\n      上面的正则表达式表示，在Tiny框架引擎中，变量必须是以下划线或大小写字母开头的，后续可以跟下划线或大小写字母和数字的字符串，才可以作为变量名。\r\n       实际上宏的名字，也是遵守同样的规则。');
INSERT INTO `item` VALUES ('4', '1', '4', '基本表达式', '#set(array=null)\r\n${array?[1]}\r\n${1+1f-0.02d} #eol\r\n${2.5f*4/2.0d} #eol\r\n${10%2} #eol\r\n${10.0%2.0} #eol\r\n${++3} #eol\r\n${3++} #eol\r\n${--2} #eol\r\n${2--} #eol\r\n${2<<1} #eol\r\n${2>>1} #eol\r\n${4|6|6} #eol\r\n${4+6+6^3|3} #eol\r\n${true?1:2} #eol\r\n${2?:1} #eol\r\n${true||false} #eol\r\n${true&&false} #eol\r\n${1==2} #eol', '  注意事项：\r\n    对于字符串常量，注意：不论是单引号框起来的字符串还是双引号框起来的字符串，都是一样的，唯一的区别有，当字符串中包含双引号或单引号的时候可以少用转义符。\r\n\r\n    对于比较运算，注意：==的执行逻辑是对两边的表达式执行equals运算。但是为了更好的进行比较，模板引擎会对表达式两边的变量转换成相同的类型然后进行比较。所有的对象都可以用比较符进行比较运算，只不过，实现了Comparable接口的类型，采用比较方法进行比较，没有实现Comparable接口的，抛出不支持操作异常。 ');
INSERT INTO `item` VALUES ('5', '1', '6', '数组常量', ' []  ##表示空数组\r\n[1..5]##等价于[1,2,3,4,5]\r\n[5..1]##等价于[5,4,3,2,1]\r\n[(1+4)..1]##等价于[5,4,3,2,1]\r\n[1,2,3,4,5]##纯数字数组\r\n[1,\"aa\",2,\"cc\",3]##数字及字符串混合数组\r\n[1，aa,2,\"cc\",3]##数字，变量，字符串混合数组\r\n#set ( list = [\"a\",\"b\",\"c\",\"d\"])\r\n#set ( i=1)\r\n 调用示例：\r\n${list[0]}\r\n${list[i+1]}\r\n 安全调用：\r\n${list?.get(1)}\r\n${list?[0]}\r\n${list?[i+1]}\r\n${items?[0]}\r\n${items?[i+1]}', '  [expression,...]\r\n\r\n  在调用的时候，如果不能确认，前面的变量是否为空，可以加一个安全调用方式：${list?[index]}或${list?.get(1)}');
INSERT INTO `item` VALUES ('6', '1', '7', 'MAP常量', ' #set(aa=1,bb=2,cc=3)\r\n#set(map={aa:1,bb:\"2\",cc:1+2})\r\n\r\n#set(mapt={\'aa\':1,\'bb\':\"2\",\'cc\':1+2})\r\n${mapt.get(\"aa\")}\r\n${mapt?.get(\"aa\")}\r\n${mapt?[\"aa\"]}\r\n${mapt[\"aa\"]}\r\n\r\n#for(m:map)\r\n   ${m?.key}=${m.value}\r\n#end', '{expression:expression,...}\r\n  {}  ##表示空Map\r\n {aa:\"aaValue\",bb:\"bbValue\"}##纯字符串Map\r\n{aa:1,bb:\"bbValue\"}##数字及字符串混合Map\r\nmap={\"aa\":1,bb:\"2\",cc:1+2}##数字，变量，表达式混合Map\r\n调用示例：\r\n如果不能确认，前面的变量是否为空，可以加一个安全调用方式：\r\n${map?.key}\r\n${map?.get(\"aa\")}\r\n${map?[\"aa\"]}');
INSERT INTO `item` VALUES ('7', '1', '8', '方法调用#@funcName(...)', '    ${format(\"a:%10s b:%10s c:%10s\",1,2,3)}\r\n    ${\"info\".length()}', '   functionName(...)\r\n   varName.functionName(...)\r\n   Tiny模板框架中内嵌有一些函数，用户可以方便的进行函数扩展，具体请查阅内置函数章节以及函数扩展章节');
INSERT INTO `item` VALUES ('8', '1', '9', '注释Comment', '   ## ${abc} #abc() 或者其它内容\r\n#--\r\n这里可以出现任意内容\r\n--#\r\n#*\r\n这里可以出现任意内容\r\n*#', '##这里是行注释内容\r\n#--这里是块注释内容 --#\r\n#* 这里是块注释内容 *#\r\n\r\n之所以支持两种块注释方式，是为了在兼容性及方便性方面提供更大的便捷。\r\n#* *#方式是为了与Velocity兼容，这样熟悉Velocity的人员更容易上手。\r\n#-- --#是为了便于把Html中的注释改成Tiny模板注释。');
INSERT INTO `item` VALUES ('9', '1', '10', '缩进排版支持', '#for(i:[1..3])\r\n    ${i}#eol\r\n    #[\r\n    #for(j:[1..3])\r\n        #t${i}*${j}=${i*j}#eol\r\n    #end\r\n    #]\r\n#end', '      #[、#{[} 缩进一格\r\n      #]、#{]} 取消缩进一格\r\n      #t、#{t} 添加当前缩进空格\r\n      #eol、#{eol} 换行\r\n      #b、#{b} 显示一个空格');
INSERT INTO `item` VALUES ('10', '1', '11', '显示文本内容', '     This is a test info.\r\n<a href=\"#\">link</a>\r\n#[[\r\n   ${abc}\r\n   #macro aa()info #end\r\n]]#', '      非指令、非注释、非表达式的所有内容\r\n      #[[...]]#，不解析文本块的所有内容\r\n\r\n      字符转义\r\n       如果有些内容本来是文本内容，但是由于与模板引擎的指令或表达式产生冲突，此时可以采用转义方式进行处理。\r\n       比如：\r\n         要输出：${abc}而不是要读取变量abc的值，则可以用这样的写法：\\${abc}\r\n         支持的转义方式有：\r\n              \\\\\r\n              \\#\r\n              \\$\r\n        比如下面的示例：\r\n        \\#macro abc()===>#macro abc()\r\n        \\${abc}===>${abc}\r\n        \\\\\\${abc}===>\\${abc}');
INSERT INTO `item` VALUES ('11', '2', '1', '条件语句#if...#else...#elseif..#end', '    #set(user={\"age\":18})\r\n\r\n      #if(user)       ##表示如果user对象不为空时，执行里面的代码块\r\n        ...\r\n      #end\r\n     #if(user.age<7)\r\n        ...小学前\r\n     #elseif(user.age<13)\r\n        ...小学\r\n      #elseif(user.age<16)\r\n        ...中学\r\n      #elseif(user.age<19)\r\n        ...高中\r\n      #else\r\n        ...高中以后\r\n    #end', '  #if(expression)\r\n   ...\r\n  #elseif(expression)\r\n   ...\r\n  #else\r\n   ...\r\n  #end');
INSERT INTO `item` VALUES ('12', '2', '2', '循环语句#for...#else#end', '#set(array=[1,2,3])\r\n#for ( x : array)\r\n    ${x}\r\n#end\r\n\r\n#set ( items = [\"a\",\"b\",\"c\",\"d\"])\r\n#for (x : items)\r\n    ${x}\r\n#end\r\n\r\n#set ( map = {\"abc\": 1, \"def\": 2})\r\n#for (x : map)\r\n    ${x.key} - ${x.value}\r\n#end', '  #for|foreach(var:expression)\r\n     ...\r\n  #else\r\n     ...\r\n  #end\r\n表示对expression进行循环处理，当expression不可以循环时，执行#else指令部分的内容。虽然foreach也表示循环，但是为了通用建议使用for.');
INSERT INTO `item` VALUES ('13', '2', '3', '循环中断', '    #for(num:[1,2,3])\r\n        ${num}\r\n        #break(num==2)\r\n     #end\r\n     表示当num的值为2的时候，跳出循环体。\r\n     它等价于：\r\n   #for(num:[1,2,3])\r\n      ${num}\r\n      #if(num==2)#break\r\n       #end\r\n    #end', '  #break(expression)\r\n  #break');
INSERT INTO `item` VALUES ('14', '2', '4', '循环继续#continue', '#for(num:[1,2,3])\r\n        #continue(num==2)\r\n        ${num}\r\n    #end\r\n   表示当num的值为2的时候，执行下一次循环。\r\n   它等价于：\r\n#for(num:[1,2,3])\r\n    #if(num==2)\r\n        #continue \r\n    #end\r\n    ${num}\r\n#end', '    #continue(expression)\r\n    #continue');
INSERT INTO `item` VALUES ('15', '2', '5', '方法停止执行#stop', '#for(num:[1,2,3])\r\n    ${num}\r\n    #stop(num==2)\r\n#end\r\n与下面的等价(由于stop停止了模板文件的执行，下面的结果并不会显示)\r\n#for(num:[1,2,3])\r\n    ${num}\r\n    #if(num==2)\r\n      #stop\r\n    #end\r\n#end', '   #stop(expression)\r\n   #stop\r\n    #stop只能继续当前当前处理，而不表示中断所有处理，比如：在模板文件里执行，只会停止模板文件的执行；在宏里执行，只能停止宏的执行。');
INSERT INTO `item` VALUES ('16', '2', '6', '行结束指令', '   第一行#{eol}第二行', '   #eol\r\n   #{eol}\r\n   表示显式输出一个\"\\r\\t\"');
INSERT INTO `item` VALUES ('17', '3', '1', '宏定义语句#macro', '  示例1：\r\n  #macro header(subTitle)\r\n    <h1>Tiny框架：${subTitle}</h1>\r\n  #end\r\n调用方式：\r\n  #header(\"homepage\")\r\n  #header(\"about\")\r\n\r\n  示例2：\r\n  #macro div()\r\n    <div>\r\n    #bodyContent\r\n    </div>\r\n  #end\r\n  #macro p()\r\n    <p>\r\n    #bodyContent\r\n    </p>\r\n  #end\r\n调用方式：\r\n \r\n#@div()\r\n    #@p()\r\n        <em>一些信息</em><b>一些内容</b>\r\n    #end\r\n#end', '     #macro macroName([varName[,varName]])\r\n        #bodyContent\r\n      #end\r\n宏的名字和变量的名字必须符合Tiny变量的定义规范，宏的参数的个数可以为0~N个。\r\n#bodyContent表示中间可以包含任意的符合Tiny模板规范的内容\r\n   注意：宏的定义支持嵌套定义\r\n     #macro aa()\r\n       aa-Content\r\n       #macro(bb)\r\n       bb-Content\r\n       #end\r\n     #end\r\n     等价于：\r\n     #macro aa()\r\n       aa-Content\r\n     #end\r\n     #macro(bb)\r\n       bb-Content\r\n     #end\r\n     考虑到代码的易读性，建议还是采用第二种的定义方式');
INSERT INTO `item` VALUES ('18', '3', '2', '模板方式定义bold宏', '    #macro bold()\r\n      <b>#bodyContent</b>\r\n    #end\r\n\r\n    #@bold()HelloWorld.#end', '     实际编写一个bold的宏，用于对宏包含的内容前后增加<b>....</b>标签\r\n     宏有两种，一种是可以嵌套内容的，一种是不可以嵌套内容的。\r\n  AbstractMacro用于不需要嵌套内容的宏继承，调用方式：#macroName()\r\n  AbstractBlockMacro 类用于需要嵌套内容的宏继承，调用方式：#@macroName()\r\n                                                                                              ......\r\n                                                                                          #end');
INSERT INTO `item` VALUES ('19', '3', '3', '模板方式定义helloworld宏', '     #macro helloWorld()\r\n         Hello, World.\r\n     #end\r\n\r\n   #@helloWorld()\r\n   #end', '   定义宏helloWorld，然后在调用helloWorld宏时，输出\"Hello, World.\"');
INSERT INTO `item` VALUES ('20', '3', '4', '宏引入语句#import', '    #import(\"/aa/aa/aa.component\") ', '      #import(expression)\r\n      #import(\"/a/b/filename\")   ##表示绝对路径,其中filename表示宏名称。\r\n      其中expression必须是一个合法的TinyTemplate表达式，具体参考表达式小节。\r\n      这个表达式的执行结果应该是一个字符串，其标示了要引入的宏的路径');
INSERT INTO `item` VALUES ('21', '3', '5', '宏调用时的参数问题', '     #macro hello(a,b,c,d,e)\r\n         a=${a}#{eol}\r\n         b=${b}#{eol}\r\n         c=${c}#{eol}\r\n         d=${d}#{eol}\r\n         e=${e}#{eol}\r\n     #end\r\n     各种调用方式：\r\n     #hello(1,2,3,4,5)    表示调用#hello的时候，a=1,b=2,c=3,d=4,e=5\r\n     #hello(1,2)  表示调用#hello的时候，a=1,b=2其它值未设定\r\n     #hello(e=5,2,3)  表示调用#hello的时候，b=2,c=3,e=5其它值未设定\r\n     #hello(e=5,d=4)  表示调用#hello的时候，d=4,e=5其它值未设定\r\n     #hello(e=5)  表示调用#hello的时候，e=5 ,其他值未设定', '    1. #call指令方式 ，#call(\"hello\",...) 只能用于无包含内容的调用\r\n     2.#macroName方式，#hello(...)，#hello(...) ，这种有无内容均可\r\n                                       ...\r\n                                    #end\r\n      命名传参用于宏的参数比较多，但是在实际使用的时候，有可能不是全部有用，这个时候就可以采用命名传参的方式。\r\n      #macroName方式适合于名字确切的场景。\r\n      #call(expression)方式，适合于名字需要动态计算的场景。');
INSERT INTO `item` VALUES ('22', '4', '1', '用call,callMacro调用宏', '      #macro macroName(a,b)\r\n         this a macro call\r\n         ${a}#eol\r\n         ${b}#eol\r\n      #end\r\n\r\n      ${call(\"macroName\",1,2)}\r\n      等价于#call(\"macroName\",1,2)\r\n      等价于#macroName(1,2)\r\n ', '  call函数的返回值为宏的运行结果。call有类似macro语法，也是支持单行调用和多行带内容调用\r\n  单行调用：\r\n  #call(\"macroName\")\r\n  #call(\"macroName\",1,2)\r\n  #callMacro(\"macroName\")\r\n  #callMacro(\"macroName\",1,2)\r\n\r\n  多行带内容调用,需要@和结束标识\r\n  #@call(\"macroName\")\r\n  ......\r\n  #end');
INSERT INTO `item` VALUES ('23', '4', '2', '格式化函数fmt,format,formatter', '    #set(name=\"cying\")\r\n    #set(result=format(\"hello,%s\",name))\r\n    ${format(\"hello,%s\",name)}', '    三个函数的作用完全相同。\r\n    format(formatString,...)\r\n    返回值：格式化后结果，字符串\r\n\r\n    format函数的底层实现是调用了java.util.Formatter实现的，因此具体如何填写，格式化串，请参考：java.util.Formatter用法 章节。');
INSERT INTO `item` VALUES ('24', '4', '3', '求值函数eval,evaluate\"', '   #set(name=\"cying\") \r\n   #set(result=eval(\"hello,${name}\"))\r\n    ${eval(\"hello,${name}\")}', '    求值函数(eval，evaluate)，用于执行一段宏代码，并返回执行结果\r\n    eval(\"模板内容\")\r\n    evaluage(templateContentVarName)\r\n    返回值：执行后的结果，字符串');
INSERT INTO `item` VALUES ('25', '4', '4', '类实例判断函数(is,instanceOf,instance)', '   ${instance(\"abc\",\"java.lang.String\",\"java.lang.Byte\")}\r\n   ${instanceOf(10,\"java.lang.Integer\")}', ' 类实例判断函数(is,instanceOf,instance) 根据传入对象判断是否为指定类的实例，返回值为true或者false.\r\n    is(object,clazz...)\r\n    instanceOf(object,clazz...)\r\n    instance(object,clazz...)');
INSERT INTO `item` VALUES ('26', '4', '5', '读取文本资源函数read,readContent', '', '    读取文本资源函数(read,readContent),用于读取指定路径的文本资源，并返回指定结果\r\n    read(\"src/test.txt\")\r\n    readContent(resourceUrl)\r\n    返回值：加载文本资源的结果，字符串');
INSERT INTO `item` VALUES ('27', '4', '6', '自定义扩展函数', '', 'tiny模板引擎支持自定义函数');
INSERT INTO `item` VALUES ('28', '5', '1', '入门示例', null, null);
INSERT INTO `item` VALUES ('29', '5', '2', '进阶示例', null, null);
INSERT INTO `item` VALUES ('30', '5', '3', '布局重写语句#layout', null, null);
INSERT INTO `item` VALUES ('31', '5', '4', '模板嵌套语句#include', null, null);
INSERT INTO `item` VALUES ('32', '6', '2', '多层宏调用示例', '#macro firstMacro()\r\n<div>\r\n    #bodyContent\r\n</div>\r\n#end\r\n#macro secondMacro()\r\n<b>\r\n    #bodyContent\r\n</b>\r\n#end\r\n#@firstMacro()\r\n    #@secondMacro()\r\n    Information\r\n    #end\r\n#end\r\n#@secondMacro()\r\n    #@firstMacro()\r\n    Information\r\n    #end\r\n#end', 'tiny模版引擎的宏支持嵌套，但要注意有内容的宏在调用时要用@符号，以区别于宏的定义。');
INSERT INTO `item` VALUES ('33', '6', '3', '宏定义中调用宏示例', '#macro firstMacro()\r\n<div>\r\n    #@secondMacro()\r\n        #bodyContent\r\n    #end\r\n</div>\r\n#end\r\n#macro secondMacro()\r\n<b>\r\n    #bodyContent\r\n</b>\r\n#end\r\n#@firstMacro()\r\n    Information\r\n#end', '注意宏定义与调用写法的区别\r\n上面示例应该得到这样的结果：\r\n<div><b>Information</b></div>\r\n');
INSERT INTO `item` VALUES ('34', '6', '4', '多次循环调用示例', '#for(i:[1..2])\r\n    #for(j:[1..2])\r\n        ${i}*${j}=${i*j}#eol\r\n    #end\r\n#end', '以下是期待的调用结果：\r\n1*1=1\r\n1*2=2\r\n2*1=2\r\n2*2=4');
INSERT INTO `item` VALUES ('35', '6', '1', '递归调用示例', '#macro printNumber(number)\r\n    ${number}#eol\r\n    #if(number<10)\r\n        #printNumber(number+1)\r\n    #end\r\n#end\r\n#printNumber(1)', '与其他语言一样，模版引擎的宏也支持递归调用');
INSERT INTO `item` VALUES ('36', '6', '5', '输出内容缩进示例', '#for(i:[1..2])\r\n${i}#eol\r\n#[\r\n    #for(j:[1..2])\r\n#t${i}*${j}=${i*j}#eol\r\n    #end\r\n#]\r\n#end', '预期结果如下：\r\n1\r\n    1*1=1\r\n    1*2=2\r\n2\r\n    2*1=2\r\n    2*2=4');
INSERT INTO `item` VALUES ('37', '6', '6', '按参数名调用宏', '#macro header(title,t)\r\n  <h1>${title}-${t}</h1>\r\n#end\r\n#header(t=\"ccccc\") \r\n#header(t=\"ccccc\",title=\"cccccc\")', 'Tiny模板语言支持按参数名调用宏。适用于宏有多个参数，而用户只想输入部分参数的场景\r\n#macro header(title,t)\r\n  <h1>${title}-${t}</h1>\r\n#end\r\n假设用户只想输入t这个参数，那么在页面调用可以采用如下写法：\r\n#header(t=\"ccccc\") \r\n#header(t=\"ccccc\",title=\"cccccc\")\r\n参数名和参数值之间用=连接，如果有多个参数可以采用英文逗号或者空格分隔，参数名的顺序不影响最终结果的执行\r\n结果如下：\r\n<h1>cccccc-ccccc</h1>');
INSERT INTO `item` VALUES ('38', '6', '7', 'Hello Tiny模板引擎 World!', '<html>\r\n<body>\r\n#set( foo = \"Tiny模板引擎\" )\r\nHello ${foo} World!\r\n</body>\r\n<html>', '一旦一个变量被赋予初值，你可以在你的HTML文档中的任意位置引用该变量。我们在下面的例子中展示：如何对变量foo进行赋值和页面引用。\r\n    以上结果是一个网页，打印“Hello Tiny模板引擎 World！”\r\n   为了让TTL包含的指令语句更具可读性，我们建议你每条TTL指令语句单独一行。虽然这不是必须的，但会使你的页面结构清晰。关于 set指令将在后面章节更详细的讲解');
INSERT INTO `item` VALUES ('39', '1', '5', '常数', '${123}#eol\r\n${123L}#eol\r\n${99.99}#eol\r\n${99.99F}#eol\r\n${99.99d}#eol\r\n${99.99e99}#eol\r\n${-123}#eol\r\n${-99.99F}#eol\r\n${-99.99E-10d}#eol\r\n${0xFF00}#eol\r\n${0xFF00l}#eol\r\n${0.001}#eol\r\n${0.001D}#eol\r\n${1.10D}#eol', '常量数字的表示方法');
