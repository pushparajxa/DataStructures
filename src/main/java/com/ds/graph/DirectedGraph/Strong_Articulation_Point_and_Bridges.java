package com.ds.graph.DirectedGraph;

import static com.ds.graph.DirectedGraph.StronglyConnectedComponents.tarjanAlgorithm;

import com.ds.graph.utils.DirectedGraph;
import com.ds.graph.utils.DirectedGraph.DirectedEdge;
import com.ds.graph.utils.DirectedGraphUtils;
import java.util.ArrayList;
import java.util.List;

// Strong articulation points are calculated for Dircted Graphs. For  un-directed graphs they are called just articulation points.
// Strong bridges are defined for Directed Graphs. For un-directed graphs they are called just bridges.
//http://www.sofsem.cz/sofsem12/files/presentations/Thursday/GiuseppeItaliano.pdf
public class Strong_Articulation_Point_and_Bridges {
    
    // We remove each edge and see if it increases the number of connected components. This takes
    // O(m*(n+m)) where n, m are number of vertices and edges respectively.
    public static void calculateStrongArticulationPoints(DirectedGraph graph){
    
    }
    
    public static void main(String[] args) {
        DirectedGraph graph = DirectedGraphUtils.readDirectedGraph("/tmp/DirectedGraph");
    
        List<DirectedEdge> originalEdges = List.copyOf(graph.getEdges());
        
        List<DirectedEdge> strongBridges = new ArrayList<>();
        
        for (int i=0; i<originalEdges.size(); i++){
            DirectedEdge edge = originalEdges.get(i);
            
            graph.removeEdge(edge);
            if(tarjanAlgorithm(graph).size()>0) {
                strongBridges.add(edge);
            }
            originalEdges.add(edge);
            
        }
        
        if (strongBridges.size() ==0 ){
            System.out.println("There are no strong bridges in the graph.");
        }
        else {
            System.out.println("There are " + strongBridges.size() +" strongb bridges in the "
                + "graph.");
        }
      
       
    }
    
}
