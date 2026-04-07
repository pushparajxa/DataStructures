/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.Arrays;

public class MinJobDifficulty {
    
    int[] jobDifficulty;
    
    public int minDifficulty2(int[] jobDifficulty, int d) {
        
        this.jobDifficulty = jobDifficulty;
        
        int len = jobDifficulty.length;
        
        if(d > len)
            return -1;
        
        if(len ==d)
            return Arrays.stream(jobDifficulty).reduce(0, Integer::sum);
        
        /*
        
         -----------------------
        |  1  | 2   |  3  |len  |
         -----------------------
     1  |     |     |     |     |
         -----------------------
     2  |     |     |     |     |
         -----------------------
     d  |     |     |     |     |
         -----------------------
         
         */
        
        
        int [][] val = new int[d+1][len+1];
        System.out.println(Arrays.toString(jobDifficulty));
        
        val[1][1] = jobDifficulty(len);
        
        
        for (int i = 2; i <=len ; i++) {
            val[1][i] = Math.max(jobDifficulty(len-i+1), val[1][i-1]);
        }
        
        System.out.println(Arrays.toString(val[0]));
        
        for(int i=2; i<=d;i++) { // days from second day on wards.
            int start= Math.min(i, len -(d-i));
            int end = Math.max(i, len-(d-i));
            System.out.println("Start=" + start +". End=" + end);
            for (int j = start; j<= end ; j++) {
                //j tasks from last. For the ith days from last,
                // minimum you must give 1 task each day, making it to min = i tasks.
                // maximum will be total_tasks - ( 1 task for each of len -i days before)
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                
                for(int k =1; k<= j - (i-1) ; k++){
                    System.out.println("k="+ k+". latest="+ (len -j+k) + ". subcall=val["+ (i-1)+ "]["+ (j-k-1)+"]");
                    // max = Math.max(max, jobDifficulty[len -(i+1)-k+1]);
                    max = Math.max(max, jobDifficulty(len -j+k));
                    min = Math.min(min, max + val[i-1][j-k]);
                }
                
                
                val[i][j] = min;
                System.out.println("Day = " + i + " .No.of tasks="+ j +". Vals: " + Arrays.toString(val[i]));
            }
            
        }
        

        return val[d][len];
    }
    
    int jobDifficulty(int n){
        return this.jobDifficulty[n-1];
    }
    
    public int minDifficulty(int[] jobDifficulty, int d) {
        
        
        int len = jobDifficulty.length;
        
        if(d > len)
            return -1;
        
        if(len ==d)
            return Arrays.stream(jobDifficulty).reduce(0, Integer::sum);
        
        /*
        
         -----------------------
        |  1  | 2   |  3  |len  |
         -----------------------
     1  |     |     |     |     |
         -----------------------
     2  |     |     |     |     |
         -----------------------
     d  |     |     |     |     |
         -----------------------
         
         */
        
        
        int [][] val = new int[d][len];
        System.out.println(Arrays.toString(jobDifficulty));
        
        val[0][0] = jobDifficulty[len-1];
        
       // val[0][1] = Math.max(jobDifficulty[len-2], val[0][1-1]);
        
        //val[0][len-1] = Math.max(jobDifficulty[0], val[0][len-2]);
        
        for (int i = 1; i <=len-1 ; i++) {
            val[0][i] = Math.max(jobDifficulty[len-i-1], val[0][i-1]);
        }
        
        System.out.println(Arrays.toString(val[0]));
        
        for(int i=1; i<d;i++){ // days from second day on wards.
            int start= Math.min(i+1, len -(d-(i+1)));
            int end = Math.max(i+1, len-(d-(i+1)));
            System.out.println("Start=" + start +". End=" + end);
            for (int j = start; j<= end ; j++) {
                //j tasks from last. For the ith days from last,
                // minimum you must give 1 task each day, making it to min = i tasks.
                // maximum will be total_tasks - ( 1 task for each of len -i days before)
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                
                for(int k =1; k<= j - (i+1-1) ; k++){
                    System.out.println("k="+ k+". latest="+ (len -j+k-1) + ". subcall=val["+ (i-1)+ "]["+ (j-k-1)+"]");
                   // max = Math.max(max, jobDifficulty[len -(i+1)-k+1]);
                    max = Math.max(max, jobDifficulty[len -j+k-1]);
                    min = Math.min(min, max + val[i-1][j-k-1]);
                }
                
                
                val[i][j-1] = min;
                System.out.println("Day = " + i + " .No.of tasks="+ j +". Vals: " + Arrays.toString(val[i]));
            }
            
        }
        
        
        /*
        i days j tasks
           min = Integer.MAX_VALUE;
           for k = 1 , 2, j - i+1;
              min = Math.min(min, max(1..k) + val[i-1 days][j-k tasks]
        
        val[i days][j tasks] = min;
        
        */
        // return val[d-1][d-1];
        return val[d-1][len-1];
    }
    
    

    
    public static void main(String[] args) {
        MinJobDifficulty minJobDifficulty = new MinJobDifficulty();
      int res = minJobDifficulty.minDifficulty2(new int[]{6,5,4,3,2,1}, 2);
      // int  res = minJobDifficulty.minDifficulty2(new int[]{11,111,22,222,33,333,44,444}, 6);
        
        System.out.println(res);
        
        
    }
    
}























































