package com.graph.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.graph.utils.DirectedGraphUtils.VISITED;

public  class DirectedGraph {
    private Hashtable<Integer,Vertex> vertexContainer = new Hashtable<>();
    private List<DirectedEdge> edgeContianer;
    private int num_vertices;
    private int num_edges;

    static DirectedGraph readGraph(String filePath) {
        //Line 1: Number of Vertices
        //Line 2: Number of Edges
        //Followed by each line containing <Vertex1> <Vertex2>
        DirectedGraph graph = new DirectedGraph();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(filePath).toFile()));
            graph.num_vertices = Integer.parseInt(bufferedReader.readLine());
            graph.num_edges = Integer.parseInt(bufferedReader.readLine());
            graph.edgeContianer = new ArrayList<>(graph.num_edges);
            int count = 0;
            while(count<graph.num_edges) {
                String line = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)");
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    int vertex1 = Integer.parseInt(matcher.group(1));
                    int vertex2 = Integer.parseInt(matcher.group(2));
                    int value = Integer.parseInt(matcher.group(3));
                    Vertex startVertex = new Vertex(vertex1);
                    Vertex endVertex = new Vertex(vertex2);
                    DirectedEdge edge = new DirectedEdge(startVertex,endVertex,value);
                    startVertex.outEdges.add(edge);
                    endVertex.inEdges.add(edge);
                    graph.addVertex(startVertex);
                    graph.addVertex(endVertex);
                    graph.addEdge(edge);
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    private void addEdge(DirectedEdge edge) {
        edgeContianer.add(edge);
    }

    private void addVertex(Vertex v1) {
        vertexContainer.put(v1.number,v1);
    }

    public Collection<Vertex> getVertices() {
       return  vertexContainer.values();
    }

    public int getNumberOfVertices() {
        return num_vertices;
    }

    public List<DirectedEdge> getEdges() {
        return edgeContianer;
    }


    public static class Vertex extends Decorator {
        private int number;
        private List<DirectedEdge> inEdges = new ArrayList<>();
        private List<DirectedEdge> outEdges = new ArrayList<>();
        private static String START_TIME="StartTime";
        private static String END_TIME ="EndTime";

        public static boolean isVisited(Vertex vertex) {
            if(vertex.isPropertyDefined(VISITED)){
                return (Boolean)vertex.getProperty(VISITED);
            }else{
                return false;
            }
        }


        public static void setVisited(Vertex vertex,boolean b) {
            vertex.updateProperty(VISITED,b);
        }

        public static void setStartTime(Vertex vertex,int startTime) {
            vertex.updateProperty(START_TIME,startTime);
        }

        public static void setEndTime(Vertex vertex,int endTime) {
            vertex.updateProperty(END_TIME,endTime);
        }


        Vertex(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object other) {
            if(other instanceof Vertex) {
                return ((Vertex)other).number==this.number;
            }else {
                return false;
            }
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Iterable<? extends DirectedEdge> getOutEdges() {
            return outEdges;
        }

        public static int getStartTime(Vertex vertex) {
           return  (int)vertex.getProperty(START_TIME);
        }

        public static int getEndTime(Vertex vertex) {
            return  (int)vertex.getProperty(END_TIME);
        }
    }

    public static class DirectedEdge extends Decorator {
        private Vertex begin, end;
        private int value;
        public static final String EDGE_MARK = "EdgeMark";

        public static void markEdge(DirectedEdge edge,EDGE_MARK treeEdge) {
            edge.updateProperty(EDGE_MARK,treeEdge);
        }

        public static enum EDGE_MARK {TREE_EDGE,BACK_EDGE,CROSS_EDGE,FORWARD_EDGE }

        public static boolean isVisited(DirectedEdge edge){
            if(edge.isPropertyDefined(VISITED)){
                return (Boolean)edge.getProperty(VISITED);
            }else{
                return false;
            }
        }



        DirectedEdge(Vertex begin,Vertex end) {
            this.begin = begin;
            this.end = end;
        }

        DirectedEdge(Vertex begin,Vertex end,int value) {
            this.begin = begin;
            this.end = end;
            this.value = value;
        }

        public Vertex getOtherEnd(Vertex vertex) {
            if(begin.equals(vertex))
                return this.end;
            else
                return begin;
        }

        public Vertex getBegin(){
            return begin;
        }

        public Vertex getEnd(){
            return end;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
