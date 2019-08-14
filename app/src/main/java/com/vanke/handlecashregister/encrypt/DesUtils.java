package com.vanke.handlecashregister.encrypt;

import android.util.Base64;

import java.net.URLEncoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DesUtils
{
  private static final String ALGORITHM_DES = "DES/ECB/PKCS5Padding";

  public static String encryptDES(String paramString1, String paramString2)
    throws Exception
  {
    Key localKey = toKey(paramString2.getBytes());
    Cipher localCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    localCipher.init(1, localKey);
    return URLEncoder.encode(Base64Helper.encode(localCipher.doFinal(paramString1.getBytes("UTF-8"))), "UTF-8");
  }

  private static Key toKey(byte[] paramArrayOfByte)
    throws Exception
  {
    DESKeySpec localDESKeySpec = new DESKeySpec(paramArrayOfByte);
    return SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec);
  }
}

/* Location:           C:\Users\IT\Desktop\ApkIDE\classes2_dex2jar.jar
 * Qualified Name:     com.vanke.hisense.utils.DesUtils
 * JD-Core Version:    0.6.2
 */