package com.kangwencai.common.utils.encrypt;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;

import javax.crypto.Cipher;

/**
 * RSA加密的工具类
 */
public class RSA {
    // 服务器RSA公钥
	final static String SERVER_KEY ="a3b385479f82f4122e71f472f8abc72423f7b4530d232518c325c64e615c39a30a5e1f8c318b73ad2140a6b294edcdd84f7c9fb6e52025fd02c9fca2831495eeae46952f64eda3c9f3336645b545a0efb6de944298cb607bf54603bc0dd2ebee0579edc26b7bdde89f35ae8d5159c82702c05cc275a17de07afd29323279f5ab";

    final static String encryptMode = "RSA/ECB/PKCS1PADDING";
    static RSAPublicKey rsaPublicKey= parseRSAPublickKey(SERVER_KEY);

    /**
     * 服务器公钥
     **/
    public static RSAPublicKey sPubKey;
    /**
     * 客户端私钥
     **/
    public static RSAPrivateKey cPriKey;
    /**
     * 客户端公钥
     **/
//    public static RSAPublicKey cPubKey;
    public static String cPubKey;
    /**随机数**/
    public static String randomCode;

    /**
     * 生成base64编码保存的公钥和私钥1、系统生成RSA密钥对；2、按照系统协议将公钥私钥编码，再用base64编码储存到hashmap中;3、将密钥缓存到本地
     *
     * @return
     */
    public static HashMap<String, String> getKeys() {
        HashMap<String, String> keyMap = new HashMap<String, String>();
        try {
            // 初始化密钥生成器
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            // 获取密钥对
            KeyPair pair = keyGen.genKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
            // 装到hashMap里面返回
            keyMap.put(
                    "privateKey",
                    Base64.encodeToString(privateKey.getEncoded(),
                            Base64.DEFAULT).trim());
            String pubKey = Base64.encodeToString(publicKey.getEncoded(),
                    Base64.DEFAULT).trim();
            keyMap.put("publicKey", pubKey);
            saveKeys(privateKey, pubKey);
//			Log.e("PublickKey",
//					Base64.encodeToString(publicKey.getEncoded(),
//							Base64.DEFAULT).trim());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyMap;
    }

    /**
     * 将产生的密钥保存到本地,
     *
     * @param privateKey
     * @param pubKey
     * @return
     */
    private static void saveKeys(RSAPrivateKey privateKey, String pubKey) {
        cPriKey = privateKey;
        cPubKey = pubKey;
        randomCode= MathUtils.randomString(16);
    }

    /**
     * 公钥和私钥采用base64储存和传输。在使用前，用该方法将公钥解析出来
     *
     * @param str 编码为base64的公钥
     * @return公钥
     */
    public static RSAPublicKey parseRSAPublickKey(String str) {
        RSAPublicKey publicKey = null;
        try {
            X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64.decode(str,
                    Base64.DEFAULT));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKey = (RSAPublicKey) kf.generatePublic(x509);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析公钥出错");
        }
        return publicKey;
    }

    /***
     * 保存服务器RSA公钥
     *
     * @param pubKey
     */
    public static void svaesPubKey(String pubKey) {
        sPubKey = parseRSAPublickKey(pubKey);
    }

    /**
     * 公钥和私钥采用base64储存和传输。在使用前，用该方法将私钥解析出来
     *
     * @param str 编码为base64的私钥
     * @return私钥
     */
    public static RSAPrivateKey parseRSAPrivateKey(String str) {
        RSAPrivateKey privateKey = null;

        try {
            // X509EncodedKeySpec x509 = new
            // X509EncodedKeySpec(Base64.decode(str));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                    Base64.decode(str, Base64.DEFAULT));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("", "解析私钥出错");
        }

        return privateKey;
    }

    /**
     * 使用服务器的RSA加密，加密完之后使用base64编码方便传输
     *
     * @param data
     * @return
     */
    public static String encryptByRSA(String data) {
        try {

            int length = rsaPublicKey.getModulus().bitLength() / 8;
            Cipher cipher = Cipher.getInstance(encryptMode);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            String[] splitString = SplitString(data, length - 11);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes;
            for (String s : splitString) {
                bytes = cipher.doFinal(s.getBytes());
                baos.write(bytes);
            }
            bytes = baos.toByteArray();
            // 加密完成后采用base64传输
            return Base64.encodeToString(bytes, Base64.DEFAULT).trim();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加密失败");
            return null;
        }
    }

    /**
     * RSA加密:首先实例化Cipher
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String encryptByRSA(String data, RSAPublicKey publicKey) {
        try {

            int length = publicKey.getModulus().bitLength() / 8;
            // int y = data.length() % length;
            Cipher cipher = Cipher.getInstance(encryptMode);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            String[] splitString = SplitString(data, length - 11);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes;
            for (String s : splitString) {
                bytes = cipher.doFinal(s.getBytes());
                baos.write(bytes);
            }
            bytes = baos.toByteArray();
            // return Array2String(bytes);
            // 加密完成后采用base64传输
            return Base64.encodeToString(bytes, Base64.DEFAULT).trim();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加密失败");
            return null;
        }
    }

    /**
     * 解密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String DecryptByRSA(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(encryptMode);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int length = privateKey.getModulus().bitLength() / 8;
            byte[] bytes = Base64.decode(data, Base64.DEFAULT);
            StringBuffer decryptStr = new StringBuffer();
            byte[] byteary;
            for (int i = 0; i < bytes.length / length; i++) {
                byteary = Arrays.copyOfRange(bytes, i * length, i * length
                        + length);
                // 这地方仍旧用的是系统默认的编码方式，服务器用的是base64
                decryptStr.append(new String(cipher.doFinal(byteary)));
            }
            return decryptStr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("", "解密失败");
        }

        return null;
    }

    public static String DecryptByRSA(String data, String privateKey) {
        RSAPrivateKey priKey = parseRSAPrivateKey(privateKey);
        String result = DecryptByRSA(data, priKey);
//		Log.e("", "加密后立即解密" + result);
        return result;
    }

    /**
     * 将字符串拆分成固定长度字符串组成的数组
     *
     * @param data
     * @param length
     * @return
     */
    public static String[] splitString2StrArray(String data, int length) {
        String[] resultArray;
        int arrayLength = 0;
        if (data.length() % length == 0) {
            arrayLength = data.length() / length;
        } else {
            arrayLength = data.length() / length + 1;
        }
        resultArray = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            if (i == arrayLength - 1) {
                resultArray[i] = data.substring(i * length, data.length());
            } else {
                resultArray[i] = data
                        .substring(i * length, i * length + length);
            }
        }
        return resultArray;
    }

    /**
     * 将字符串转化为byte型字符数组 1、每两个字符转组成一个byte
     *
     * @param data
     * @return
     */
    public static byte[] String2HexArray(String data) {

        byte[] result = new byte[(data.length() + 1) / 2];
        char c1, c2;
        byte b;
        for (int i = 0; i < result.length; i++) {
            c1 = data.charAt(2 * i);
            c2 = data.charAt(2 * i + 1);
            b = (byte) ((char2byte(c1) << 4) + char2byte(c2));
            result[i] = b;
        }
        return result;
    }

    /**
     * 将字符转为对应的16进制数
     *
     * @param c
     * @return
     */
    private static byte char2byte(char c) {
        byte result = 0;
        if ('0' <= c && c <= '9') {
            result = (byte) (c - '0');
        } else if ('a' <= c && c <= 'f') {
            result = (byte) (c - 'a' + 10);
        } else if ('A' <= c && c <= 'F') {
            result = (byte) (c - 'A' + 10);
        } else {
            result = (byte) (c - 48);
        }
        return result;
    }

    /**
     * 将字符数组转化为字符串，字符小于16的时候要在前面加0
     *
     * @param bytes
     * @return
     */
    @SuppressWarnings("unused")
    private static String Array2String(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        String stmp;
        for (byte b : bytes) {
            stmp = Integer.toHexString(b & 0xff);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString();
    }

    /**
     * RSA加解密最大长度为117个字节，所以当字节数过长时候要把原始明文拆分成为117byte长度的字符串
     *
     * @param data   原始明文
     * @param length 拆分的数据长度
     * @return
     */
    private static String[] SplitString(String data, int length) {
        String[] result;
        // 计算返回的数组大小
        int x = data.length() / length;
        int y = data.length() % length;
        if (y != 0) {
            x += 1;
        }
        result = new String[x];
        // 拆分
        for (int i = 0; i < x; i++) {
            if (y != 0 & i == x - 1) {
                result[i] = data.substring(i * length, data.length());
            } else {
                result[i] = data.substring(i * length, (i + 1) * length);
            }
        }
        return result;
    }

    /**
     * 公钥私钥使用明文保存和传输的时候，采用该方法获得公钥
     *
     * @param modulus  模数
     * @param exponent 公钥指数
     * @return公钥
     */
    public static RSAPublicKey getRSAPublicKey(String modulus, String exponent) {

        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec spec = new RSAPublicKeySpec(b1, b2);
            RSAPublicKey publickKey = (RSAPublicKey) factory
                    .generatePublic(spec);
            return publickKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥私钥使用明文保存和传输的时候，采用该方法获得私钥
     *
     * @param modulus  模数
     * @param exponent 私钥指数
     * @return私钥
     */
    public static RSAPrivateKey getRSAPrivateKey(String modulus, String exponent) {

        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            RSAPrivateKey privateKey = (RSAPrivateKey) factory
                    .generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
