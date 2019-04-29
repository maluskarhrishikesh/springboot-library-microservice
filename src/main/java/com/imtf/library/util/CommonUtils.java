package com.imtf.library.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CommonUtils {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		CommonUtils bcTest = new CommonUtils();
		String hashed = bcTest.generateHash("test");
		boolean verify = bcTest.verifyHash("test", hashed);
		System.out.println("Hashed=" + hashed);
		System.out.println("Vreified=" + verify);
	}

	public static String generateHash(String plainText) throws NoSuchAlgorithmException {
		String salt = BCrypt.gensalt(10, SecureRandom.getInstance("SHA1PRNG"));
		return BCrypt.hashpw(plainText, salt);
	}

	public static boolean verifyHash(String plainText, String cipher) {
		return BCrypt.checkpw(plainText, cipher);
	}
}
