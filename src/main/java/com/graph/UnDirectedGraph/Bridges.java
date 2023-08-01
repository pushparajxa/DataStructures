package com.graph.UnDirectedGraph;

import com.graph.utils.UnDirectedGraph;
import com.graph.utils.UnDirectedGraphUtils;
import static com.graph.utils.UnDirectedGraph.*;
/*
6
7
5 0 0
0 1 0
1 2 0
2 4 0
4 3 0
3 1 0
3 2 0

Ans :: 0 - 5 & 0 - 1
*/

//https://www.hackerearth.com/practice/algorithms/graphs/articulation-points-and-bridges/tutorial/
//http://web.iitd.ac.in/~bspanda/biconnectedMTL776.pdf -- Understood from here on 11th July
public class Bridges {
    public static void main(String[] args) {
        UnDirectedGraph graph = UnDirectedGraphUtils.readGraph("/tmp/unDirectedGraph5");
        findBridges(graph);
        for(Edge edge: graph.getEdges()){
            if(Edge.getBridgeFlag(edge)){
                System.out.println("Edge :: "+edge.toString());
            }
        }
    }

    public static void findBridges(UnDirectedGraph graph){
        int clock = 0;
        for(UnDirectedGraph.Vertex vertex: graph.getVertices() ){
            if(!Vertex.isVisited(vertex))
                clock =  doBDFS(vertex,null,clock);
        }
    }

    private static int doBDFS(Vertex vertex,Vertex parent, int clock) {
        int lowTime = clock;
        int discTime = clock;
        Vertex.setDiscoveryTime(vertex,clock);
        Vertex.setVisited(vertex,true);
        clock++;
        for(Edge edge: vertex.getEdges()){
            Vertex otherEnd = edge.getOtherEnd(vertex);
            if(!Vertex.isVisited(otherEnd)){
                clock= doBDFS(otherEnd,vertex,clock);
                int otherEndlowTime = Vertex.getLowTime(otherEnd);
                if(otherEndlowTime>discTime){
                    Edge.setBridgeFlag(edge,true);
                }else{
                   // lowTime = otherEndlowTime;
                    lowTime = Math.min(lowTime,otherEndlowTime);
                    Edge.setBridgeFlag(edge,false);
                }
            }
            else if(Vertex.isVisited(otherEnd) && !otherEnd.equals(parent)){
                int discoveryTime = Vertex.getDiscoveryTime(otherEnd);
                if(lowTime>discoveryTime){
                    lowTime=discoveryTime;
                }
                Edge.setBridgeFlag(edge,false);
            }
        }
        Vertex.setLowTime(vertex,lowTime);
        return clock;
    }
}
