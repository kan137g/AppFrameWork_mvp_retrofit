//package com.vanke.handlecashregister.encrypt;
//
//import java.io.UnsupportedEncodingException;
//
//public class Base64
//{
//  public static final String DEFAULT_ENCODING = "UTF-8";
//
//  public static final byte[] _decode(byte[] paramArrayOfByte)
//    throws IllegalArgumentException
//  {
//    if (paramArrayOfByte == null)
//      throw new IllegalArgumentException("byteData cannot be null");
//    byte[] arrayOfByte1 = new byte[paramArrayOfByte.length];
//    for (int i = paramArrayOfByte.length; (i - 1 > 0) && (paramArrayOfByte[(i - 1)] == 61); i--);
//    if (i - 1 == 0)
//      return null;
//    byte[] arrayOfByte2 = new byte[i * 3 / 4];
//    int j = 0;
//    if (j < i)
//    {
//      if (paramArrayOfByte[j] == 43)
//        arrayOfByte1[j] = 62;
//      while (true)
//      {
//        j++;
//        break;
//        if (paramArrayOfByte[j] == 47)
//          arrayOfByte1[j] = 63;
//        else if (paramArrayOfByte[j] < 58)
//          arrayOfByte1[j] = ((byte)(-48 + (52 + paramArrayOfByte[j])));
//        else if (paramArrayOfByte[j] < 91)
//          arrayOfByte1[j] = ((byte)(-65 + paramArrayOfByte[j]));
//        else if (paramArrayOfByte[j] < 123)
//          arrayOfByte1[j] = ((byte)(-97 + (26 + paramArrayOfByte[j])));
//      }
//    }
//    int k = 0;
//    int i3;
//    for (int m = 0; (k < i) && (m < 3 * (arrayOfByte2.length / 3)); m = i3)
//    {
//      int i1 = m + 1;
//      arrayOfByte2[m] = ((byte)(0xFC & arrayOfByte1[k] << 2 | 0x3 & arrayOfByte1[(k + 1)] >>> 4));
//      int i2 = i1 + 1;
//      arrayOfByte2[i1] = ((byte)(0xF0 & arrayOfByte1[(k + 1)] << 4 | 0xF & arrayOfByte1[(k + 2)] >>> 2));
//      i3 = i2 + 1;
//      arrayOfByte2[i2] = ((byte)(0xC0 & arrayOfByte1[(k + 2)] << 6 | 0x3F & arrayOfByte1[(k + 3)]));
//      k += 4;
//    }
//    if (k < i)
//    {
//      if (k < i - 2)
//      {
//        int n = m + 1;
//        arrayOfByte2[m] = ((byte)(0xFC & arrayOfByte1[k] << 2 | 0x3 & arrayOfByte1[(k + 1)] >>> 4));
//        (n + 1);
//        arrayOfByte2[n] = ((byte)(0xF0 & arrayOfByte1[(k + 1)] << 4 | 0xF & arrayOfByte1[(k + 2)] >>> 2));
//        return arrayOfByte2;
//      }
//      if (k < i - 1)
//      {
//        (m + 1);
//        arrayOfByte2[m] = ((byte)(0xFC & arrayOfByte1[k] << 2 | 0x3 & arrayOfByte1[(k + 1)] >>> 4));
//        return arrayOfByte2;
//      }
//      throw new IllegalArgumentException("Warning: 1 input bytes left to process. This was not Base64 input");
//    }
//    return arrayOfByte2;
//  }
//
//  public static final byte[] _encode(byte[] paramArrayOfByte)
//  {
//    if (paramArrayOfByte == null)
//      throw new IllegalArgumentException("byteData cannot be null");
//    byte[] arrayOfByte = new byte[4 * ((2 + paramArrayOfByte.length) / 3)];
//    int i = 0;
//    int j = 0;
//    while (i < -2 + paramArrayOfByte.length)
//    {
//      int i2 = j + 1;
//      arrayOfByte[j] = ((byte)(0x3F & paramArrayOfByte[i] >>> 2));
//      int i3 = i2 + 1;
//      arrayOfByte[i2] = ((byte)(0xF & paramArrayOfByte[(i + 1)] >>> 4 | 0x3F & paramArrayOfByte[i] << 4));
//      int i4 = i3 + 1;
//      arrayOfByte[i3] = ((byte)(0x3 & paramArrayOfByte[(i + 2)] >>> 6 | 0x3F & paramArrayOfByte[(i + 1)] << 2));
//      j = i4 + 1;
//      arrayOfByte[i4] = ((byte)(0x3F & paramArrayOfByte[(i + 2)]));
//      i += 3;
//    }
//    int m;
//    int k;
//    if (i < paramArrayOfByte.length)
//    {
//      m = j + 1;
//      arrayOfByte[j] = ((byte)(0x3F & paramArrayOfByte[i] >>> 2));
//      if (i < -1 + paramArrayOfByte.length)
//      {
//        int n = m + 1;
//        arrayOfByte[m] = ((byte)(0xF & paramArrayOfByte[(i + 1)] >>> 4 | 0x3F & paramArrayOfByte[i] << 4));
//        int i1 = n + 1;
//        arrayOfByte[n] = ((byte)(0x3F & paramArrayOfByte[(i + 1)] << 2));
//        j = i1;
//      }
//    }
//    else
//    {
//      k = 0;
//      label229: if (k >= j)
//        break label364;
//      if (arrayOfByte[k] >= 26)
//        break label283;
//      arrayOfByte[k] = ((byte)(65 + arrayOfByte[k]));
//    }
//    while (true)
//    {
//      k++;
//      break label229;
//      j = m + 1;
//      arrayOfByte[m] = ((byte)(0x3F & paramArrayOfByte[i] << 4));
//      break;
//      label283: if (arrayOfByte[k] < 52)
//        arrayOfByte[k] = ((byte)(-26 + (97 + arrayOfByte[k])));
//      else if (arrayOfByte[k] < 62)
//        arrayOfByte[k] = ((byte)(-52 + (48 + arrayOfByte[k])));
//      else if (arrayOfByte[k] < 63)
//        arrayOfByte[k] = 43;
//      else
//        arrayOfByte[k] = 47;
//    }
//    label364:
//    while (k < arrayOfByte.length)
//    {
//      arrayOfByte[k] = 61;
//      k++;
//    }
//    return arrayOfByte;
//  }
//
//  public static String decode(byte[] paramArrayOfByte)
//    throws UnsupportedEncodingException
//  {
//    return decode(paramArrayOfByte, "UTF-8");
//  }
//
//  public static String decode(byte[] paramArrayOfByte, String paramString)
//    throws UnsupportedEncodingException
//  {
//    if (paramArrayOfByte == null)
//      throw new IllegalArgumentException("encoded cannot be null");
//    return new String(_decode(paramArrayOfByte), paramString);
//  }
//
//  public static final byte[] decode(String paramString)
//    throws UnsupportedEncodingException
//  {
//    return decode(paramString, "UTF-8");
//  }
//
//  public static final byte[] decode(String paramString1, String paramString2)
//    throws IllegalArgumentException, UnsupportedEncodingException
//  {
//    if (paramString1 == null)
//      throw new IllegalArgumentException("encoded cannot be null");
//    return _decode(paramString1.getBytes(paramString2));
//  }
//
//  public static String encode(byte[] paramArrayOfByte)
//    throws UnsupportedEncodingException
//  {
//    return encode(paramArrayOfByte, "UTF-8");
//  }
//
//  public static String encode(byte[] paramArrayOfByte, String paramString)
//    throws UnsupportedEncodingException
//  {
//    if (paramArrayOfByte == null)
//      throw new IllegalArgumentException("byteData cannot be null");
//    return new String(_encode(paramArrayOfByte), paramString);
//  }
//
//  public static byte[] encode(String paramString)
//    throws UnsupportedEncodingException
//  {
//    return encode(paramString, "UTF-8");
//  }
//
//  public static byte[] encode(String paramString1, String paramString2)
//    throws UnsupportedEncodingException
//  {
//    if (paramString1 == null)
//      throw new IllegalArgumentException("string cannot be null");
//    return _encode(paramString1.getBytes(paramString2));
//  }
//}
//
///* Location:           C:\Users\IT\Desktop\ApkIDE\classes2_dex2jar.jar
// * Qualified Name:     com.hisense.security.Base64
// * JD-Core Version:    0.6.2
// */