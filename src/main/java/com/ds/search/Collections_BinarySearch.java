
package com.ds.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections_BinarySearch {
    
    

    static class Pair  implements Comparable<Pair>{
        int start, end;
        
        Pair(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Pair o) {
           if(this.start == o.start){
               return this.end> o.end? 1 : -1;
           }
           else if(this.start > o.start) {
               return 1;
           }
           else {
               return -1;
           }
        }
        
    }
    
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(List.of(1,2,3,5,9,13));
        
        int res = Collections.binarySearch(array, 3);
        System.out.println(res);
        
        res = Collections.binarySearch(array, 6);
        System.out.println(res);
        
        array.add(-res -1, 6 );
        System.out.println(array);
        
        array = new ArrayList<>();
        
        res = Collections.binarySearch(array, 0);
        System.out.println(res);
        
        
        List<Pair> pairs = new ArrayList<>(List.of(new Pair(1,2), new Pair(3,5)));
        
        res = Collections.binarySearch(pairs, new Pair(3,6));
        
        System.out.println(res);
        
    }
}
