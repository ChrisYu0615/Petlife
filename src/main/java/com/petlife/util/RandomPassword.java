package com.petlife.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomPassword {
	private static final int MIN = 33;
	private static final int MAX = 126;
	private static final int BOUND = MAX - MIN + 1;

	public static String getNewPassword() {
		// 隨機密碼10碼，必須包含一個大寫英文、一個小寫英文、一個特殊符號、其餘隨意
		StringBuilder sb = new StringBuilder();
		// 小寫英文字
		sb.append((char) ((int) (Math.random() * 26) + 65));
		// 大寫英文字
		sb.append((char) ((int) (Math.random() * 26) + 97));
		// 特殊符號
		int condition = (int) (Math.random() * 3) + 1;
		switch (condition) {
		case 1:
			sb.append((char) ((int) (Math.random() * 15) + 33));
			break;
		case 2:
			sb.append((char) ((int) (Math.random() * 5) + 91));
			break;
		case 3:
			sb.append((char) ((int) (Math.random() * 4) + 123));
			break;
		}

		// 隨機產生7個密碼
		for (int i = 1; i <= 7; i++) {
			sb.append((char) ((int) (Math.random() * BOUND) + MIN));
		}

		char[] passwordStr = sb.toString().toCharArray();
		List<Character> passwordList = new ArrayList<>();
		for (char c : passwordStr) {
			passwordList.add(c);
		}
		return shufflePassword(passwordList).toString();
	}

	private static StringBuilder shufflePassword(List<Character> passwordList) {
		Collections.shuffle(passwordList);
		System.out.println(passwordList);

		StringBuilder newPassword = new StringBuilder();
		for (Character password : passwordList) {
			newPassword.append(password);
		}
		return newPassword;
	}
}
