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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.xmlparser.node.XmlNode;

public class BaseInfosBuilder {

	private Map<String, BaseInfo> maps = new HashMap<String, BaseInfo>();
	private Map<String, String> replaceMap = new HashMap<String, String>();
	private Map<String, Boolean> notMatch = new HashMap<String, Boolean>();
	private Logger logger = LoggerFactory.getLogger(BaseInfosBuilder.class);

	public BaseInfosBuilder(XmlNode stdNodes, XmlNode replaceNode) {
		List<XmlNode> subNodes = stdNodes.getSubNodes("standard-field");
		if (!CollectionUtil.isEmpty(subNodes)) {
			for (XmlNode subNode : subNodes) {
				String code = getNodeValue("标准编号", subNode);
				String topic = getNodeValue("标准主题", subNode);
				String chineseName = getNodeValue("标准中文名称", subNode);
				String englishName = getNodeValue("标准英文名称", subNode);
				englishName = preprocess(englishName);
				String aliasName = getNodeValue("标准别名", subNode);
				String length = getNodeValue("标准长度", subNode);
				String scale = getNodeValue("取值精度", subNode);
				BaseInfo info = new BaseInfo(code, topic, chineseName,
						englishName, aliasName, length, scale);
				maps.put(chineseName.toUpperCase(), info);
			}
		}
		List<XmlNode> nodes = replaceNode.getSubNodes("term-abbreviation");
		if (!CollectionUtil.isEmpty(subNodes)) {
			for (XmlNode node : nodes) {
				String key = node.getSubNode("Term").getPureText();
				String value = getNodeValue("Abbreviation", node);
				replaceMap.put(key.toUpperCase(), value);
			}
		}

	}

	private String preprocess(String englishName) {
		return englishName.replaceAll("'s", "")
				.replaceAll("\\.", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", " ").replaceAll("/", " ");
	}

	private String getNodeValue(String nodeName, XmlNode xmlNode) {
		XmlNode subNode = xmlNode.getSubNode(nodeName);
		if (subNode == null) {
			return null;
		}
		return subNode.getPureText();
	}

	public String getEnglishName(String chineseName) {
		BaseInfo baseInfo = getBaseInfo(chineseName);
		if (baseInfo != null) {
			return baseInfo.getEnglishName();
		}
		logger.logMessage(LogLevel.WARN, "单词：[{0}],未匹配", chineseName);
		return null;
	}

	public String getLength(String chineseName, String defaultValue) {
		BaseInfo baseInfo = getBaseInfo(chineseName);
		if (baseInfo != null) {
			return baseInfo.getLength();
		}
		return defaultValue;
	}

	public String getScale(String chineseName, String defaultValue) {
		BaseInfo baseInfo = getBaseInfo(chineseName);
		if (baseInfo != null) {
			return baseInfo.getScale();
		}
		return defaultValue;
	}

	public String getReplaceName(String orignalName) {
		String fullName = getReplaceFullName(orignalName);
		String[] array = StringUtil.split(fullName, " ");
		if (!fullName.equals(orignalName) || array.length == 1) {
			return fullName;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(getReplaceFullName(array[i]));
			if (i < array.length - 1) {
				buffer.append("_");
			}
		}
		return buffer.toString();
	}

	public String getReplaceFullName(String orignalName) {
		String repalceName = replaceMap.get(orignalName.toUpperCase());
		if (!StringUtil.isBlank(repalceName)) {// 先匹配整个单词
			return repalceName;
		}
		if (!notMatch.containsKey(orignalName.toUpperCase())) {
			logger.logMessage(LogLevel.WARN, "单词：[{0}],未匹配", orignalName);
			notMatch.put(orignalName.toUpperCase(), true);
		}
		return orignalName;
	}

	private BaseInfo getBaseInfo(String chineseName) {
		return maps.get(chineseName.toUpperCase());
	}
}
