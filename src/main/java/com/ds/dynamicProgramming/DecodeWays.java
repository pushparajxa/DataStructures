
package com.ds.dynamicProgramming;

import java.util.ArrayList;

public class DecodeWays {

  public static void main(String[] args) {

    int result = numDecodings("12120");
    System.out.println(result);

  }

  public static  int numDecodings(String s) {
    if(s == null || s.isEmpty()) {
      return 0;
    }

    int[] dp = new int[s.length() + 1];
    dp[0] = 1;

    for(int i = 1; i <= s.length(); i++) {
      char curr = s.charAt(i-1);
      int sum = 0;
      // If current character is valid
      // then add previous dp value
      if(curr - '0' > 0 && curr - '0' <= 9) {
        sum += dp[i-1];
      }

      if(i > 1) {
        // if last two characters are greater than 9
        // and less than 26, then add dp[i-2] value
        int val = Integer.parseInt(s.substring(i-2, i));
        if(val > 9 && val <=26) {
          sum += dp[i-2];
        }
      }
      dp[i] = sum;
    }
    return dp[s.length()];
  }

  public static int numDecodings3(String s) {
    if(s.charAt(0)=='0'){
      return 0;
    }
    char [] cr = s.toCharArray();
    int [] ir = new int[cr.length];
    for(int i=0;i<cr.length;i++){
      ir[i] = (int)cr[i]-48;
    }

    if(s.length()==1){
      return 1;
    }

    int sum = 1;

    if(ir[0] == 1){
      if(ir[1]==0){
        sum =1;
      }else{
        sum =2;
      }

    }else if(ir[0]==2){
      if(ir[1]==0 || ir[1]>6){
        sum =1;
      }else{
        sum=2;
      }
    }else{
      if(ir[1]==0){
        return 0;
      }else{
        sum=1;
      }
    }

    for(int i=2;i<ir.length;i++){
      int c = ir[i];
      if(ir[i-1]==1){
        if(ir[i-2]==0){
          if(c==0){
            sum = sum ;
          }else {
            sum = sum +sum;
          }
        }else if(ir[i-2]!=1 && ir[i-2]!=2){
          if(c==0){
            sum = sum;
          }else {
            sum = sum +sum;
          }
        }else{
          if(c==0){
            sum = sum-sum/2;
          }else {
            sum = sum +sum -1;
          }
        }
      }else if(ir[i-1]==0){
        if(c==0){
          return 0;
        }else {
          sum = sum;
        }
      } else if(ir[i-1]!=1 && ir[i-1]!=2){
        if(c==0){
          return 0;
        }else {
          sum = sum;
        }
      }else if(ir[i-1]==2){
        if(ir[i-2]==0){
          if(c==0){
            sum = sum;
          }else if(c>6){
            sum = sum ;
          }else {
            sum = sum +sum ;
          }
        }else if(ir[i-2]!=1 && ir[i-2]!=2){
          if(c==0){
            sum = sum;
          }else if(c>6){
            sum = sum ;
          }else {
            sum = sum +sum ;
          }
        }else{
          if(c==0){
            sum = sum-sum/2;
          }else if(c>6){
            sum = sum ;
          }else {
            sum = sum +sum -1;
          }
        }
      }


    }

    return sum;
  }

  public static int numDecodings2(String s) {
    if(s.charAt(0)=='0'){
      return 0;
    }
    char [] cr = s.toCharArray();
    int [] ir = new int[cr.length];
    for(int i=0;i<cr.length;i++){
      ir[i] = (int)cr[i]-48;
    }

    ArrayList<ArrayList<Integer>>  lofl = new ArrayList<>();
    ArrayList<Integer> tempList = new ArrayList<>();
    tempList.add(ir[0]);
    lofl.add(tempList);

    for(int i=1;i<ir.length;i++){
      ArrayList<ArrayList<Integer>> toAdd = new ArrayList<>();
      for(ArrayList<Integer> list : lofl){
        int last = list.get(list.size()-1);
        int parsed = Integer.parseInt(last+""+ir[i]);
        if(parsed <=26){
          ArrayList<Integer> temp2 = new ArrayList();
          temp2.addAll(list);
          temp2.remove(temp2.size()-1);
          temp2.add(parsed);
          toAdd.add(temp2);
        }
        if(ir[i]!=0){
          list.add(ir[i]);
          toAdd.add(list);
        }
      }
      lofl = toAdd;
    }

    return lofl.size();
  }

}
