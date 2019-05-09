package com.graph.DirectedGraph;

//https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
//There are two algorithms to do this. 1.Tarjan Algorithm 2. Kosaraju Algorithm

import com.graph.utils.DirectedGraph;
import com.graph.utils.DirectedGraphUtils;

import java.util.*;

import static com.graph.utils.DirectedGraph.*;

public class StronglyConnectedComponents {

    public static void main(String[] args) {
        DirectedGraph graph = DirectedGraphUtils.readDirectedGraph("/tmp/DirectedGraph");
        Hashtable<Vertex,List<Vertex>> vertexListHashtable = tarjanAlgorithm(graph);
        ArrayList<Map.Entry<Vertex,List<Vertex>>> entries = topologocalSort(graph);
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
                clock = findSCCs(otherEnd,clock,new Stack<>(),sccs);
                int otherEndLowTime = Vertex.getLowTime(otherEnd);
                lowTime = Math.min(lowTime,otherEndLowTime);
            }else if(Vertex.getOnStackFlag(vertex)){
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
