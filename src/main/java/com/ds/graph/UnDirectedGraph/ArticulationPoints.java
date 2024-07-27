
package com.ds.graph.UnDirectedGraph;

import com.ds.graph.utils.UnDirectedGraph;
import com.ds.graph.utils.UnDirectedGraphUtils;

import static com.ds.graph.utils.UnDirectedGraph.*;
/*
https://www.hackerearth.com/practice/algorithms/graphs/articulation-points-and-bridges/tutorial/
http://pisces.ck.tp.edu.tw/~peng/index.php?action=showfile&file=fb1f19a9be617037cb419c5d454b184bead47e243
https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
http://web.iitd.ac.in/~bspanda/biconnectedMTL776.pdf   The pdf file is saved in DataStructures/Graphs/UndirectedGraphs_Reference.pdf
 */


/*
3
2
0 1 0
1 2 0
Ans :: 1

7
8
0 1 0
2 1 0
2 0 0
1 3 0
3 5 0
5 4 0
4 1 0
1 6 0
Ans :: 1

8
11
0 1 0
0 2 0
1 2 0
1 3 0
1 4 0
3 4 0
3 5 0
4 5 0
6 5 0
7 5 0
6 7 0
Ans :: 1,5
// This graph is from articulation points section in the slides at https://web.iitd.ac.in/~bspanda/biconnectedMTL776.pdf
 */
//Running time : O(V+E)
public class ArticulationPoints {
    public static void main(String[] args) {
            UnDirectedGraph graph = UnDirectedGraphUtils.readGraph("/tmp/unDirectedGraph1");
            findArticulationPOints(graph);
    }

    public static void findArticulationPOints(UnDirectedGraph graph){
        int clock =0;
        for(Vertex vertex: graph.getVertices()){
            if(!Vertex.isVisited(vertex)){
                clock = doADFS(vertex,null,clock);
            }
        }

        for(Vertex vertex:graph.getVertices()){
            if(Vertex.getArticularionPointFlag(vertex)){
                System.out.println("Vertex :: "+vertex.getNumber());
            }
        }
    }

    private static int doADFS(Vertex vertex,Vertex parent, int clock) {
        Vertex.setVisited(vertex,true);
        int lowTime = clock;
        int discoveryTime = clock;
        Vertex.setDiscoveryTime(vertex,clock);
        clock++;
        int childrenCount=0;
        for(Edge edge: vertex.getEdges()){
                Vertex otherEnd = edge.getOtherEnd(vertex);
                if(Vertex.isVisited(otherEnd)){
                    if (!otherEnd.equals(parent)) {
                        int discTime = Vertex.getDiscoveryTime(otherEnd);
                        if (discTime < discoveryTime) {
                            lowTime = discTime;
                        }
                    }
                }else{
                    childrenCount++;
                    clock = doADFS(otherEnd,vertex,clock);
                    if(parent==null){
                        if(childrenCount>=2){
                            Vertex.setArticularionPointFlag(vertex,true);
                        }
                    }else{
                        int otherEndLowTime = Vertex.getLowTime(otherEnd);
                        if(otherEndLowTime>=discoveryTime){
                            Vertex.setArticularionPointFlag(vertex,true);
                        }
                        lowTime = Math.min(lowTime,otherEndLowTime);

                    }
                }
            }
        Vertex.setLowTime(vertex,lowTime);
        return clock; // do we need to do clock++? Check this.
    }
}
