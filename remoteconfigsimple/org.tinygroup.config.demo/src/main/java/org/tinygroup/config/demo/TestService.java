/**
 * 
 */
package org.tinygroup.config.demo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author yanwj06282
 *
 */
public class TestService {

	@Value("${name1}")
	String pro1;
	
	String pro2;
	
	String pro3;
	
	String pro4;
	
	public void setPro1(String pro1) {
		this.pro1 = pro1;
	}
	
	public void setPro2(String pro2) {
		this.pro2 = pro2;
	}

	public void setPro3(String pro3) {
		this.pro3 = pro3;
	}

	public void setPro4(String pro4) {
		this.pro4 = pro4;
	}
	
}
