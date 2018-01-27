package com.graph.DirectedGraph;

import static com.graph.utils.CommonGraphUtils.EDGE_TYPE;
import static com.graph.utils.DirectedGraph.*;
import static com.graph.utils.DirectedGraph.DirectedEdge.*;
import static com.graph.utils.DirectedGraph.DirectedEdge.EDGE_MARK.*;


import com.graph.utils.DirectedGraph;
import com.graph.utils.DirectedGraphUtils;
import com.graph.utils.UnDirectedGraphUtils;
import graph.Edge;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/*
8
8
0 1 0
2 1 0
0 2 0
0 3 0
3 4 0
5 6 0
6 7 0
7 5 0
 */

//Implemented as per here http://www.cs.yale.edu/homes/aspnes/pinewiki/DepthFirstSearch.html
//DirectedGraph also called as digraph. Topological sort, Strongly Connected Components are defined only for directed graphs
public class DirectedGraphProblems {

    public static void main(String[] args) {
        DirectedGraph directedGraph = DirectedGraphUtils.readDirectedGraph("/tmp/dirGraph1");
        markEdges(directedGraph);
       // LinkedList<Vertex> vertices = topologicalSort(directedGraph);
       // System.out.println(vertices.toString());
        directedGraph.printGraph();
        System.out.println("Cycle Exist = "+ checkCycleExists(directedGraph));
    }

    private static boolean checkCycleExists(DirectedGraph directedGraph) {
        markEdges(directedGraph);
        List<DirectedEdge> edges = directedGraph.getEdges();
        for(DirectedEdge edge: edges){
            if(edge.getProperty(EDGE_TYPE) == BACK_EDGE){
                return true;
            }
        }
        return false;
    }

    public static void clearGraphProperties(DirectedGraph graph){
        for(Vertex vertex: graph.getVertices()){
            vertex.clearProperties();
        }

        for(DirectedEdge edge: graph.getEdges()){
            edge.clearProperties();
        }

    }

    public static LinkedList<Vertex> topologicalSort(DirectedGraph graph){
        dfs(graph,false,0);
        //sort the verrices in the descending order of endTimes
        LinkedList<Vertex> vertices = new LinkedList<>(graph.getVertices());
        Collections.sort(vertices,new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                if(Vertex.getEndTime(v1) < Vertex.getEndTime(v2)){
                    return 1;
                }else{
                    return -2;
                }
            }
        });
        return vertices;

    }

    //A graph is acyclic if it doesn't have any back-edges.

    //Instead of marking egdes .. we can just pass the visiting hierarchy and check the visited vertex is in that or not. If yes
    // then return that cycle exists[see GeeksForGeeks
    private static void dfs(DirectedGraph directedGraph,boolean isAForest,int startVertex) {
        int clock = 0;
        if(isAForest){
            for(DirectedGraph.Vertex vert : directedGraph.getVertices()) {
                if(!Vertex.isVisited(vert))
                    clock = doDfs(vert,null,clock);
            }
        }else{
            doDfs(directedGraph.getVertex(startVertex),null,clock);
        }
    }

    private static int doDfs(Vertex vertex,Vertex parent, int clock) {
        Vertex.setStartTime(vertex,clock++);
        Vertex.setVisited(vertex,true);
        Vertex.setParent(vertex,parent);
        for(DirectedEdge edge : vertex.getOutEdges()) {
            if(!Vertex.isVisited(edge.getOtherEnd(vertex))) {
                clock = doDfs(edge.getOtherEnd(vertex),vertex,clock);
            }
        }
        Vertex.setEndTime(vertex,clock++);
        return clock;
    }

    public static void markEdges(DirectedGraph directedGraph){
        dfs(directedGraph,true,0);
        //Tree edges will be marked in the step above
        //Now mark back-edges, Forward edges and Cross Edges
        //The algorithm is described here http://www.cs.yale.edu/homes/aspnes/pinewiki/DepthFirstSearch.html
        for(DirectedEdge edge: directedGraph.getEdges()){

            if(!edge.isPropertyDefined(EDGE_TYPE)){
                Vertex begin = edge.getBegin();
                Vertex end = edge.getEnd();

                int beginStartTime=Vertex.getStartTime(begin),beginEndTime=Vertex.getEndTime(begin);
                int endStartTime=Vertex.getStartTime(end),endEndTime =Vertex.getEndTime(end);

                if(beginStartTime>endStartTime && beginEndTime<endEndTime){
                    DirectedEdge.markEdge(edge,BACK_EDGE);
                }else if(beginStartTime<endStartTime && beginEndTime>endEndTime && !Vertex.isParent(edge.getBegin(),edge.getEnd())){
                    DirectedEdge.markEdge(edge,FORWARD_EDGE);
                }else if(beginStartTime>endStartTime && beginEndTime>endEndTime){
                    DirectedEdge.markEdge(edge,CROSS_EDGE);
                }else if(beginStartTime<endStartTime && beginEndTime>endEndTime && Vertex.isParent(edge.getBegin(),edge.getEnd())){
                    DirectedEdge.markEdge(edge,TREE_EDGE);
                }
            }

        }
    }

}