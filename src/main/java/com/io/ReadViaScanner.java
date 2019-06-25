
package com.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadViaScanner {

  public static void main(String[] args) {
    Scanner scanner  = new Scanner(System.in);
    int n= scanner.nextInt();
    int i=0;int size,j;
    while(i<n){
      size = scanner.nextInt();
      j=0;
      int arr[] = new int[size];
      while(j<size){
        arr[j]= scanner.nextInt();
        j++;
      }


      i++;
    }
  }

  private static void readIntoArray(int[] input, BufferedReader br, int size) throws IOException {
    String line =  br.readLine();

    String ints[] = line.split(" ");
    int i=0;
    while(i<size){
      input[i] = Integer.parseInt(ints[i]);
      i++;
    }

  }

 /* public static void main (String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n= Integer.parseInt(br.readLine());
    int i=0;int size,j;
    while(i<n){
      size = Integer.parseInt(br.readLine());
      int arr[] = new int[size];
      readIntoArray(arr,br,size);
      System.out.println(getResult(arr));
      i++;
    }
  }
  */
}
