package com.ds.graph.DirectedGraph;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
https://www.geeksforgeeks.org/transitive-closure-of-a-graph/
Floyd-Warshall Algorithm
 */
public class Transitive_Closure {

    boolean adjMatrix[][];
    int num_vertices;

    public static void main(String[] args) {
        Transitive_Closure closure = new Transitive_Closure();
        closure.readGraph();
        closure.transitive_closure();
    }

    static void print2DGraph(boolean[][] arr) {
        for(boolean[] ar : arr) {
            System.out.println(Arrays.toString(ar));
        }

    }

    void readGraph() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("/tmp/graph1")));
            String line;
            Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)");
            try {
                line = reader.readLine();
                num_vertices = Integer.parseInt(line);
                adjMatrix = new boolean[num_vertices][num_vertices];
                line = reader.readLine();
                int num_edges = Integer.parseInt(line);
                int i = 0;
                while(i<num_vertices) {
                    adjMatrix[i][i] = true;
                    i++;
                }
                i = 0;
                while(i<num_edges) {
                    line = reader.readLine();
                    Matcher matcher = pattern.matcher(line);

                    if(matcher.matches()) {
                        int vertex_num1 = Integer.parseInt(matcher.group(1));
                        int vertex_num2 = Integer.parseInt(matcher.group(2));

                        adjMatrix[vertex_num1][vertex_num2] = true;
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

    void transitive_closure() {
        System.out.println("Original Graph");
        print2DGraph(adjMatrix);

        for(int i = 0;i<num_vertices;i++) {
            for(int j = 0;j<num_vertices;j++) {
                if(j!=i) {
                    for(int k = 0;k<num_vertices;k++) {
                        if(k!=j && k!=i) {
                            if(adjMatrix[j][i] && adjMatrix[i][k]) {
                                adjMatrix[j][k] = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\n Transitive Closure is "+"\n");
        print2DGraph(adjMatrix);
    }


}
