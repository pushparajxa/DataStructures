package com.dynamicProgramming;


import java.util.*;

import static com.general.ArrayUtils.*;

//Held-Karp Algorithm. Implemneted based on the original research paper.
public class TravellingSalesManProblem {

    //Select subsets of size "select" from array input
    static ArrayList<int[]> printCombinations(int select,int[] input) {
        if(select==0) {
            ArrayList<int[]> list = new ArrayList<>();
            list.add(new int[]{});
            return list;
        }

        ArrayList<int[]> result = new ArrayList<>();
        combinatiorics_helper(0,0,select,input,new int[select],result);
        return result;
    }

    static void combinatiorics_helper(int start,int rank,int rank_max,int[] input,int[] combination,ArrayList<int[]> result) {
        for(int i = start;i<input.length && elementsToTheLeft(i,input)>=elemtsNeeded(i,rank_max);i++) {
            combination[rank] = input[i];
            if(rank!=rank_max-1) {
                combinatiorics_helper(i+1,rank+1,rank_max,input,combination,result);
            }else {
                result.add(combination.clone());
            }
        }
    }

    //Elements to the left if the cursor is at i.
    static int elementsToTheLeft(int i,int[] array) {
        return array.length-1-i;
    }

    static int elemtsNeeded(int rank,int rank_maximum) {
        return rank_maximum-(rank+1);
    }


    static void getShortestTour(int[][] tourCost) {
        int numCities = tourCost.length;
        int[] cities = new int[tourCost.length-1];

        for(int i = 0;i<cities.length;i++) {
            cities[i] = i+1;
        }

        HashMap<Integer,HashMap<Set<Integer>,Pair>> preValues;
        //For a give Set store a Pair which contains cost to reach 0(using the Integer key) using this set and the path
        //The Size of this map is highest when you (n-1)/2 items from a list of n i..e 2^n.When we iterate each
        // of that selection though n cities we get storage as O(n*(2^n)
        HashMap<Integer,HashMap<Set<Integer>,Pair>> preValuesNew = new HashMap<>();

        for(int l = 0;l<cities.length;l++) {
            HashMap<Set<Integer>,Pair> setIntegerHashMap = new HashMap<>();
            setIntegerHashMap.put(toSet(new int[]{}),new Pair(tourCost[cities[l]][0],0));
            preValuesNew.put(cities[l],setIntegerHashMap);
        }

        for(int i = 1;i<=cities.length-1;i++) {
            ArrayList<int[]> combinations = printCombinations(i,cities);
            preValues = preValuesNew;
            preValuesNew = new HashMap<>();
            for(int j = 1;j<numCities;j++) {
                for(int[] comb : combinations) {
                    if(!contains(j,comb)) {
                        Pair pair = getMinCost(j,comb,preValues,tourCost);
                        insertIntoMap(j,comb,pair,preValuesNew);
                    }
                }
            }
        }

        Pair min = getMinCost(0,cities,preValuesNew,tourCost);

        System.out.println(min.cost);
        System.out.println("0->"+min.path);
    }

    private static void insertIntoMap(int key,int[] comb,Pair minCost,HashMap<Integer,HashMap<Set<Integer>,Pair>> preValuesNew) {
        if(preValuesNew.containsKey(key)) {
            preValuesNew.get(key).put(toSet(comb),minCost);
        }else {
            HashMap<Set<Integer>,Pair> setIntegerHashMap = new HashMap<>();
            setIntegerHashMap.put(toSet(comb),minCost);
            preValuesNew.put(key,setIntegerHashMap);
        }
    }

    private static Pair getMinCost(int city,int[] comb,HashMap<Integer,HashMap<Set<Integer>,Pair>> preValues,int[][] costMap) {
        int min = Integer.MAX_VALUE;
        StringBuffer path = null;
        int minVertex = Integer.MIN_VALUE;
        Set<Integer> set = toSet(comb);
        for(int i = 0;i<comb.length;i++) {
            set.remove(comb[i]);
            int value = costMap[city][comb[i]]+preValues.get(comb[i]).get(set).cost;
            if(min>value) {
                min = value;
                minVertex = comb[i];
                path = preValues.get(comb[i]).get(set).path;
            }
            set.add(comb[i]);
        }
        StringBuffer stringBuffer = new StringBuffer(Integer.toString(minVertex));
        return new Pair(min,stringBuffer.append("->").append(path));
    }

    private static void initHashMap(HashMap<Integer,HashMap<Set<Integer>,Integer>> costMap,int length) {
        for(int i = 1;i<=length;i++) {
            costMap.put(i,new HashMap<>());
        }
    }

    public static void main(String[] args) {
        int[][] cost = new int[][]{
                {0,1,15,6},
                {2,0,7,3},
                {9,6,0,12},
                {10,4,8,0}
        };
        getShortestTour(cost);

    }

    static class Pair {
        int cost;
        StringBuffer path;

        Pair(int cost,StringBuffer string) {
            this.cost = cost;
            this.path = string;
        }

        Pair(int cost,int vertex) {
            this.cost = cost;
            this.path = new StringBuffer(Integer.toString(vertex));
        }

        @Override
        public String toString() {
            return cost+","+path;
        }
    }

}
