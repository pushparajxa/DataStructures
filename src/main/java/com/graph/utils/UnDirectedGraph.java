package com.graph.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnDirectedGraph {
    private Hashtable<Integer,Vertex> vertexContainer = new Hashtable<>();
    private List<Edge> edgeContainer;
    private int num_vertices;
    private int num_edges;

    public static UnDirectedGraph readGraph(String fileName) {
        UnDirectedGraph graph = new UnDirectedGraph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Paths.get(fileName).toFile()));
            graph.num_vertices = Integer.parseInt(reader.readLine());
            graph.num_edges = Integer.parseInt(reader.readLine());
            int count = 0;
            Pattern pattern = Pattern.compile("\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)");
            Matcher matcher = pattern.matcher("");
            while(count<graph.num_edges) {
                matcher.reset(reader.readLine());
                if(matcher.find()) {
                    int vertex1 = Integer.parseInt(matcher.group(1));
                    int vertex2 = Integer.parseInt(matcher.group(2));
                    int value = Integer.parseInt(matcher.group(3));
                    Vertex v1 = null;
                    Vertex v2 = null;

                    if(graph.containsVertex(vertex1)) {
                        v1 = graph.getVertex(vertex1);

                    }else {
                        v1 = new Vertex(vertex1);
                    }

                    if(graph.containsVertex(vertex2)) {
                        v2 = graph.getVertex(vertex2);

                    }else {
                        v2 = new Vertex(vertex2);
                    }

                    Edge edge = new Edge(v1,v2,value);
                    v1.addEdge(edge);
                    v2.addEdge(edge);
                    graph.addEdge(edge);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public Vertex getVertex(int vertex1) {
        return vertexContainer.get(vertex1);
    }

    public void addEdge(Edge edge) {
        edgeContainer.add(edge);
    }

    private boolean containsVertex(int vertex1) {
        return vertexContainer.containsKey(vertex1);
    }

    static class Vertex extends Decorator {
        private int number;
        private List<Edge> edges = new LinkedList<>();

        public Vertex(int vertex1) {
            this.number = vertex1;
        }

        @Override
        public boolean equals(Object other) {
            if(other instanceof Vertex) {
                return number==((Vertex)other).number;
            }else {
                return false;
            }
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }

    static class Edge extends Decorator {
        private int value;
        private Vertex end1, end2;

        Edge(Vertex end1,Vertex end2,int value) {
            this.value = value;
            this.end1 = end1;
            this.end2 = end2;
        }

        Vertex getOtherEnd(Vertex end1) {
            if(end1.equals(end1)) {
                return end2;
            }else {
                return end2;
            }
        }
    }
}
