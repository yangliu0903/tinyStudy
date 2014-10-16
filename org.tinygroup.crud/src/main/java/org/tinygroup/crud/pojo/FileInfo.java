package org.tinygroup.crud.pojo;

public class FileInfo {
    
	private String name;
	private long size;
	
	public FileInfo(){
		
	}
	
    public FileInfo(String name,long size){
    	setName(name);
    	setSize(size);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
