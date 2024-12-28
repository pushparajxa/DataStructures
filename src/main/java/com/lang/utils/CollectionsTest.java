/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.lang.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//import org.apache.commons.collections.ArrayStack;

public class CollectionsTest
{

  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    System.out.println(set);

    boolean f = true;
   // System.out.println(f |= false);
    
    ArrayList<String> al = new ArrayList<>();
    
    String s = "hello";
    
    al.add(s);
    al.add(s);
    
    System.out.println(al.size());
    
    al.add(s);
    al.add(s);
    
    System.out.println(al.size());
    
  }

}