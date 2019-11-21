package com.lang.unicode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Stack overflow question asked in connection with this is
 * https://stackoverflow.com/questions/58839796/utf-16-encoding-appeding-extra-bytes
 */
public class UnicodeConversion
{
    public static void main(String[] args) throws IOException
    {
        String jaString = new String("\u20AC\u20AC");
        System.out.println(jaString);
      //  System.out.println(Arrays.toString(jaString.getBytes()));
       // System.out.println(Arrays.toString(jaString.getBytes("UTF8")));
        System.out.println(Arrays.toString(jaString.getBytes("UTF-16BE")));

        String base64EncodedString = Base64.getEncoder().encodeToString(jaString.getBytes("UTF-8"));
        System.out.println(base64EncodedString);
        byte[] decodedBase64Bytes = Base64.getDecoder().decode(base64EncodedString);
        System.out.println(Arrays.toString(decodedBase64Bytes));
        String decodedBase64String = new String(decodedBase64Bytes,"UTF-8");
        System.out.println(decodedBase64String);
/*
        char c = 1<<15;
        System.out.println((int)c);
        System.out.println(c);
        char [] chars = {c};
        String str = new String(chars);
        System.out.println(str.length());
        System.out.println((byte)c);

        byte c2 = (byte)(1<<7);
        System.out.println(c2);
        System.out.println(Byte.toUnsignedInt(c2));
        System.out.println((byte)(int)c2);*/

        byte[] bytes = Base64.getEncoder().encodeToString(jaString.getBytes("UTF-16BE")).getBytes(StandardCharsets.US_ASCII);
        System.out.println(Arrays.toString(bytes));
        String st = new String(Base64.getDecoder().decode(new String(bytes,StandardCharsets.US_ASCII)),"UTF-16BE");
        //System.out.println(Arrays.toString(jaString.getBytes(StandardCharsets.US_ASCII)));
        //System.out.println(new String(jaString.getBytes(StandardCharsets.US_ASCII),StandardCharsets.US_ASCII));
        System.out.println(st+"  ==  "+st.equalsIgnoreCase(jaString));

         bytes = jaString.getBytes("UTF-8");
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);
        DataOutputStream fileOutputStream = new DataOutputStream(new FileOutputStream("/u/motamari/unicode/testFile3"));
        //fileOutputStream.writeUTF(jaString);
        fileOutputStream.write(bytes);
        fileOutputStream.close();


    }
}
