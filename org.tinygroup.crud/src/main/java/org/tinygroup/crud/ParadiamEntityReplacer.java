/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.crud;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tinygroup.commons.io.StreamUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.convert.textxml.simple.TextToXml;
import org.tinygroup.xmlparser.formatter.XmlFormater;
import org.tinygroup.xmlparser.node.XmlNode;
import org.tinygroup.xmlparser.parser.XmlStringParser;

/**
 * Created by luoguo on 2014/10/30.
 */
public class ParadiamEntityReplacer {
    public static void main(String[] args) throws Exception {
    	
//    	String test="AC000001,账户,账号,ACCOUNT NO.,对外账号$|$账户编号,编码,19,,";
//    	String[] array=StringUtil.split(test, ",");
    	
        Map<String, String> titleMap = new HashMap<String, String>();
        titleMap.put("标准编号", "标准编号");
        titleMap.put("标准主题", "标准主题");
        titleMap.put("标准中文名称", "标准中文名称");
        titleMap.put("标准英文名称", "标准英文名称");
        titleMap.put("标准别名", "标准别名");
        titleMap.put("标准格式", "标准格式");
        titleMap.put("标准长度", "标准长度");
        titleMap.put("度量单位", "度量单位");
        titleMap.put("取值范围", "取值范围");
        titleMap.put("取值精度", "取值精度");
        TextToXml textToXml = new TextToXml(titleMap, "standard-fields", "standard-field", "\r\n", ",");
        XmlNode xmlNode = new XmlStringParser().parse(textToXml.convert(StreamUtil.readText(new FileInputStream("C:\\Users\\renhui\\Desktop\\标准字段\\基础信息标准（参考版）.csv"), "GBK",true))).getRoot();
//        XmlFormater formatter = new XmlFormater();
//        System.out.println(formatter.format(xmlNode));
        Map<String, String> titleMap2 = new HashMap<String, String>();
        titleMap2.put("Term", "Term");
        titleMap2.put("Abbreviation", "Abbreviation");
        TextToXml textToXml2 = new TextToXml(titleMap2, "term-abbreviations", "term-abbreviation", "\r\n", ",");
        XmlNode xmlNode2 = new XmlStringParser().parse(textToXml2.convert(StreamUtil.readText(new FileInputStream("C:\\Users\\renhui\\Desktop\\标准字段\\Physical_Abbreviations.csv"), "GBK",true))).getRoot();
//          System.out.println(formatter.format(xmlNode2));
        BaseInfosBuilder builder=new BaseInfosBuilder(xmlNode,xmlNode2);
        XmlNode entityNode = new XmlStringParser().parse(StreamUtil.readText(new FileInputStream("C:\\Users\\renhui\\Desktop\\标准字段\\project.xml"), "UTF-8",true)).getRoot();
//        System.out.println(formatter.format(entityNode));

        List<XmlNode> dbTables = entityNode.getSubNodesRecursively("DBTable");
        for (XmlNode daTableNode: dbTables) {
			replaceName(builder, daTableNode);
			XmlNode modelChilden=daTableNode.getSubNode("ModelChildren");
			if(modelChilden!=null){
				List<XmlNode> columnNodes=modelChilden.getSubNodes("DBColumn");
				if(!CollectionUtil.isEmpty(columnNodes)){
					for (XmlNode columnNode : columnNodes) {
						String nameValue=columnNode.getAttribute("Name");
						replaceName(builder, columnNode);
						replaceProperties(nameValue,"Length", columnNode, builder);
						replaceProperties(nameValue,"Scale", columnNode, builder);
					}
				}
			}
		}
        XmlFormater formatter = new XmlFormater();
        StreamUtil.writeText(formatter.format(entityNode), new FileOutputStream("C:\\Users\\renhui\\Desktop\\标准字段\\newproject.xml"), "UTF-8", true);
//      System.out.println(formatter.format(entityNode));
//        ?entityNode.write(new FileOutputStream("C:\\Users\\renhui\\Desktop\\标准字段\\newproject.xml"), "UTF-8");
    }
    
    private static void replaceProperties(String nameValue,String propertyName,XmlNode xmlNode,BaseInfosBuilder builder){
		String propertyValue=xmlNode.getAttribute(propertyName);
		propertyValue=builder.getLength(nameValue, propertyValue);
		xmlNode.setAttribute(propertyName, propertyValue);
    }
    

	private static void replaceName(BaseInfosBuilder builder,
			XmlNode operateNode) {
		String nameValue=operateNode.getAttribute("Name");
		String engName=builder.getEnglishName(nameValue);
		if(engName!=null){
			operateNode.setAttribute("Name", builder.getReplaceName(engName));
		}
	}
}

