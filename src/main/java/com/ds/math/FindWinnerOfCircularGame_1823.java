/*
 ** COPYRIGHT **
 */
package com.ds.math;

public class FindWinnerOfCircularGame_1823 {
    
    public int findTheWinner(int n, int k) {
        int count = n;
        boolean [] visited = new boolean [n];
        int i =-1,p;
        while(count != 1){
            p = k;
            while(p>0){
                i = (i+1)%n;
                if(visited[i]){
                    // do nothing.
                }
                else {
                    p = p-1;
                }
                
                
            }
            
            if(!visited[i]){
                visited[i] = true;
                count--;
            }
        }
        
        for(int j=0; j<n;j++){
            if(!visited[j])
                return j+1;
        }
        
        return -1;
        
    }
}