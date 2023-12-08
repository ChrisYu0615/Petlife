package com.petlife.util;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class RandomAuthenCode {

	public static String setAuthenCode(String memberType, String account) {
		Jedis jedis = new Jedis("localhost", 6379);

		String code = returnAuthCode();

		jedis.set(memberType + ":" + account, code);
		jedis.expire(memberType + ":" + account, 600);

		jedis.close();
		return code;
	}

	public static String getAuthenCode(String memberType, String account) {
		Jedis jedis = new Jedis("localhost", 6379);
		
		String authenCodeFromJedis = jedis.get(memberType + ":" + account);
		
		jedis.close();
		
		return authenCodeFromJedis;
	}

	private static String returnAuthCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			int condition = (int) (Math.random() * 3) + 1;
			switch (condition) {
			case 1:
				char c1 = (char) ((int) (Math.random() * 26) + 65);
				sb.append(c1);
				break;
			case 2:
				char c2 = (char) ((int) (Math.random() * 26) + 97);
				sb.append(c2);
				break;
			case 3:
				sb.append((int) (Math.random() * 10));
			}
		}
		return sb.toString();
	}

}
