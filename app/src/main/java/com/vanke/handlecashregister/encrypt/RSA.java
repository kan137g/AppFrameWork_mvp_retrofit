package com.vanke.handlecashregister.encrypt;

 


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;

public class RSA {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    private static final String RSA = "RSA";
    private final Cipher decryptor;
    private final Cipher encryptor;

    public RSA(String paramString1, String paramString2) {
        encryptor = initEncryptor(paramString1);
        decryptor = initDecryptor(paramString2);
    }

    public static String decrypt(String paramString1, String paramString2, String paramString3) {
        return new RSA(null, paramString1).decrypt(paramString2, paramString3);
    }

    public static String encrypt(String paramString1, String paramString2, String paramString3) {
        return new RSA(paramString1, null).encrypt(paramString2, paramString3);
    }

    private Cipher initDecryptor(String paramString) {
        Cipher localCipher = null;
        if (paramString != null) ;
        try {
            PrivateKey localPrivateKey = parsePrivateKey(paramString);
            localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            localCipher.init(Cipher.DECRYPT_MODE, localPrivateKey);
            return localCipher;
        } catch (Exception localException) {
        }
        return null;
    }

    private Cipher initEncryptor(String paramString) {
        Cipher localCipher = null;
        if (paramString != null) ;
        try {
            PublicKey localPublicKey = parsePublicKey(paramString);
            localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            localCipher.init(Cipher.ENCRYPT_MODE, localPublicKey);
            return localCipher;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private static PrivateKey parsePrivateKey(String paramString) {
        try {
            RSAPrivateCrtKeySpec localRSAPrivateCrtKeySpec = new RSAPrivateCrtKeySpec(new BigInteger(1, Base64Helper.decode(substring(paramString, "<Modulus>", "</Modulus>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<Exponent>", "</Exponent>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<D>", "</D>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<P>", "</P>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<Q>", "</Q>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<DP>", "</DP>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<DQ>", "</DQ>"))), new BigInteger(1, Base64Helper.decode(substring(paramString, "<InverseQ>", "</InverseQ>"))));
            PrivateKey localPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(localRSAPrivateCrtKeySpec);
//            RSAPrivateKey privateKey= (RSAPrivateKey) localPrivateKey;
//            System.out.println( "私钥是："+Base64Helper.encode(localPrivateKey.getEncoded()).trim());
            return localPrivateKey;
        } catch (Exception localException) {
            localException.printStackTrace();

        }
        return null;
    }

    private static PublicKey parsePublicKey(String paramString)
            throws UnsupportedEncodingException {
        try {
            RSAPublicKeySpec localRSAPublicKeySpec = new RSAPublicKeySpec(new BigInteger(1, Base64Helper.decode(substring(paramString, "<Modulus>", "</Modulus>"))),
                    new BigInteger(1, Base64Helper.decode(substring(paramString, "<Exponent>", "</Exponent>"))));
            PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(localRSAPublicKeySpec);

//            System.out.println( "公钥是："+Base64Helper.encode(localPublicKey.getEncoded()).trim());

            return localPublicKey;
        } catch (Exception localException) {
        }
        return null;
    }

    public static String substring(String paramString1, String paramString2, String paramString3) {
        return paramString1.substring(paramString1.indexOf(paramString2) + paramString2.length(), paramString1.indexOf(paramString3));
    }

    public String decrypt(String paramString) {
        return decrypt(paramString, "UTF-8");
    }

    public String decrypt(String paramString1, String paramString2) {
        try {
            byte[] arrayOfByte = Base64Helper.decode(paramString1);
//            System.out.println("长度为:"+arrayOfByte.length+"密文为："+Arrays.toString(arrayOfByte));
            String str = new String(this.decryptor.doFinal(arrayOfByte), paramString2);
            return str;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public static String decrypt_2(String paramString1 ) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            RSAPrivateKey privateKey = (RSAPrivateKey) parsePrivateKey(Config.SERVER_PUBLIC_KEY_CRM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            int length = privateKey.getModulus().bitLength() / 8;
            System.out.println("每次加解密的长度是："+length);
            byte[] bytes = Base64Helper.decode(paramString1 );
            StringBuffer decryptStr = new StringBuffer();
            byte[] byteary;
            for (int i = 0; i < bytes.length / length; i++) {
                byteary = Arrays.copyOfRange(bytes, i * length, i * length
                        + length);
                // 这地方仍旧用的是系统默认的编码方式，服务器用的是Base64Helper
                decryptStr.append(new String(cipher.doFinal(byteary), "UTF-8"));
                System.out.println(decryptStr.toString());
            }
            return decryptStr.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }
    public String encrypt(String paramString) {
        return encrypt(paramString, "UTF-8");
    }

    public String encrypt(String paramString1, String paramString2) {
        try {
            byte[] arrayOfByte = paramString1.getBytes(paramString2);
            byte[] temp=this.encryptor.doFinal(arrayOfByte);
//            System.out.println("长度为:"+temp.length+"密文为："+Arrays.toString(temp));
//            System.out.println(new String(this.decryptor.doFinal(temp), paramString2));

            String str = Base64Helper.encode(temp);
            return str;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }
}

