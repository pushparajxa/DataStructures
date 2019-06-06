
package com.strings;

import java.util.HashMap;
import java.util.Map;

public class BayersMooreAlgorithm {

  public static void main(String[] args) {
    String inputStrig="abacabddggjjabacab";
    String patternString="abacab";

    BayersMooreAlgorithm bayersMooreAlgorithm = new BayersMooreAlgorithm();
    bayersMooreAlgorithm.multiMatch(inputStrig, patternString);

  }

  private void match(String inputString, String patternString){

    char [] input = inputString.toCharArray();
    char [] pattern = patternString.toCharArray();

    Map<Character, Integer> indices = getIndices(pattern);

    int i=pattern.length-1;
    int j=pattern.length-1;

    int pLen = pattern.length;

    while(i<input.length){
      if(input[i] == pattern[j]){
        if(j==0){
          System.out.println("There is a match from start="+i+" and end at "+(i+pLen-1));
          return ;
        }
        j--;
        i--;
      }else if(!indices.containsKey(input[i])){
        j = pLen-1;
        i = i+pLen;
      }else if(indices.get(input[i])< j){
        i = i+pLen-1-j+j-indices.get(input[i]); // i = i+pLen-(1+indices.get(input[i]));
        j = pLen-1;
      }else{ //indices.get(input[i])> j  .. indices.get(input[i]) == j won't be possible since
        // in that case input[i] will be equal to pattern[j]
        j = pLen-1;
        i = i+pLen-1-j+1 ;// i = i+pLen-j
      }

    }

    System.out.println("There is no match");

  }

  private Map<Character,Integer> getIndices(char[] pattern) {
      HashMap<Character, Integer> result = new HashMap<>();

    for (int i = 0; i < pattern.length; i++) {
      result.put(pattern[i],i);

    }

    return result;
  }

  private void multiMatch(String inputString, String patternString){

    char [] input = inputString.toCharArray();
    char [] pattern = patternString.toCharArray();

    Map<Character, Integer> indices = getIndices(pattern);

    int i=pattern.length-1;
    int j=pattern.length-1;

    int pLen = pattern.length;

    while(i<input.length){
      if(input[i] == pattern[j]){
        if(j==0){
          System.out.println("There is a match from start="+i+" and end at "+(i+pLen-1));
          i=i+pLen-1+pLen;
          j = pLen-1;
        }else {
          j--;
          i--;
        }
      }else if(!indices.containsKey(input[i])){
        j = pLen-1;
        i = i+pLen;
      }else if(indices.get(input[i])< j){
        i = i+pLen-1-j+j-indices.get(input[i]); // i = i+pLen-(1+indices.get(input[i]));
        j = pLen-1;
      }else{ //indices.get(input[i])> j  .. indices.get(input[i]) == j won't be possible since
        // in that case input[i] will be equal to pattern[j]
        j = pLen-1;
        i = i+pLen-1-j+1 ;// i = i+pLen-j
      }

    }

    System.out.println("There is no match");

  }

}
