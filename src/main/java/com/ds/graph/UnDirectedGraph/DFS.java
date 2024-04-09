package com.ds.graph.UnDirectedGraph;

import static com.ds.graph.utils.UnDirectedGraph.*;
import static com.ds.graph.utils.UnDirectedGraphUtils.EDGE_TYPE.BACK_EDGE;
import static com.ds.graph.utils.UnDirectedGraphUtils.EDGE_TYPE.TREE_EDGE;

import com.ds.graph.utils.UnDirectedGraph;
import com.ds.graph.utils.UnDirectedGraphUtils;

import java.util.Collection;

public class DFS {
    public static void main(String[] args) {
        UnDirectedGraph graph = UnDirectedGraphUtils.readGraph("/tmp/undirectedGraph");
        dfs(graph);
    }

    public static void dfs(UnDirectedGraph graph) {
        Collection<Vertex> vertices = graph.getVertices();
        for(Vertex vertex: vertices){
            if(!Vertex.isVisited(vertex)){
                doDFS(vertex);
            }
        }

    }

    // When we do dfs/bfs on an undirected graph we get connected components if the graph is a forest.
    //If the graph is connected we get only one single connected component.
    public static void connectedComponents(UnDirectedGraph graph){
        dfs(graph);
    }

    private static void doDFS(Vertex vertex) {
        Vertex.setVisited(vertex,true);
        for(Edge edge : vertex.getEdges()){
            if(!Edge.isVisited(edge)){
                /* We check if the edge is visited to ensure that we do not label a tree edge as a back edge.
                   For example consider  A-----B and start DFS from A. Initially you will lable
                   AB as tree edge, when at B if you do not check that BA has already been
                   visited you will re-label it as back edge.(But in fact it is a tree edge).
                 */
                Vertex otherEnd = edge.getOtherEnd(vertex);
                if(Vertex.isVisited(otherEnd)){
                    Edge.setMark(edge,BACK_EDGE);
                }else{
                    Edge.setVisited(edge,true);
                    Edge.setMark(edge,TREE_EDGE);
                    doDFS(otherEnd);
                }
            }
        }
    }
}
