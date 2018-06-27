package com.kangwencai.common.utils.encrypt;

import java.security.MessageDigest;

public class MD5Encrypt {

	/**
	 * 进行base64解码，再进行MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * 生成数字签名，数字签名的生成方式为：1、进行MD5加密，取结果的第5~20位再次MD5加密。2、再取第二次的加密结果 的4~19位作为数字签名
	 * 
	 * @param str
	 */
	public static String doubleMD5Encrypt(String str) {
		// 1、
		String result = MD5Encrypt.getMD5Str(str);
//		Log.e("", "第一次" + result);
		result = MD5Encrypt.getMD5Str(result.substring(5, 21));
//		Log.e("", "第二次" + result);
		// 2.
		result = result.substring(4, 20);
		return result;
	}

}
