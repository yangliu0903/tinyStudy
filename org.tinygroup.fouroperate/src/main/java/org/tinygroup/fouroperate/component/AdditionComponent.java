package org.tinygroup.fouroperate.component;

import org.tinygroup.context.Context;

public class AdditionComponent extends AbstractFourOperateComponent {

	public void execute(Context context) {
		context.put(resultKey, number1+number2);
	}

}
