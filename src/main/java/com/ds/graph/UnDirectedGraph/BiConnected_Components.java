package com.ds.graph.UnDirectedGraph;

import com.ds.graph.utils.UnDirectedGraph;
import com.ds.graph.utils.UnDirectedGraph.Edge;
import com.ds.graph.utils.UnDirectedGraph.Vertex;
import com.ds.graph.utils.UnDirectedGraphUtils;
import java.util.Stack;

/**
 * 8
 * 10
 * 1 2 0
 * 2 3 0
 * 3 1 0
 * 2 5 0
 * 5 6 0
 * 5 4 0
 * 4 6 0
 * 4 8 0
 * 4 7 0
 * 7 8 0
 *
 * Implemented based on this http://web.iitd.ac.in/~bspanda/biconnectedMTL776.pdf
 *  The pdf file is saved in DataStructures/Graphs/UndirectedGraphs_Reference.pdf
 */

public class BiConnected_Components {

  public static void main(String[] args) {
    UnDirectedGraph graph = UnDirectedGraphUtils.readGraph("/tmp/unDirectedGraph5");
    getBiConnectecComponents(graph);
  }

  private static void getBiConnectecComponents(UnDirectedGraph graph) {
    int clock = 1;
    Stack<Edge> stack = new Stack<>();
    for (Vertex vertex : graph.getVertices()) {
      if (!Vertex.isVisited(vertex)) {
        Vertex.setParent(vertex,null);
        clock = biDFS(vertex, clock,stack);
        clock++;
      }
    }
  }

  private static int biDFS(Vertex vertex, int clock, Stack<Edge> stack) {

    Vertex.setDiscoveryTime(vertex, clock);
    Vertex.setLowTime(vertex, clock);
    Vertex.setVisited(vertex, true);
    int discTime = clock;
    int lowTime = clock;


    for (Edge edge : vertex.getEdges()) {
      Vertex otherEnd = edge.getOtherEnd(vertex);
      int otherDiscTime;
      int otherLowTime ;

      if(Vertex.isVisited(otherEnd) && (Vertex.getParent(vertex)==null || !Vertex.getParent(vertex).equals(otherEnd))){
        otherDiscTime = Vertex.getDiscoveryTime(otherEnd);
         if(otherDiscTime<discTime){
           lowTime = Math.min(lowTime,otherDiscTime);
           stack.push(edge);
         }

      }else if(!Vertex.isVisited(otherEnd)){
         Vertex.setParent(otherEnd,vertex);
         stack.push(edge);
         clock = biDFS(otherEnd,++clock,stack);
         otherLowTime = Vertex.getLowTime(otherEnd);
         lowTime = Math.min(lowTime,otherLowTime);
         if(otherLowTime>=discTime){
           System.out.println("Start Connected Component ");
           Edge ege;
           do{
              ege = stack.pop();
             System.out.println(ege);
           }while(!(ege.getEnds().contains(vertex) && ege.getEnds().contains(otherEnd)));
           System.out.println("End Connected Component ");
         }
      }

    }
    Vertex.setLowTime(vertex,lowTime);
    return clock;
  }

}
