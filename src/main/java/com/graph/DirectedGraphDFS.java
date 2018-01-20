package com.graph;

import static com.graph.utils.DirectedGraph.*;
import static com.graph.utils.DirectedGraph.DirectedEdge.*;
import static com.graph.utils.DirectedGraph.DirectedEdge.EDGE_MARK.*;


import com.graph.utils.DirectedGraph;
import com.graph.utils.DirectedGraphUtils;

public class DirectedGraphDFS {

    public static void main(String[] args) {
        DirectedGraph directedGraph = DirectedGraphUtils.readDirectedGraph("/tmp/dirGraph1");
        dfs(directedGraph);
    }

    private static void dfs(DirectedGraph directedGraph) {
        int clock = 0;
        for(DirectedGraph.Vertex vert : directedGraph.getVertices()) {
            clock = doDfs(vert,clock);
        }
        //Tree edges will be marked in the step above
        //Now mark back-edges, Forward edges and Cross Edges
        //The algorithm is described here http://www.cs.yale.edu/homes/aspnes/pinewiki/DepthFirstSearch.html
        for(DirectedEdge edge: directedGraph.getEdges()){

            if(!edge.isPropertyDefined(EDGE_MARK)){
                Vertex begin = edge.getBegin();
                Vertex end = edge.getEnd();

                int beginStartTime=Vertex.getStartTime(begin),beginEndTime=Vertex.getEndTime(begin);
                int endStartTime=Vertex.getStartTime(end),endEndTime =Vertex.getEndTime(end);

                if(beginStartTime>endStartTime && beginEndTime<endEndTime){
                    DirectedEdge.markEdge(edge,BACK_EDGE);
                }else if(beginStartTime<endStartTime && beginEndTime>endEndTime){
                    DirectedEdge.markEdge(edge,FORWARD_EDGE);
                }else if(beginStartTime>endStartTime && beginEndTime>endEndTime){
                    DirectedEdge.markEdge(edge,CROSS_EDGE);
                }
            }

        }
    }

    private static int doDfs(Vertex vertex,int clock) {
        Vertex.setStartTime(vertex,clock++);
        Vertex.setVisited(vertex,true);
        for(DirectedEdge edge : vertex.getOutEdges()) {
            if(!Vertex.isVisited(edge.getOtherEnd(vertex))) {
                DirectedEdge.markEdge(edge,TREE_EDGE);
                clock = doDfs(edge.getOtherEnd(vertex),clock);
            }
        }
        Vertex.setEndTime(vertex,clock++);
        return clock;
    }


}