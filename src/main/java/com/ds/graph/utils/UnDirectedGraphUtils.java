package com.ds.graph.utils;

public class UnDirectedGraphUtils {
    public enum EDGE_TYPE{BACK_EDGE,TREE_EDGE};
    public static UnDirectedGraph readGraph(String fileName){
        return UnDirectedGraph.readGraph(fileName);
    }
}
