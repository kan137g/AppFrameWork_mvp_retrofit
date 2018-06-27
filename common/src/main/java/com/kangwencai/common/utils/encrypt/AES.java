package com.kangwencai.common.utils.encrypt;

import android.util.Base64;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 *
 * @author 330860
 */
public class AES {
    private static final String ENCRYPTMODEL = "AES/ECB/PKCS5Padding";
    private static final String HASH_ALGORITHM_ = "SHA1PRNG";
    private static final int KEY_LENGTH = 128;
    /**AES密钥**/
    public static String aesKey;
    /**
     * 获得经base64编码后的AES密钥字符串
     *
     * @return
     */
    public static String getAESKey() {
        String result = null;
        try {
            KeyGenerator keyGenerator;
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            result = Base64.encodeToString(secretKey.getEncoded(),
                    Base64.DEFAULT).substring(0, 16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*
     * 转换密钥< br>
     *
     * @param password
     *
     * @return
     *
     * @throws Exception
     */
    public static String getAESKey(String key)  {
        KeyGenerator kgen;
        try {
            kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance(HASH_ALGORITHM_);
            secureRandom.setSeed(key.getBytes());
            kgen.init(KEY_LENGTH, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            String result = Base64.encodeToString(secretKey.getEncoded(),
                    Base64.DEFAULT).substring(0, 16);
            return result;
        } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
        }
        return null;
        // byte[] enCodeFormat = secretKey.getEncoded();
        // SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat,
        // ENCRYPTION_ALGORITHM_);
        // return secretKeySpec;
    }
    /**
     * AES加密
     *
     * @param origin    要加密的明文
     * @param secretKey 用base64编码过的密钥
     *                  ，截取0-16位
     * @return
     */
    public static String AESEncrypt(String origin, String secretKey) {
        String result = null;
        SecretKeySpec skspec = new SecretKeySpec(secretKey.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTMODEL);
            cipher.init(Cipher.ENCRYPT_MODE, skspec);
            byte[] encrypt = cipher.doFinal(origin.getBytes());
            result = Base64.encodeToString(encrypt, Base64.DEFAULT);
//			System.out.println("AES加密后的数据" + result);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AES", "AES解密出错");
        }
        return result;
    }

    /**
     * 解密
     *
     * @param origin
     * @param secretKey
     * @return
     */
    public static String AESDecrypt(String origin, String secretKey) {
        String result = null;
        SecretKeySpec skspec = new SecretKeySpec(secretKey.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTMODEL);
            cipher.init(Cipher.DECRYPT_MODE, skspec);
            byte[] decrypt = cipher.doFinal(Base64.decode(origin,
                    Base64.DEFAULT));
            result = new String(decrypt);
//			System.out.println("AES解密后的数据" + result);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AES", "AES解密出错");
        }
        return result;
    }

    /**
     * 将加密后的byte数组转化为字符换，只有一位的值要在前面加0
     *
     * @param array
     * @return
     */
    @SuppressWarnings("unused")
    private static String Array2hex(byte[] array) {
        StringBuffer result = new StringBuffer();
        String stmp = "";
        for (byte b : array) {
            stmp = Integer.toHexString(b & 0xff);
            if (stmp.length() == 1) {
                result.append("0" + stmp);
            } else {
                result.append(stmp);
            }

        }
        return result.toString();
    }

    /**
     * 将要解密的密文转化为数组，每两个字符合成一个byte型数据
     *
     * @param hexStr
     * @return
     */
    @SuppressWarnings("unused")
    private static byte[] hex2Array(String hexStr) {

        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
