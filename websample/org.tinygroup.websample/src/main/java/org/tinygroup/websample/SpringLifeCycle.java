package org.tinygroup.websample;

import org.springframework.context.Lifecycle;

public class SpringLifeCycle implements Lifecycle {

	public void start() {
         System.out.println("------SpringLifeCycle starting-------");
	}

	public void stop() {
		 System.out.println("------SpringLifeCycle stoped-------");
	}

	public boolean isRunning() {
		return true;
	}

}
