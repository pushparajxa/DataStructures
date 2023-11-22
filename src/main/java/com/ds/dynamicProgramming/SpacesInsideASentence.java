
package com.ds.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SpacesInsideASentence {

  public static void main(String[] args) {

    String input = "HaveANiceDay";
    //input = "Myn";
    HashSet<String> set = new HashSet<>();
    /*set.add("Myn");
    set.add("My");
    set.add("n");*/
    set.add("Have");
    set.add("A");
    set.add("Nice");
    set.add("Day");

    int[][] matrix = new int[input.length()][input.length()];
    int[][] spaceAfter = new int[input.length()][input.length()];

    for (int i = 0; i < input.length(); i++) {
      if (set.contains(input.substring(i, i + 1))) {
        matrix[i][i] = 1;
      }
    }

    for (int i = 2; i <= input.length(); i++) {
      for (int j = 0; j + i - 1 <= input.length() - 1; j++) {
        for (int k = j; k <= j + i - 1; k++) {
          if (k == j + i - 1) {
            if (set.contains(input.substring(j, j + i))) {
              matrix[j][j + i - 1] = i;
              spaceAfter[j][j + i - 1] = j + i - 1;
            } else {
              matrix[j][j + i - 1] = 0;
            }
          } else {
            if (matrix[j][k] != 0 && matrix[k + 1][j + i - 1] != 0
                && matrix[j][k] + matrix[k + 1][j + i - 1] == i) {
              matrix[j][j + i - 1] = i;
              spaceAfter[j][j + i - 1] = k;
              break;
            }
          }
        }

      }
    }

    System.out.println(matrix[0][input.length() - 1]);

    for (int i = 0; i < input.length(); i++) {
      System.out.println(Arrays.toString(matrix[i]));
    }

    System.out.println("Spaces After Index");

    for (int i = 0; i < input.length(); i++) {
      System.out.println(Arrays.toString(spaceAfter[i]));
    }

    if (matrix[0][input.length() - 1] == 0) {
      System.out.println("Printing the line with spaces is not possible");
    }

    int i = spaceAfter[0][input.length() - 1];
    ArrayList<Integer> spaceIndices = new ArrayList<>();
    spaceIndices.add(i);
    while (i < input.length() - 1) {
      i = spaceAfter[i + 1][input.length() - 1];
      spaceIndices.add(i);
    }

    spaceIndices.remove(Integer.valueOf(input.length() - 1));// Since it is end of sentence, we
    // don't need a
    // space
    // there.

    System.out.println("Spaces will be after the following indices");
    System.out.println(spaceIndices);

    char[] result = new char[input.length() + spaceIndices.size()];
    int count = 0;
    for (int j = 0; j < input.length(); j++) {
      if (spaceIndices.contains(j)) {
        result[count++] = input.charAt(j);
        result[count++] = ' ';
      } else {
        result[count++] = input.charAt(j);
      }
    }

    System.out.println("**Sentence after putting spaces = " + new String(result) + "**");

  }

}
