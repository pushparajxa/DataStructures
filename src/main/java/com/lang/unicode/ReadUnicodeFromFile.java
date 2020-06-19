package com.lang.unicode;

import java.io.*;

public class ReadUnicodeFromFile
{
    public static void main(String[] args)
    {
        //String str = new String("\u65e5\u672c\u8a9e\u6587\u5b57\u5217");
        String str = new String(Character.toChars( 0x20020));
        try {
            FileOutputStream fos = new FileOutputStream("/u/motamari/test.txt");
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream("/u/motamari/test.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char)ch);
            }
            in.close();
            System.out.println(buffer.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
