package com.hswu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private final static char[] HEX = "0123456789ABCDEF".toCharArray();

	public static String md5(String src){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");

			md.update(src.getBytes());
			byte[] b=md.digest();

			StringBuilder sBuilder = new StringBuilder();

			for (int i = 0; i < b.length; i++) {
				sBuilder.append(byte2HexStr(b[i]));
			}

			return sBuilder.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}


	private static String byte2HexStr(byte bys) {
		char[] chs = new char[2];
		chs[0] = HEX[bys >> 4 & 0xf];
		chs[1] = HEX[bys& 0xf];
		return new String(chs);
	}

}
