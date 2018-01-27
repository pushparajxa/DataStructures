package com.graph.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.graph.utils.CommonGraphUtils.*;

public class UnDirectedGraph {
    private Hashtable<Integer,Vertex> vertexContainer = new Hashtable<>();
    private List<Edge> edgeContainer= new ArrayList<>();
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
                        graph.addVertex(v1);
                    }

                    if(graph.containsVertex(vertex2)) {
                        v2 = graph.getVertex(vertex2);

                    }else {
                        v2 = new Vertex(vertex2);
                        graph.addVertex(v2);
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

    private void addVertex(Vertex v2) {
        vertexContainer.put(v2.number,v2);
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

    public Collection<Vertex> getVertices() {
        return vertexContainer.values();
    }

    public static class Vertex extends Decorator {
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

        public static boolean isVisited(Vertex vertex) {
            if(vertex.isPropertyDefined(VISITED_FLAG))
                return (boolean)vertex.getProperty(VISITED_FLAG);
            else
                return false;
        }

        public static void setVisited(Vertex vertex,boolean b) {
            vertex.updateProperty(VISITED_FLAG,b);
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public static void setLowTime(Vertex vertex,int clock) {
            vertex.updateProperty( LOW_TIME,clock);
        }

        public static void setDiscoveryTime(Vertex vertex,int clock) {
            vertex.updateProperty(DISC_TIME,clock);
        }

        public static int getDiscoveryTime(Vertex otherEnd) {
           return (int) otherEnd.getProperty(DISC_TIME);
        }

        public static int getLowTime(Vertex otherEnd) {
            return (int) otherEnd.getProperty(LOW_TIME);
        }

        public static void setArticularionPointFlag(Vertex vertex,boolean b) {
            vertex.updateProperty(ARTICULATION_POINT_FLAG,b);
        }

        public static boolean getArticularionPointFlag(Vertex vertex) {
            if(vertex.isPropertyDefined(ARTICULATION_POINT_FLAG))
                 return  (boolean)vertex.getProperty(ARTICULATION_POINT_FLAG);
            else
                return false;
        }

        public int getNumber(){
            return number;
        }
    }

    public static class Edge extends Decorator {
        private int value;
        private Vertex end1, end2;

        public Edge(Vertex end1,Vertex end2,int value) {
            this.value = value;
            this.end1 = end1;
            this.end2 = end2;
        }

        public Vertex getOtherEnd(Vertex end1) {
            if(this.end1.equals(end1)) {
                return end2;
            }else {
                return this.end1;
            }
        }

        public static boolean isVisited(Edge edge) {
            if(edge.isPropertyDefined(VISITED_FLAG))
                return (boolean)edge.getProperty(VISITED_FLAG);
            else
                return false;
        }

        public static void setMark(Edge edge,UnDirectedGraphUtils.EDGE_TYPE edge_type) {
            edge.updateProperty(EDGE_TYPE,edge_type);

        }

        public static void setVisited(Edge edge,boolean b) {
            edge.updateProperty(VISITED_FLAG,true);
        }
    }
}
