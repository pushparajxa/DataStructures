
package com.ds.graph.DirectedGraph;

import com.ds.graph.utils.DirectedGraph;
import com.ds.graph.utils.DirectedGraph.DirectedEdge;
import com.ds.graph.utils.DirectedGraph.Vertex;
import com.ds.graph.utils.DirectedGraphUtils;

// For this algorithm graph should be a directed graph. Difference between this and Dijkstra's
// algorithm is that it will handle edges with negative weight
public class BellmanFordSingleSourceShortestPaths
{
    
    public static void main(String[] args) {
        DirectedGraph directedGraph = DirectedGraphUtils.readDirectedGraph("/tmp/dirGraph1");

        String distance = "DISTANCE";
        String previous = "PREVIOUS_VERTEX";
        
        // Why we do n -1 times loop over all edges?
        // Check the example run of algorithm in Algorithm design book
    
        for (Vertex vertex: directedGraph.getVertices()) {
            vertex.updateProperty(distance, Integer.MAX_VALUE);
        }
        
        for (int i = 1; i <= directedGraph.getNumberOfVertices()-1 ; i++) {
           for(DirectedEdge edge : directedGraph.getEdges()) {
               Vertex begin = edge.getBeginVertex();
               Vertex end   = edge.getEndVertex();
    
               int val = (int)begin.getProperty(distance) + edge.getValue();
               
                if (val < (int)end.getProperty(distance)) {
                   end.updateProperty(distance, val);
                   end.updateProperty(previous, begin);
               }
               
           }
        }
    
        // Here we check for negative weight cycle, i,e, cycle with sum of all weights of edges
        // in it is less than zero.
        for(DirectedEdge edge : directedGraph.getEdges()) {
            Vertex begin = edge.getBeginVertex();
            Vertex end   = edge.getEndVertex();
        
            int val = (int)begin.getProperty(distance) + edge.getValue();
        
            if (val < (int)end.getProperty(distance)) {
                System.out.println("Negative weight cycle detected");

            }
            
            return;
        }
        
        
    }
}
