package org.tinygroup.bundletestsample;

public class ShipService {
	public void shipService(Pack pack){
		System.out.println("shipped: "+pack.getPacknum());
		System.out.println("contain: "+pack.getGoods().getName());
		System.out.println("    num: "+pack.getNum());
		
	}
}
