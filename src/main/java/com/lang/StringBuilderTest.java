/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.lang;

public class StringBuilderTest {

  public static void main(String[] args)
  {
    StringBuffer stringBuilder = new StringBuffer();
    stringBuilder.append("Thsiasdhsajk hdassa");
    stringBuilder.setLength(3);

    StringBuffer stringBuilder1 = new StringBuffer();
    stringBuilder1.append("Thsiasdhsajk hdassa");

    System.out.println(stringBuilder.equals(stringBuilder1));
  }
}
