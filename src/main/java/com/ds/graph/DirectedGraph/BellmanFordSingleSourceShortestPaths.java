
package com.ds.graph.DirectedGraph;

import com.ds.graph.utils.DirectedGraph;
import com.ds.graph.utils.DirectedGraph.DirectedEdge;
import com.ds.graph.utils.DirectedGraphUtils;

public class BellmanFordSingleSourceShortestPaths
{
    
    public static void main(String[] args) {
        DirectedGraph directedGraph = DirectedGraphUtils.readDirectedGraph("/tmp/dirGraph1");
        
        int a;
    
        for (int i = 0; i < directedGraph.getNumberOfVertices()-1 ; i++) {
           for(DirectedEdge edge : directedGraph.getEdges()) {
           
           }
           
        }
    }
}
