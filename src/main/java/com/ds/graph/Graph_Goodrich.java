package com.ds.graph;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Un-directed Graph with no loops.
//http://www.cs.yale.edu/homes/aspnes/pinewiki/DepthFirstSearch.html
public class Graph_Goodrich {

    HashMap<Integer,Vertex> vertexContainer = new HashMap<Integer, Vertex>();
    List<Edge> edgeContainer = new LinkedList<Edge>();

    public static void main(String[] args) {
        Graph_Goodrich graph = new Graph_Goodrich();
        graph.readGraph();
    }

    void readGraph() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("/tmp/graph1")));
            String line;
            Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)");
            try {
                line = reader.readLine();
                int num_vertices = Integer.parseInt(line);
                line = reader.readLine();
                int num_edges = Integer.parseInt(line);
                int i = 0;
                while(i<num_edges) {
                    line = reader.readLine();
                    Matcher matcher = pattern.matcher(line);

                    if(matcher.matches()) {
                        int vertex_num1 = Integer.parseInt(matcher.group(1));
                        int vertex_num2 = Integer.parseInt(matcher.group(2));

                        Vertex vtx1 ;
                        Vertex vtx2 ;

                        if(vertexContainer.containsKey(vertex_num1)){
                            vtx1 = vertexContainer.get(vertex_num1);
                        }else{
                            vtx1 = new Vertex(vertex_num1);
                            vertexContainer.put(vertex_num1,vtx1);
                        }
                        if(vertexContainer.containsKey(vertex_num2)){
                            vtx2 = vertexContainer.get(vertex_num2);
                        }else{
                            vtx2 = new Vertex(vertex_num2);
                            vertexContainer.put(vertex_num2,vtx2);
                        }

                        Edge edge = new Edge(vtx1,vtx2);
                        vtx1.adjacencyList.add(edge);
                        vtx2.adjacencyList.add(edge);
                        edgeContainer.add(edge);
                    }

                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Depth first search
    /*
       In case of digraph or directed graph we can label the non-tree edges as forward edges, back-edges, cross-edges
     */
    private void dfs(int vertex,Set<Integer> visited, List<Edge> nonTreeEdges, List<Edge>  discoveryEdges/*Tree Edges */, Set<Edge> explored) {
        visited.add(vertex);
        Iterator<Edge> iterator = vertexContainer.get(vertex).adjacencyList.iterator();
        while(iterator.hasNext()) {
            Edge edge = iterator.next();
            if(!explored.contains(edge)){ // We should check whether edge is explored or not, otherwise we will mark tree edge as non-tree edge
                //A->B->C->A after going from A,B,C,A then we may mark A->B as non-tree edge overriding its status as tree edge.
                explored.add(edge);
                Vertex otherEnd = edge.getOtherEnd(vertexContainer.get(vertex));
                if(visited.contains(otherEnd)) {
                    nonTreeEdges.add(edge);
                }
                else{
                    discoveryEdges.add(edge);
                    dfs(otherEnd.number,visited,nonTreeEdges,discoveryEdges,explored); //TODO : Is it possible to write non-recursively?
                }
            }
        }
    }

    private void bfs(Stack<Vertex> stack,Set<Vertex> visited,List<Edge> backEdges,List<Edge> discoveryEdges,Set<Edge> explored){
        Stack<Vertex> nStack = new Stack<Vertex>();
        for(Vertex v : stack) {
            Iterator<Edge> iterator = vertexContainer.get(v.number).adjacencyList.iterator();
            while(iterator.hasNext()){
                Edge edge = iterator.next();
                if(!explored.contains(edge)){
                    explored.add(edge);
                    Vertex otherEnd = edge.getOtherEnd(v);
                    if(visited.contains(otherEnd)){
                        backEdges.add(edge);
                    }else{
                        discoveryEdges.add(edge);
                        visited.add(otherEnd);
                    }
                    nStack.add(otherEnd);
                }
            }
        }
        bfs(nStack,visited,backEdges,discoveryEdges,explored);
    }

    static class Edge {
        Vertex vertex1, vertex2;
        int data;
        /*Decorator pattern .. page no. 329 Goodrich 6.5.1 Decorator Pattern*/
        Hashtable<String,Object> attributes =  new Hashtable<>();

        Edge(Vertex vertex1,Vertex vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        Vertex getOtherEnd(Vertex vertex){
            if(vertex1.equals(vertex))
                return vertex2;
            else if(vertex2.equals(vertex))
                return vertex1;
            else
                throw new RuntimeException("Given vertex doesn't belong to this edge");
        }

        boolean isExplored(){
            if(attributes.containsKey("Explored")){
                return (Boolean)attributes.get("Explored");
            }else
                 return  false;
        }

    }

    static class Vertex {
        int number;
        PriorityQueue<Edge> adjacencyList = new PriorityQueue<>();

        Vertex(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object obj) {
            if(! (obj instanceof Vertex)) {
                return false;
            }else{
                Vertex vertex = (Vertex)obj;
                return vertex.number == number;
            }
        }
    }
    //Breadth First Search
}

