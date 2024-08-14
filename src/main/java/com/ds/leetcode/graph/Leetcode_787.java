
package com.ds.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Leetcode_787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        buildGraph(flights);
        return getCheapPrice(src, dst, k);
    }
    
    private static class Vertex {
        public  int id;
        public List<Edge> outEgdes = new ArrayList<>();
        public PriorityQueue<Edge> heap = new PriorityQueue(new Comparator<Edge>(){
            
            @Override
            public int compare(Edge i1, Edge i2) {
                if(i1.getWgt() >= i2.getWgt()) {
                    return 1;}
                else {
                    return -1;
                }
            }
        }
        );
        int attached;
        boolean isVisited = false;
        
        public  Vertex(int id){
            this.id = id;
        }
        
        void addOutEdge(Edge e){
            outEgdes.add(e);
            heap.add(e);
        }
        
        
        int getId(){
            return id;
        }
        
        @Override
        public String toString(){
            return ""+id;
        }
    }
    
    private static class Edge{
        int src, dest, wgt;
        
        public Edge(int src, int dest, int weight)
        {
            this.src = src;
            this.dest = dest;
            this.wgt = weight;
        }
        
        public int getSource(){
            return src;
        }
        
        public int getDest(){
            return dest;
        }
        
        public int getWgt(){
            return wgt;
        }
        
    }
    
    private HashMap<Integer, Vertex> vertexContainer = new HashMap<>();
    
    private List<Edge> edgeContainer = new ArrayList<>();
    
    private void buildGraph(int [][]edges) {
        for(int i=0; i<edges.length; i++){
            int []edge = edges[i];
            int srcV = edge[0];
            int destV = edge[1];
            Edge e = new Edge(edge[0], edge[1],edge[2]);
            
            Vertex src, dest;
            
            if(!vertexContainer.containsKey(srcV)) {
                src = new Vertex(srcV);
                vertexContainer.put(srcV,src);
            }
            else {
                src = vertexContainer.get(srcV);
            }
            
            if(!vertexContainer.containsKey(destV)) {
                dest = new Vertex(destV);
                vertexContainer.put(destV,dest);
            }
            else {
                dest = vertexContainer.get(destV);
            }
            
            src.addOutEdge(e);
            
            edgeContainer.add(e);
        }
    }
    
    private int getCheapPrice(int start, int end, int k){
        
        Stack<Vertex> stack = new Stack();
    
        Vertex src = vertexContainer.get(start);
        src.isVisited = true;
        stack.push(src);
        
        int sum = 0;
        int hops = 0;
        int ret = Integer.MAX_VALUE;
        
        while(!stack.isEmpty()){
            Vertex v1 = stack.peek();
            Edge edge = v1.heap.poll();
            //System.out.println("Vertex=" + v1.id +". Edge Weight=" + edge.getWgt());
            if(edge == null) {
                hops--;
                sum = sum - v1.attached;
                stack.pop();
                v1.heap.addAll(v1.outEgdes);
                v1.isVisited = false;
            }
            else {
                int opp = edge.getDest();
                
                if(opp == end) {
                    ret = Math.min(ret, sum + edge.getWgt());
                    System.out.println(stack);
                    hops--;
                    sum = sum - v1.attached;
                    stack.pop();
                    v1.heap.addAll(v1.outEgdes);
                    v1.isVisited = false;
                    //return sum;
                }
                else {
                    if(hops>k) {
                        // do nothing.
                    }
                    else if(hops ==k){
                        if(opp == end) {
                            ret = Math.min(ret,sum + edge.getWgt());
                            //return sum;
                            System.out.println(stack);
                            hops--;
                            sum = sum - v1.attached;
                            stack.pop();
                            v1.heap.addAll(v1.outEgdes);
                            v1.isVisited = false;
                        }
                        else {
                            //do nothing.
                        }
                    }
                    else {
                        Vertex v2 = vertexContainer.get(opp);
                        if(!v2.isVisited) {
                            stack.push(v2);
                            hops++;
                            sum = sum + edge.getWgt();
                            v2.attached = edge.getWgt();
                            v2.isVisited = true;
                        }
                    }
                }
            }
        }
        
        
        
        if(ret == Integer.MAX_VALUE)
            return -1;
        else
            return ret;
    }
    
    public static void main(String[] args) {
        Leetcode_787 leetcode_787 = new Leetcode_787();
        int val = leetcode_787.findCheapestPrice(10, new int[][]{
            {0,4,2},
            {1,5,4}, {1,0,4},{1,7,6},{1,2,6},
            {2,5,6},
            {3,4,4},
            {4,7,10},{4,1,5},{4,0,9},
            {5,9,1},{5,2,4},
            {6,2,10},{6,8,6},{6,5,8},
            {8,7,3},
            {7,4,4},{7,9,4}, {7,0,5},{7,8,10},{7,2,8},
            {9,7,3},{9,6,5},{1,9,1}
        }, 6, 0, 7);
    
        System.out.println(val);
        
    }
}
