package com.graph.DirectedGraph;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
https://www.geeksforgeeks.org/transitive-closure-of-a-graph-using-dfs/
 */
public class Transitive_Closure_DFS {
    Hashtable<Integer,Vertex> vertexContainer = new Hashtable<>();
    List<Edge> edgeContainer = new ArrayList<>();
    int num_vertices;

    public static void main(String[] args) {
        Transitive_Closure_DFS transitive_closure_dfs = new Transitive_Closure_DFS();
        transitive_closure_dfs.readDirectedGraph();
        boolean[][] transitiveClosure = transitive_closure_dfs.getTransitiveClosure();
        Transitive_Closure.print2DGraph(transitiveClosure);
    }

    void readDirectedGraph() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/tmp/graph1")));
            String line = bufferedReader.readLine();
            num_vertices = Integer.parseInt(line);
            line = bufferedReader.readLine();
            int num_edges = Integer.parseInt(line);
            int count = 0;
            while(count<num_edges) {
                String edge = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)");
                Matcher matcher = pattern.matcher(edge);
                if(matcher.find()) {
                    int vertex1 = Integer.parseInt(matcher.group(1));
                    int vertex2 = Integer.parseInt(matcher.group(2));
                    Vertex vtx1;
                    Vertex vtx2;

                    if(vertexContainer.containsKey(vertex1)) {
                        vtx1 = vertexContainer.get(vertex1);
                    }else {
                        vtx1 = new Vertex(vertex1);
                        vertexContainer.put(vertex1,vtx1);
                    }
                    if(vertexContainer.containsKey(vertex2)) {
                        vtx2 = vertexContainer.get(vertex2);
                    }else {
                        vtx2 = new Vertex(vertex2);
                        vertexContainer.put(vertex2,vtx2);
                    }

                    Edge ege = new Edge(vtx1,vtx2);
                    vtx1.outEdges.add(ege);
                    vtx2.inEdges.add(ege);
                    edgeContainer.add(ege);
                }
                count++;
            }
            System.out.println("Finished reading the graph\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    boolean[][] getTransitiveClosure() {
        boolean adjMatrix[][] = new boolean[num_vertices][num_vertices];
        for(int i = 0;i<num_vertices;i++) {
            adjMatrix[i][i] = true;
        }
        Transitive_Closure.print2DGraph(adjMatrix);
        System.out.println();
        for(int i = 0;i<num_vertices;i++) {
            doDfs(i,i,adjMatrix);
        }
        return adjMatrix;
    }


    private void doDfs(int vertex,int start,boolean[][] adjMatrix) {
        Iterator<Edge> iterator = vertexContainer.get(vertex).outEdges.iterator();
        while(iterator.hasNext()) {
            Edge next = iterator.next();
            int otherEnd = next.getOtherEnd(vertex);
            if(!adjMatrix[start][otherEnd]) {
                adjMatrix[start][otherEnd] = true;
                doDfs(otherEnd,start,adjMatrix);
            }
        }
    }

    private static class Vertex {
        int number;
        List<Edge> inEdges = new ArrayList<>();
        List<Edge> outEdges = new ArrayList<>();

        public Vertex(int vertex1) {
            number = vertex1;
        }
    }

    private static class Edge {
        Hashtable<String,Object> attributes = new Hashtable<>();
        int weight;
        Vertex end1, end2;

        public Edge(Vertex vertex1,Vertex vertex2) {
            end1 = vertex1;
            end2 = vertex2;
        }

        boolean isExplored() {
            if(attributes.containsKey("Exlpored")) {
                return (Boolean)attributes.get("Explored");
            }else {
                return false;
            }
        }

        public int getOtherEnd(int vertex) {

            if(end1.number==vertex)
                return end2.number;
            else
                return end1.number;
        }

        public void clearExploredStatus() {
            attributes.remove("Explored");
        }
    }

}
