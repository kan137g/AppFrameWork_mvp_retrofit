package com.vanke.handlecashregister.encrypt;

import java.security.MessageDigest;

public class MD5
{
  public static String sign(String paramString)
  {
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      char[] arrayOfChar2 = new char[2 * arrayOfByte2.length];
      int i = arrayOfByte2.length;
      int j = 0;
      int k = 0;
      while (j < i)
      {
        int m = arrayOfByte2[j];
        int n = k + 1;
        arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
        k = n + 1;
        arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
        j++;
      }
      String str = new String(arrayOfChar2);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static String sign(String paramString1, String paramString2, long paramLong)
  {
    return sign("data=" + paramString1 + "&key=" + paramString2 + "&timestamp=" + paramLong);
  }
}

/* Location:           C:\Users\IT\Desktop\ApkIDE\classes2_dex2jar.jar
 * Qualified Name:     com.vanke.hisense.security.MD5
 * JD-Core Version:    0.6.2
 */