
package com.lang;


import java.util.List;

public class TowerResearch2 {

  public static void main(String[] args) {

  }

  public static int countMatches(List<String> grid1, List<String> grid2) {
    // Write your code here

    boolean visited1[][] = new boolean[grid1.size()][grid1.get(0).length()];
    int region1[][] = new int[grid1.size()][grid1.get(0).length()];
    char values1[][] = new char[grid1.size()][grid1.get(0).length()];

    for(int i=0;i<grid1.size();i++) {
      values1[i]= grid1.get(0).toCharArray();
    }

    for(int i=0;i<grid1.size();i++){
      for(int j=0;j<grid1.get(0).length();j++){
        visited1[i][j] = false;
      }
    }

    int count= 1;


    for(int i=0;i<grid1.size();i++){
      for(int j=0;j<grid1.get(0).length();j++){

        if(!visited1[i][j]){
           doDfs(visited1,region1,values1,count++,i,j);
        }

      }
    }


    boolean visited2[][] = new boolean[grid2.size()][grid2.get(0).length()];
    int region2[][] = new int[grid2.size()][grid2.get(0).length()];
    char values2[][] = new char[grid2.size()][grid2.get(0).length()];

    for(int i=0;i<grid2.size();i++) {
      values2[i]= grid2.get(0).toCharArray();
    }

    for(int i=0;i<grid2.size();i++){
      for(int j=0;j<grid2.get(0).length();j++){
        visited2[i][j] = false;
      }
    }

    int count2= 1;


    for(int i=0;i<grid2.size();i++){
      for(int j=0;j<grid2.get(0).length();j++){

        if(!visited2[i][j]){
          doDfs(visited2,region2,values2,count2++,i,j);
        }

      }
    }


    //check whether grids overlap or not.

 /*   for(int i=0;i<region1.length;i++){
      for(int j=0;j<region1[0].length;j++){
        if(region1[i][j]>0){
          if(region2[i][j] ){

          }
        }
      }
    }
*/

 return 0;

  }

  private static void doDfs(boolean[][] visited1, int[][] region1, char[][] values1, int count,
      int i, int j) {
    int rows = visited1.length;
    int cols = visited1[0].length;

    visited1[i][j]=true;
    if(values1[i][j]=='0'){
      return;
    }

    if(values1[i][j]=='1'){
      region1[i][j] = count;
    }

    if (i - 1 >= 0) {
      doDfs(visited1,region1,values1,count,i-1,j);
    }else if(i+1<rows){
      doDfs(visited1,region1,values1,count,i+1,j);
    }else if(j-1>=0){
      doDfs(visited1,region1,values1,count,i,j-1);
    }else if(j+1<cols){
      doDfs(visited1,region1,values1,count,i,j+1);
    }
  }


}
