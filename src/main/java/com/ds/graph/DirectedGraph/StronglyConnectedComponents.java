package com.ds.graph.DirectedGraph;

//https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
//There are two algorithms to do this. 1.Tarjan Algorithm 2. Kosaraju Algorithm
/*

Strongly connected component: A component in which for any given vertex A to any vertex B, there
will be paths in both the directions. It means there is a cycle among all the vertices of the
component.

This algorithm outputs strongly connected components in such a way that a vertex is part of only
one component.


This algorithm doesn't output all strongly connected components. For example below graph

3
4
1 2
2 1
2 3
3 1

The output of above will be [1, 2, 3], but in fact you can have [1,2] and [1,2,3], i.e, two
strongly connected components.



Another example for testing this algorithm.
6
9
2 1
1 6
5 6
5 2
6 2
4 5
5 4
4 3
3 2

 */
import com.ds.graph.utils.DirectedGraph;
import com.ds.graph.utils.DirectedGraphUtils;

import java.util.*;

import static com.ds.graph.utils.DirectedGraph.*;

public class StronglyConnectedComponents {

    public static void main(String[] args) {
        DirectedGraph graph = DirectedGraphUtils.readDirectedGraph("/tmp/DirectedGraph3");
        Hashtable<Vertex,List<Vertex>> vertexListHashtable = tarjanAlgorithm(graph);
       // ArrayList<Map.Entry<Vertex,List<Vertex>>> entries = topologocalSort(graph);
        System.out.println(vertexListHashtable.entrySet());
    }

    public static Hashtable<Vertex,List<Vertex>>  tarjanAlgorithm(DirectedGraph graph){
        int clock =0;
        Hashtable<Vertex,List<Vertex>> sccs = new Hashtable<>();
        for(Vertex vertex: graph.getVertices()){
            if(!Vertex.isVisited(vertex)){
                clock = findSCCs(vertex,clock, new Stack<>(),sccs);
            }
        }
        return sccs;
    }

    public static ArrayList<Map.Entry<Vertex,List<Vertex>>> topologocalSort(DirectedGraph graph){
        Hashtable<Vertex,List<Vertex>> listHashtable = tarjanAlgorithm(graph);
        Set<Map.Entry<Vertex,List<Vertex>>> entrySet = listHashtable.entrySet();
        ArrayList<Map.Entry<Vertex,List<Vertex>>> tArrayList = new ArrayList<>(entrySet);
        Collections.sort(tArrayList,new Comparator<Map.Entry<Vertex,List<Vertex>>>() {
            @Override
            public int compare(Map.Entry<Vertex,List<Vertex>> o1,Map.Entry<Vertex,List<Vertex>> o2) {
                int endTime1= Vertex.getEndTime(o1.getKey());
                int endTime2 = Vertex.getEndTime(o2.getKey());
                if(endTime1<endTime2){
                    return  1;
                }else {
                    return -1;
                }
            }
        });
        return tArrayList;
    }

    private static int findSCCs(Vertex vertex,int clock,Stack<Vertex> stack,Hashtable<Vertex,List<Vertex>> sccs) {
        int discoveryTime = clock;
        int lowTime = clock;
        Vertex.setVisited(vertex,true);
        Vertex.setDiscoveryTime(vertex,discoveryTime);
        Vertex.setOnStackFlag(vertex,true);
        stack.push(vertex);
        clock++;
        for(DirectedEdge edge: vertex.getOutEdges()){
            Vertex otherEnd = edge.getOtherEnd(vertex);
            if(!Vertex.isVisited(otherEnd)){
                clock = findSCCs(otherEnd,clock,stack,sccs);
                int otherEndLowTime = Vertex.getLowTime(otherEnd);
                lowTime = Math.min(lowTime,otherEndLowTime);
            }else if(Vertex.getOnStackFlag(otherEnd)){
                int otherEndDiscoveryTime = Vertex.getDiscoveryTime(otherEnd);
                lowTime = Math.min(otherEndDiscoveryTime,lowTime);
            }else{
                //this is a cross-edge ignore(it is already visited).(as well as Vertex is not on
                // stack)
            }
        }
        Vertex.setLowTime(vertex,lowTime);
        Vertex.setEndTime(vertex,clock);
        clock++;
        if(lowTime == discoveryTime){
            List<Vertex> connectedComponent = new ArrayList<>();
            Vertex popped;
            do {
                popped = stack.pop();
                connectedComponent.add(popped);
                Vertex.setOnStackFlag(popped,false);
            }while(!popped.equals(vertex));
            sccs.put(vertex,connectedComponent);
        }
        return clock;
    }


}
