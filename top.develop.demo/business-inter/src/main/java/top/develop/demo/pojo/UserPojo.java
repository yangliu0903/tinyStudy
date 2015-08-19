package top.develop.demo.pojo;

/**
 * @author yancheng11334
 *
 */
public class UserPojo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2930237314471358250L;

	/** 唯一ID */
	private Integer id;

	/** 姓名 */
	private String name;

	/** 年龄 */
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
