
package com.ds.graph.DirectedGraph;

import static com.ds.graph.utils.CommonGraphUtils.IN_DEGREE_COUNT;
import static com.ds.graph.utils.CommonGraphUtils.TOPOLOGICAL_ORDER_COUNT;

import com.ds.graph.utils.DirectedGraph;
import com.ds.graph.utils.DirectedGraph.DirectedEdge;
import com.ds.graph.utils.DirectedGraph.Vertex;
import com.ds.graph.utils.DirectedGraphUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class TopologicalSortGoodRich {

  public static void main(String[] args) {
    DirectedGraph directedGraph = DirectedGraphUtils.readDirectedGraph("/tmp/dirGraph1");
    Stack<Vertex> verticesStack = new Stack<>();
    for(Vertex vertex : directedGraph.getVertices()){
      vertex.updateProperty(IN_DEGREE_COUNT,vertex.getInEdges().size());
      if(vertex.getInEdges().isEmpty()){
        verticesStack.push(vertex);
      }
    }

    if(verticesStack.isEmpty()){
      throw new RuntimeException("The digraph has a directed cycle");
    }

    int topoOrder=1;
    while(!verticesStack.isEmpty()){

      Vertex vertex = verticesStack.pop();
      vertex.updateProperty(TOPOLOGICAL_ORDER_COUNT,topoOrder++);
      for(DirectedEdge edge: vertex.getOutEdges()){
        Vertex destination = edge.getEndVertex();
        int inDegree = (Integer)destination.getProperty(IN_DEGREE_COUNT);
        destination.updateProperty(IN_DEGREE_COUNT,inDegree-1);
        if(inDegree-1==0){
          verticesStack.add(destination);
        }
      }

    }

    for(Vertex vertex : directedGraph.getVertices()) {
      if (!vertex.isPropertyDefined(TOPOLOGICAL_ORDER_COUNT)) {
        throw new RuntimeException("Given digraph has a cycle.");
      }
    }
      Comparator<Vertex> vertexComparator = (o1, o2) -> {
        int vertex1TopoOrder = (Integer)o1.getProperty(TOPOLOGICAL_ORDER_COUNT);
        int vertex2TopoOrder = (Integer)o2.getProperty(TOPOLOGICAL_ORDER_COUNT);
        if(vertex1TopoOrder>vertex1TopoOrder){
          return  1;
        }else if(vertex1TopoOrder < vertex2TopoOrder){
          return -1;
        }else{
          return 0;
        }


      };

    ArrayList<Vertex> vertices = new ArrayList<>(directedGraph.getVertices());
    Collections.sort(vertices,vertexComparator);
    vertices.stream().forEach(
        vertex -> System.out.println("Vertex number="+vertex.getNumber() +" -- "+vertex.getProperty(TOPOLOGICAL_ORDER_COUNT))
    );

  }

}
