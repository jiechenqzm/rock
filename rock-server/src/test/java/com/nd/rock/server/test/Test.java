package com.nd.rock.server.test;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		testPath();
	}

	public static void testMd5() {
		String str = "Hello World";
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());

			byte[] tmp = md5.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : tmp) {
				sb.append(Integer.toHexString(b & 0xff));
			}
			System.out.println(sb);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testPath() {

		String homePath = System.getProperties().getProperty("user.home");

		String path = homePath + File.separator + "middleware" + File.separator
				+ "rock" + File.separator + "data" + File.separator
				+ "snapshot";
		
		System.out.println(path);

	}

}
