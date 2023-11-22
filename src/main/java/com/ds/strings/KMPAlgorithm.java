
package com.ds.strings;

import java.util.Arrays;
//From Algorithm Design(Goodrich) book
public class KMPAlgorithm {

  public static void main(String[] args) {
     String pattern = null,input=null;

     KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();

   // System.out.println(Arrays.toString(abacabs));
     input = "abacababacab";
    pattern = "abacab";

    kmpAlgorithm.multiplMatch( input, pattern );
  }

  private void match(String inputString, String patternString) {

    if(patternString.length()<inputString.length()){
      System.out.println("Input length should be greater than the pattern length");
    }

    int [] failureFunction = geenerateFailureFunction(patternString);
    System.out.println("Pattern="+patternString+".Failure function="+Arrays.toString(failureFunction));

    char [] input = inputString.toCharArray();
    char [] pattern = patternString.toCharArray();

    int i=0;
    int j=0;

    while(i<input.length){

      if(input[i] == pattern[j]){
        if(j==pattern.length-1){
          System.out.println("Matched at i="+i);
          return;
        }
        i++;
        j++;
      }else if(j>0){
        j = failureFunction[j-1];
      }else{//j==0
        i++;
      }
    }

    System.out.println("There is no match");

  }

  private void multiplMatch(String inputString, String patternString) {

    if(patternString.length()<inputString.length()){
      System.out.println("Input length should be greater than the pattern length");
    }

    int [] failureFunction = geenerateFailureFunction(patternString);
    System.out.println("Pattern="+patternString+".Failure function="+Arrays.toString(failureFunction));

    char [] input = inputString.toCharArray();
    char [] pattern = patternString.toCharArray();

    int i=0;
    int j=0;

    while(i<input.length){

      if(input[i] == pattern[j]){
        if(j==pattern.length-1){
          System.out.println("Matched at i="+i);
          i++;
          j=0;
        }else{
          i++;
          j++;
        }
      }else if(j>0){
        j = failureFunction[j-1];
      }else{//j==0
        i++;
      }
    }

    System.out.println("There is no match");

  }


  private int[] geenerateFailureFunction(String inputString) {

    int [] result = new int[inputString.length()];
    result[0]  =0;

    char[] input = Arrays.copyOfRange(inputString.toCharArray(),1, inputString.length());
    int temp[] = new int[input.length];
    char[] pattern =  inputString.toCharArray();

    int i=0;
    int j=0;
    while(i<input.length){
      if(input[i] == pattern[j]){
        temp[i]=j+1;
        i++;
        j++;
      }else if(j>0){
          j=j-1;
      }else{//j==0
        temp[i]=0;
        i++;
      }

    }

    System.arraycopy(temp,0,result,1,temp.length);

    return result;

  }


}
