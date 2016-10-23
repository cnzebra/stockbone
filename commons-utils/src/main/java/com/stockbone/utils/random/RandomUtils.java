package com.stockbone.utils.random;

import java.util.UUID;

public class RandomUtils {

	public static String randomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
