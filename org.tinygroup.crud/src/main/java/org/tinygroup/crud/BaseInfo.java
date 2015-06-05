package org.tinygroup.crud;

public class BaseInfo {
	
	private String code;
	private String topic;
	private String chineseName;
	private String englishName;
	private String aliasName;
	private String  length;
	private String  scale;
	
	public BaseInfo() {
		super();
	}
	public BaseInfo(String code, String topic, String chineseName,
			String englishName, String aliasName, String length, String scale) {
		super();
		this.code = code;
		this.topic = topic;
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.aliasName = aliasName;
		this.length = length;
		this.scale = scale;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	
}
