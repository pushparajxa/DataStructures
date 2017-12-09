
package com.graph;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Un-directed Graph with no loops. Vericies with zero degree can exist
public class Graph {

    PriorityQueue<Integer>[] adjecencyList;

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
                adjecencyList = new PriorityQueue[num_vertices];
                for(int i = 0;i<num_vertices;i++) {
                    adjecencyList[i] = new PriorityQueue<Integer>();
                }
                int i = 0;
                while(i<num_edges) {
                    line = reader.readLine();
                    Matcher matcher = pattern.matcher(line);

                    if(matcher.matches()){
                        int vertex1 = Integer.parseInt(matcher.group(1));
                        int vertex2 = Integer.parseInt(matcher.group(2));
                        adjecencyList[vertex1-1].add(vertex2-1);
                        adjecencyList[vertex2-1].add(vertex1-1);
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

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.readGraph();
    }

    //Depth first search
    private void dfs(int vertex){
        Set<Integer> visited = new HashSet<>();
        List<Edge> edges = new LinkedList<>();
        visited.add(vertex);
        Iterator<Integer> iterator = adjecencyList[vertex-1].iterator();
        while(iterator.hasNext()){
            int next = iterator.next()-1;
            if(!visited.contains(next)){
                dfs(next);
            }
        }
    }

    static class Edge{
        int vertex1,vertex2;
        Edge(int vertex1, int vertex2){
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }


    //Breadth First Search
}
