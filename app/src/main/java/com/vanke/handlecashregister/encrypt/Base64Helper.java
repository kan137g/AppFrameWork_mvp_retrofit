package com.vanke.handlecashregister.encrypt;

import java.io.UnsupportedEncodingException;

public class Base64Helper {
    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
    private static char[] base64EncodeChars = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    public static byte[] decode(String paramString) {
        try {
            byte[] arrayOfByte = decodePrivate(paramString);
            return arrayOfByte;
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] decodePrivate(String paramString)
            throws UnsupportedEncodingException {
        StringBuffer localStringBuffer = new StringBuffer();
        byte[] arrayOfByte1 = paramString.getBytes("US-ASCII");
        int i = arrayOfByte1.length;
        int j = 0;
        if (j < i) ;
        while (true) {
            byte[] arrayOfByte2 = base64DecodeChars;
            int k = j + 1;
            int m = arrayOfByte2[arrayOfByte1[j]];
            if ((k >= i) || (m != -1)) {
                if (m == -1) ;
                int i4;
                int i7;
                do {
                    int i1;
                    do {
                        do {
                            do {
                                int n = k;
                                byte[] arrayOfByte3 = base64DecodeChars;
                                k = n + 1;
                                i1 = arrayOfByte3[arrayOfByte1[n]];
                            }
                            while ((k < i) && (i1 == -1));
                        }
                        while (i1 == -1);
                        localStringBuffer.append((char) (m << 2 | (i1 & 0x30) >>> 4));
                        do {
                            int i2 = k;
                            k = i2 + 1;
                            int i3 = arrayOfByte1[i2];
                            if (i3 == 61) {
                                byte[] arrayOfByte5 = localStringBuffer.toString().getBytes("iso8859-1");
                                return arrayOfByte5;
                            }
                            i4 = base64DecodeChars[i3];
                        }
                        while ((k < i) && (i4 == -1));
                    }
                    while (i4 == -1);
                    localStringBuffer.append((char) ((i1 & 0xF) << 4 | (i4 & 0x3C) >>> 2));
                    do {
                        int i5 = k;
                        k = i5 + 1;
                        int i6 = arrayOfByte1[i5];
                        if (i6 == 61) {
                            byte[] arrayOfByte4 = localStringBuffer.toString().getBytes("iso8859-1");
                            return arrayOfByte4;
                        }
                        i7 = base64DecodeChars[i6];
                    }
                    while ((k < i) && (i7 == -1));
                }
                while (i7 == -1);
                localStringBuffer.append((char) (i7 | (i4 & 0x3) << 6));
                j = k;
                break;
            }
            j = k;
        }
        return localStringBuffer.toString().getBytes("iso8859-1");

    }

    public static String encode(byte[] paramArrayOfByte) {
        StringBuffer localStringBuffer = new StringBuffer();
        int i = paramArrayOfByte.length;
        int j = 0;
        int k = 0, m = 0;
        if (j < i) {
            k = j + 1;
            m = 0xFF & paramArrayOfByte[j];
            if (k == i) {
                localStringBuffer.append(base64EncodeChars[(m >>> 2)]);
                localStringBuffer.append(base64EncodeChars[((m & 0x3) << 4)]);
                localStringBuffer.append("==");
            }
        }
        while (true) {
            int n = k + 1;
            int i1 = 0xFF & paramArrayOfByte[k];
            if (n == i) {
                localStringBuffer.append(base64EncodeChars[(m >>> 2)]);
                localStringBuffer.append(base64EncodeChars[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
                localStringBuffer.append(base64EncodeChars[((i1 & 0xF) << 2)]);
                localStringBuffer.append("=");
            } else {
                int i2 = n + 1;
                int i3 = 0xFF & paramArrayOfByte[n];
                localStringBuffer.append(base64EncodeChars[(m >>> 2)]);
                localStringBuffer.append(base64EncodeChars[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
                localStringBuffer.append(base64EncodeChars[((i1 & 0xF) << 2 | (i3 & 0xC0) >>> 6)]);
                localStringBuffer.append(base64EncodeChars[(i3 & 0x3F)]);
                j = i2;
                break;
            }
        }
        return localStringBuffer.toString();
    }

    public static void main(String[] paramArrayOfString)
            throws UnsupportedEncodingException {
        new String(decode(encode("abcd".getBytes())));
    }
}

/* Location:           C:\Users\IT\Desktop\ApkIDE\classes2_dex2jar.jar
 * Qualified Name:     com.vanke.hisense.utils.Base64Helper
 * JD-Core Version:    0.6.2
 */