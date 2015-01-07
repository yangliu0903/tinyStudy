package org.tinygroup.bundletestsample;

import java.io.Serializable;

public class Pack implements Serializable{
	private String packnum;
	private Goods goods;
	private int num;
	public String getPacknum() {
		return packnum;
	}
	public void setPacknum(String packnum) {
		this.packnum = packnum;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
