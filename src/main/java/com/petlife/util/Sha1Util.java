package com.petlife.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Sha1Util {
	public static String encodePwd(String pwd) {
		byte[] pwdBytes = pwd.getBytes(StandardCharsets.UTF_8);
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(pwdBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		byte[] digest = md.digest();
		String encodeResult = byteArrayToHexString(digest);
		return encodeResult;
	}

	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			String temp = Integer.toHexString(b & 0xff);
			if (temp.length() == 1) {
				sb.append("0");
			}
			sb.append(temp);
		}
		return sb.toString();
	}
}
