
package com.graph.DirectedGraph;

import static com.graph.utils.CommonGraphUtils.TOPO_VISIT_STATUS;

import com.graph.utils.CommonGraphUtils.TOPOLOGICAL_ORDER_VISIT_STATUS;
import com.graph.utils.DirectedGraph;
import com.graph.utils.DirectedGraph.DirectedEdge;
import com.graph.utils.DirectedGraph.Vertex;
import com.graph.utils.DirectedGraphUtils;
import java.util.ArrayList;

/*
https://en.wikipedia.org/wiki/Topological_sorting

  Algorithm defined in Algorithm Design(Goodrich)
  and DFS based in Wikipedia

  Depth-first search[edit]
An alternative algorithm for topological sorting is based on depth-first search. The algorithm loops through each node of the graph, in an arbitrary order,
initiating a depth-first search that terminates when it hits any node that has already been visited since the beginning of the topological
sort or the node has no outgoing edges (i.e. a leaf node):

L ← Empty list that will contain the sorted nodes
while exists nodes without a permanent mark do
    select an unmarked node n
    visit(n)

function visit(node n)
    if n has a permanent mark then return
    if n has a temporary mark then stop   (not a DAG)
    mark n with a temporary mark
    for each node m with an edge from n to m do
        visit(m)
    remove temporary mark from n
    mark n with a permanent mark
    add n to head of L
Each node n gets prepended to the output list L only after considering all other nodes which depend on n (all descendants of n in the graph).
Specifically, when the algorithm adds node n, we are guaranteed that all nodes which depend on n are already in the output list L: they were
 added to L either by the recursive call to visit()
which ended before the call to visit n, or by a call to visit() which started even before the call to visit n.
Since each edge and node is visited once, the algorithm runs in linear time. This depth-first-search-based algorithm is
the one described by Cormen et al. (2001); it seems to have been first described in print by Tarjan (1976).
 */
public class TopologicalSort_Wikipedia {

  public static void main(String[] args) {
    DirectedGraph directedGraph = DirectedGraphUtils.readDirectedGraph("/tmp/dirGraph1");

    ArrayList<Vertex> topoOrder = new ArrayList<>();

    for (Vertex vertex : directedGraph.getVertices()) {

      if (vertex.isPropertyDefined(TOPO_VISIT_STATUS) && vertex.getProperty(TOPO_VISIT_STATUS) !=
          TOPOLOGICAL_ORDER_VISIT_STATUS.PERMANENT) {
        throw new RuntimeException("The given digraph has cycle in it");

      }

      if (vertex.isPropertyDefined(TOPO_VISIT_STATUS) && vertex.getProperty(TOPO_VISIT_STATUS) ==
          TOPOLOGICAL_ORDER_VISIT_STATUS.PERMANENT) {
        continue;
      }

     doDFsAndToTopoList(vertex,topoOrder);

    }

    topoOrder.stream().forEach(vertex -> System.out.println(vertex.getNumber()));


  }

  static void doDFsAndToTopoList(Vertex vertex, ArrayList<Vertex> topoOrder) {
        if(vertex.isPropertyDefined(TOPO_VISIT_STATUS) && vertex.getProperty(TOPO_VISIT_STATUS) ==
            TOPOLOGICAL_ORDER_VISIT_STATUS.PERMANENT){
          return;
        }

     if (vertex.isPropertyDefined(TOPO_VISIT_STATUS) && vertex.getProperty(TOPO_VISIT_STATUS) !=
        TOPOLOGICAL_ORDER_VISIT_STATUS.PERMANENT) {
      throw new RuntimeException("The given digraph has cycle in it");

    }
    vertex.updateProperty(TOPO_VISIT_STATUS,TOPOLOGICAL_ORDER_VISIT_STATUS.TEMPORARY);
     for(DirectedEdge directedEdge: vertex.getOutEdges()){
       Vertex destination = directedEdge.getEnd();
       doDFsAndToTopoList(destination, topoOrder);
     }
     vertex.updateProperty(TOPO_VISIT_STATUS,TOPOLOGICAL_ORDER_VISIT_STATUS.PERMANENT);
     topoOrder.add(0,vertex);

  }

}
