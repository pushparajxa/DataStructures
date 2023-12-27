/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.util;

import java.util.HashSet;
import java.util.Set;

public class CollectionsTest
{

  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    System.out.println(set);

    boolean f = true;
    System.out.println(f |= false);


  }

}