package com.graph.utils;

public class DirectedGraphUtils {
    public static final String VISITED = "Visited";
    public static DirectedGraph readDirectedGraph(String fileName){
        return DirectedGraph.readGraph(fileName);
    }


}
