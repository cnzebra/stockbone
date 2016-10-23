package com.stockbone.spatula;

public class SpatulaFactory {

	public static Spatula getSpatula(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return (Spatula) Class.forName(className).newInstance();
	}
}
