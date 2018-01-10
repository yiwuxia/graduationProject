package com.lewky.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

//用来做一些常用的操作
public class WebTools {

	// 获取一个唯一的id
	public static String createNewId() {
		String id = UUID.randomUUID().toString();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			byte[] bs = md.digest(id.getBytes());
			BASE64Encoder base = new BASE64Encoder();
			id = base.encode(bs);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
}
