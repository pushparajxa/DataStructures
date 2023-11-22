
package com.ds.dynamicProgramming;

public class DeleteColumnsToMakeSorted {

  public static void main(String[] args) {
   int res =  minDeletionSize(new String[]{
        "abbba"});
    System.out.println(res);
  }

  public static int minDeletionSize(String[] A) {
    int [] col_val = new int[A[0].length()];//number of columns
    col_val[0]=1;
    int cols = A[0].length();
    boolean status=false;
    int max=0;
    for(int i=1;i<cols;i++){
      max=0;
      for(int j=0;j<i;j++){
        status=true;
        for(int k=0;k<A.length;k++){
          if(A[k].charAt(j)>A[k].charAt(i)){
            status=false;
          }
        }
        if(status){
          max = col_val[j]>max?col_val[j]:max;
        }

      }
      col_val[i]=1+max;

    }

    int final_max=0;
    for (int i= 0; i < col_val.length; i++) {
      final_max = col_val[i]>final_max ? col_val[i] : final_max;
    }

    return A[0].length()-final_max;
  }
}
