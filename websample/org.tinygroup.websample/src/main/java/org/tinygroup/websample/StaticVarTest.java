package org.tinygroup.websample;

public final class StaticVarTest {

	private static int var = 0;

	public static int addVar() {
		var++;
		System.out.println(var);
		return var;
	}

}
