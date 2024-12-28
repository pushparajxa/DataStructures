
package com.lang;



import java.io.*;
import java.lang.Character.UnicodeBlock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.text.StringEscapeUtils;

public class CharTest {

  private static final char[] HEX_DIGITS = {
      '0', '1', '2', '3', '4', '5', '6', '7',
      '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  public static void main(String[] args) throws UnsupportedEncodingException
  {
    boolean r[]= new boolean[3];
    System.out.println(Arrays.toString(r));
    System.out.println((int)'5' + "" + (int)'0');
    System.out.println(isLatin1(null));
    System.out.println(isLatin1(""));
    System.out.println(isLatin1("\u20AC"));
    System.out.println(isLatin1(Character.toString((char) 0x24B62)));
    System.out.println(isLatin1("adasd"));

    char [] isChars = new char[20];
    for (int i = 140; i <= 159 ; i++) {
        isChars[i-140]= (char)i;
    }
    UnicodeBlock unicodeBlock = UnicodeBlock.LATIN_1_SUPPLEMENT;
    String isoString = new String(isChars);
    System.out.println(isoString);

    char c = 'a';
    char d = 'd';
    char x = 0x20AC;
    System.out.println("Printing Record Separator");
    System.out.println((char)0x1e);
    System.out.println("Printed Record Separator");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    String n = String.valueOf((char)0x1e);
    try {
      byteArrayOutputStream.write(n.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    String out = new String(byteArrayInputStream.readAllBytes());
    System.out.println(out);

    String recordSepStr = "FirstString"+String.valueOf((char)0x1e)+
        "SecondString";
    String[] split = recordSepStr.split(String.valueOf((char) 0x1e));
    System.out.println(Arrays.toString(split));

    System.out.println((int)c);
    System.out.println((int)d);
    System.out.println(c>d);
    System.out.println(x);
    System.out.println((int)x);
    System.out.println("€€");
    System.out.println(Character.toString((char) 0x24B62));
    char [] repres = Character.toChars(0x24B62);
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.appendCodePoint(0x24B62);
    byte[] bytes = stringBuffer.toString().getBytes("UTF-8");
    System.out.println(stringBuffer.toString().equals(new String(bytes,"UTF-8")));
    System.out.println(new String(repres));
    char[] chars = Character.toChars(0x20ac);
    System.out.println(chars);
    System.out.println("new");
    System.out.println(new String("hello"+"\u20ac"));
  //  new PrintWriter(System.out, true).println("\uD852\uDF62");
    //byte[] bytes = {D8 52 DF 62};


    /**/

    String test = "已撤";
    for (int i = 0; i < test.length() ; i++) {
      char c3 = test.charAt(i);
      System.out.println(c3);
    }

    //Pattern pattern = Pattern.compile("\\p{ASCII}*");
    Pattern pattern = Pattern.compile("[^\\p{ASCII}]+");
    Matcher matcher = pattern.matcher("dsadsdad已撤单报单被拒绝SHFE:当前状态禁止此项操作Ascii\n"
            + "\n\n\ndfsdf报单\t\t\t   fsfsdfsd\r\r\r\r前状态");
    System.out.println("Macthes result "+matcher.matches());
    while(matcher.find()){
      String match = matcher.group();
      System.out.println(match);
    }
    System.out.println("Done");

    String unicode = "Hello\u007f\u20ac撤单报单被拒";
    StringBuilder builder = new StringBuilder();
    for(char ch: unicode.toCharArray()){
      if((int)ch >=128 ){
        builder.append("\\u");
        appendHexByte(builder, (byte)((ch&0xff00) >>> 8));
        appendHexByte(builder, (byte)((ch&0xff)));
      }else{
        builder.append(ch);
      }
    }

    String input = builder.toString();
    StringBuilder  unescapedStringBuilder = new StringBuilder();
    int index = 0;
    while(index<input.length()){
      if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
        if (index + 5 < input.length()) {
          // Get 4 hex digits
          final CharSequence unicode2 = input.subSequence(index + 2, index + 6);

          try {
            final int value = Integer.parseInt(unicode2.toString(), 16);
            unescapedStringBuilder.append((char)value);
          } catch (final NumberFormatException nfe) {
            throw new IllegalArgumentException("Unable to parse unicode "
                + "value: " + unicode2, nfe);
          }
          index = index + 6;
        }
        else {
          throw new IllegalArgumentException(
              "Less than 4 hex digits in unicode value: '" + input
                  .subSequence(index, input.length())
                  + "' due to end of CharSequence");
        }
      }
      else {
        unescapedStringBuilder.append(input.charAt(index));
        index++;
      }

    }

    String unescapedString = unescapedStringBuilder.toString();

  //  String result = StringEscapeUtils.escapeJava("撤单报单被拒");
   // String result2 = StringEscapeUtils.unescapeJava(result);
 //   System.out.println(result);

    String str = "撤单报单被拒";
    str="asciiString";
    System.out.println(str);
    str.getBytes();
    List<Integer> integerList = new ArrayList<>();
    for(char c2: str.toCharArray()){

    }
   /* Pattern pattern = Pattern.compile("[0-9]+");
    Matcher matcher = pattern.matcher("adasdas12323sdasdas009099zxcxzv5654654");
    System.out.println("Macthes result "+matcher.matches());
    int i=1;
    while(matcher.find()){
      String match = matcher.group();
      System.out.println((i++)+"--"+match);
    }*/

  }

  public static boolean isLatin1(String str)
  {
    if (str == null) {
      return true;
    }
    for (int i = 0; i < str.length(); ++i) {
      if (str.charAt(i) > 0xFF) {
        return false;
      }
    }
    // When the string is empty
    return true;
  }

  public static <U extends Appendable> U appendHexByte(U app, byte b)
  {
    try {
      app.append(HEX_DIGITS[(b & 0xf0) >>> 4]);
      app.append(HEX_DIGITS[b & 0xf]);
    }
    catch (IOException e) {
      // Never happens with most commonly used appendables
      // such as StringBuilder, etc.
      throw new RuntimeException(e);
    }
    return app;
  }
}
