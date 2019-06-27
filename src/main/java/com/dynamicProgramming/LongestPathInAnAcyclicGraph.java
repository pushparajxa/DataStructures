
package com.dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//https://www.geeksforgeeks.org/dynamic-programming-trees-set-2/
//Undirected graph
public class LongestPathInAnAcyclicGraph {

  public static void main(String[] args) {

    int no_vertices = Integer.parseInt(args[0]);
    int no_edges = Integer.parseInt(args[1]);
    int graph[][] = new int[no_vertices][no_vertices];
    boolean visited[][] = new boolean[no_vertices][no_vertices];
    HashMap<Integer, Integer> heights = new HashMap<>();

    int i = 0;
    while (i < no_edges) {
      String[] split = args[2 + i].split("-");
      int source = Integer.parseInt(split[0]);
      int dest = Integer.parseInt(split[1]);
      graph[source - 1][dest - 1] = 1;
      graph[dest - 1][source - 1] = 1;
      i++;
    }

    int root = 0;
    dfs(graph, visited, root, heights);

    visited = new boolean[no_vertices][no_vertices];
    HashMap<Integer, Integer> outHeights = new HashMap<>();

    dfs2(graph, visited, root, 0, heights, outHeights);

    System.out.println(heights);
    System.out.println(outHeights);

    int longestPath = 0;
    for (int j = 0; j < no_vertices; j++) {
      System.out
          .println("Maximum height considering " + j + " as root is=" + Math.max(heights.get(j),
              outHeights.get(j)));
      longestPath = Math.max(longestPath, +Math.max(heights.get(j),
          outHeights.get(j)));
    }

    System.out.println("\nThe longest path is " + longestPath);

  }

  private static int dfs(int[][] graph, boolean[][] visited, int vertex,
      HashMap<Integer, Integer> heights) {

    int max = 0;
    for (int i = 0; i < graph.length; i++) {
      if (graph[vertex][i] == 1 && visited[vertex][i] == false) {
        visited[vertex][i] = true;
        visited[i][vertex] = true;
        max = Math.max(max, 1 + dfs(graph, visited, i, heights));
      }
    }
    heights.put(vertex, max);
    return max;
  }

  private static void dfs2(int[][] graph, boolean[][] visited, int vertex, int inHeight,
      HashMap<Integer, Integer> heights, HashMap<Integer, Integer> outHeights) {

    outHeights.put(vertex, inHeight);

    ArrayList<Integer> cHeights = new ArrayList<>();//Children excluding parent
    for (int i = 0; i < graph.length; i++) {
      if (graph[vertex][i] == 1 && visited[vertex][i] == false) {
        cHeights.add(heights.get(i));
      }
    }

    Collections.sort(cHeights);//sorts in descending order
    Collections.reverse(cHeights); // Reverse to ascending order
    int next, maxSiblingHeight;

    for (int i = 0; i < graph.length; i++) {
      if (graph[vertex][i] == 1 && visited[vertex][i] == false) {
        visited[vertex][i] = true;
        visited[i][vertex] = true;
        //get max height of sibilings other than  this .
        if (cHeights.size() == 1) {
          //This is the only child of the vertex
          maxSiblingHeight = 0;
        } else if (cHeights.get(0) == heights.get(i)) {
          maxSiblingHeight = cHeights.get(1);

        } else {
          maxSiblingHeight = cHeights.get(0);
        }

        next = 1 + Math.max(inHeight, 1 + maxSiblingHeight);
        dfs2(graph, visited, i, next, heights, outHeights);
      }
    }

  }


}
