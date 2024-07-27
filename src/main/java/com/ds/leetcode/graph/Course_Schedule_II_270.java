package com.ds.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Course_Schedule_II_270 {
    
    static HashMap<Integer, Vertex> vertices = new HashMap();
    static HashMap<Integer,Edge> edges = new HashMap<>();
    public static  final String IN = "IN", OUT = "OUT";
    
  
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
    
        if(numCourses == 1) {
            return new int[]{0};
        }
    
        if (prerequisites.length==0){
            int [] result = new int [numCourses];
            for(int i=0; i<numCourses; i++){
                result[i] = i;
            }
            return result;
        }
       int inDegrees[] = new int[numCourses];
       int outDegress[] = new int[numCourses];
        
        
        for (int i = 0; i < prerequisites.length; i++) {
    
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            Edge edge = new Edge(i, src, dest);
            edges.put(i, edge);
            vertices.putIfAbsent(dest, new Vertex(dest));
            vertices.putIfAbsent(src, new Vertex(src));
            vertices.get(src).addEdge(OUT, edge);
            vertices.get(dest).addEdge(IN,edge);
            inDegrees[dest] = inDegrees[dest] + 1;
            outDegress[src] = outDegress[src] + 1;
            
        }
    
        Stack<Integer> stack = new Stack<>();
    
        for (int i = 0; i < numCourses; i++) {
            if(inDegrees[i] == 0) {
                stack.add(i);
            }
        }
        
        int [] result = new int[numCourses];
        int i =0;
        
        while(!stack.isEmpty()) {
            int verId = stack.pop();
            result[i] = verId;
            i++;
            Vertex vertex = vertices.get(verId);
           
            for(Iterator<Edge> iterator =  vertex.outEdges.iterator(); iterator.hasNext();) {
                Edge edge = iterator.next();
                inDegrees[edge.dest] = inDegrees[edge.dest] -1;
                if (inDegrees[edge.dest] ==0) {
                    stack.add(edge.dest);
                }
                
            }
        }
    
        
    
        System.out.println(Arrays.toString(result));
        return result;
        
    }

    public static void main(String[] args) {
    
        findOrder(2, new int[][]{{0,1}});
/*        findOrder(4, new int[][]{
            {1,0},
            {2,0},
            {3,1},
            {3,2}});*/
    }
    
     static  class Vertex extends Decoratored{
        

        private int id;
        private List<Edge> inEdges = new ArrayList<>();
        private  List<Edge> outEdges = new ArrayList<>();
        
        
        
        
        public Vertex(int id) {
            this.id = id;
        }
        
        public List<Edge> getInEdges(){
            return inEdges;
        }
    
        public List<Edge> getOutEdges(){
            return outEdges;
        }
        
        void addEdge(String edgeType, Edge edge){
    
            boolean res = edgeType.equals(IN) ? inEdges.add(edge) : outEdges.add(edge);
    
        }
    
        public int getId() {
            return id;
        }
        
    }
    
    private static class Edge extends Decoratored{
        int id;
        int source;
        int dest;
        
        public Edge(int id, int source, int dest){
            this.id = id;
            this.source = source;
            this.dest = dest;
        }
    }
    
    private static class Decoratored {
        
        private HashMap<Object, Object> properties = new HashMap<>();
        
         boolean isPropertyDefined(Object property) {
            return properties.containsKey(property);
        }
    
        Object getPropertyValue(Object property){
            return properties.get(property);
        }
    
        Set<Object> definedProperties(){
            return properties.keySet();
        }
        
        void setProperty(Object property, Object value){
            this.properties.computeIfPresent(property, (prop,val) -> val);
        }
        
        
    }
    
    
    
    
}
