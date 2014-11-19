
package org.tinygroup.helloworld.component;

import org.tinygroup.context.Context;
import org.tinygroup.flow.ComponentInterface;

public class HelloWorldComponent implements ComponentInterface {
	String name;
	String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void execute(Context context) {
		context.put(result, String.format("Hello, %s", name));
	}
}
