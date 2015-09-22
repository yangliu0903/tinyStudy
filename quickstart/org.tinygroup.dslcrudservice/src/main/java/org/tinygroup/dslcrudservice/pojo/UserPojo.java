package org.tinygroup.dslcrudservice.pojo;

public class UserPojo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6824300888312733906L;

	/** ID */
	private Integer id;

	/** NAME */
	private String name;

	/** AGE */
	private Integer age;


	public void setId(Integer id){
		this. id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setName(String name){
		this. name = name;
	}

	public String getName(){
		return name;
	}

	public void setAge(Integer age){
		this. age = age;
	}

	public Integer getAge(){
		return age;
	}
}
