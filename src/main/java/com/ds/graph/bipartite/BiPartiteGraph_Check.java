/*
 ** COPYRIGHT **
 */
package com.ds.graph.bipartite;

import java.util.HashMap;

public class BiPartiteGraph_Check {
    
    
    static public boolean isBipartite(int[][] graph) {
        
        HashMap<Integer, Boolean> map = new HashMap<>();
        int len = graph.length;
        
        HashMap<Integer, Boolean> visited = new HashMap<>();
        
        boolean val =  check(graph, 0, true, map, visited);
        
        if(val == false) {
            System.out.println("Vertex = 0. False");
            return false;
        }
        
        if(visited.size() != len){
            
            for (int i = 0; i < len; i++) {
                if(!visited.containsKey(i)){
                    boolean val2 =  check(graph, i, true, map, visited);
                    if(val2 == false) {
                        System.out.println("Vertex = "+ i+". False");
                        return false;
                    }
                }
            }
            
            return true;
        
        }
        else {
            return val;
        }
        
        
    }
    
    static boolean check(int[][] graph, int vertex, boolean partition,
        HashMap<Integer, Boolean> partitionMap,
        HashMap<Integer, Boolean> visited){
        
        visited.put(vertex, true);
        partitionMap.put(vertex, partition);
        
        
        for (int i = 0; i < graph[vertex].length ; i++) {
            
            if(partitionMap.get(graph[vertex][i]) == null || partitionMap.get(graph[vertex][i]) != partition ) {
                
                if(!visited.containsKey(graph[vertex][i])) {
                  
                    if(!check(graph, graph[vertex][i], !partition, partitionMap,visited))
                        return false;
                        
                }
            }
            else { // partitionMap.get(graph[vertex][i]) == partition
                System.out.println("Vertex = "+ graph[vertex][i]+". False");
                return false;
            }
            
        }
        
        return true;
        
        
    }
    
    public static void main(String[] args) {
        
        int [][] graph = new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}};
        
        boolean res;
        //res = isBipartite(graph);
        
      //  System.out.println(res);
        
        graph = new int[][]{
            {1,3},
            {0,2},
            {1,3},
            {0,2}
            
        };
        
       // res = isBipartite(graph);
        
      //  System.out.println(res);
        
        graph = new int[][]{
            {3,4,6},
        {3,6},
        {3,6},
        {0,1,2,5},
        {0,7,8},
        {3},
        {0,1,2,7},
        {4,6},
        {4},
        {}
            
        };
        
        res = isBipartite(graph);
        
        System.out.println(res);
    }
    
}
