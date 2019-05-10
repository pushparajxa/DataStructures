/*
You are given N pairs of numbers. In every pair, the first number is always smaller than the second number. A pair (c, d) can follow another pair (a, b) if b < c. Chain of pairs can be formed in this fashion. Your task is to complete the function maxChainLen which returns an integer denoting the longest chain which can be formed from a given set of pairs.

Input:
The first line of input contains an integer T denoting the no of test cases then T test cases follow .Then T test cases follow . The first line of input contains an integer N denoting the no of pairs . In the next line are 2*N space separated values denoting N pairs.

Output:
For each test case output will be the length of the longest chain formed.

Constraints:
1<=T<=100
1<=N<=100

Example(To be used only for expected output):
Input
2
5
5  24 39 60 15 28 27 40 50 90
2
5 10 1 11

Output
3
1
​
https://practice.geeksforgeeks.org/problems/max-length-chain/1
 */

package com.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MaxLenthChain {
static  class Pair
{
  int x;
  int y;

  public Pair(int a, int b)
  {
    x = a;
    y = b;
  }
}

  int maxChainLength(Pair arr[], int n)
  {

    Comparator<Pair> pairComparator = new Comparator<Pair>() {
      @Override
      public int compare(Pair pair1, Pair pair2) {
        return pair1.y - pair2.y;
      }
    };

    Arrays.sort(arr,pairComparator);
    int max=0;

    for(int i=0;i<n;i++){
      int count =1;
      Pair curr = arr[i];
      for (int j = 0; j < n; j++) {
        if(arr[j].x>curr.y){
          count++;
          curr = arr[j];
        }
      }
      if(count>max){
        max=count;
      }
    }
return max;
  }
}
