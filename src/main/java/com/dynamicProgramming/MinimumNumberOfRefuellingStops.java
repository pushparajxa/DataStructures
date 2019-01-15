
package com.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/minimum-number-of-refueling-stops/
 */
public class MinimumNumberOfRefuellingStops {

  public static void main(String[] args) {
    MinimumNumberOfRefuellingStops minimumNumberOfRefuellingStops =
        new MinimumNumberOfRefuellingStops();
    int minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(1, 1, new int[][]{});
/*
    //target = 100, startFuel = 1, stations = [[10,100]]
    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(100, 1, new int[][]{{10,100}});
    //target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]

    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(100, 10,
        new int[][]{
        {10,60},
        {20,30},
        {30,30},
        {60,40}
    });
    //999,1000,[[5,100],[997,100],[998,100]]

    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(999, 1000,
        new int[][]{
            {10,60},
            {20,30},
            {30,30},
            {60,40}
        });

    //100,50,[[50,50]]

    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(100, 50,
        new int[][]{
            {50,50}
        });



    //100 ,50 ,[[25,50],[50,25]]

    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(100, 50,
        new int[][]{
            {25,50},
            {50,25}
        });

    //100 ,50 ,[[25,30]]

    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(100, 50,
        new int[][]{
            {25,30},
        });

    */

    //1000, 299 ,[[13,21],[26,115],[100,47],[225,99],[299,141],[444,198],[608,190],[636,157],[647,255],[841,123]]

    minRefuelStops = minimumNumberOfRefuellingStops.minRefuelStops(1000, 299,
        new int[][]{
            {13,21},
            {26,115},
            {100,47},
            {225,99},
            {299,141},
            {444,198},
            {608,190},
            {636,157},
            {647,255},
            {841,123}
        });
    //1008 -> 255 + 753 -> 255 + 299+ 115 + 141 + 198
    // 26, 115

    System.out.println(minRefuelStops);
  }

  public int minRefuelStops(int target, int startFuel, int[][] stations) {

    if (startFuel >= target) {
      return 0;
    }else if(stations.length==0){
      return -1;
    }

    if (startFuel < stations[0][0]) {
      return -1;
    }

    HashMap<Integer, List<Integer>> stops = new HashMap<>();

    HashMap<Integer, Integer> maxs = new HashMap<>();

    maxs.put(0, startFuel);
    stops.put(0, new ArrayList<>());

    for (int i = 1; i <= stations.length; i++) {
      if (i == stations.length) {

        List<Integer> result = getStops(stops.get(stations.length-1), stations, stations.length,
            maxs.get(stations.length - 1), target);
        stops.put(i, result);
        int totalSum = getTotalFuelFromStops(result, startFuel, stations);
        maxs.put(i, totalSum);

      } else {
        if (maxs.get(i - 1) >= stations[i][0]) {
          maxs.put(i, maxs.get(i - 1));
          stops.put(i, stops.get(i - 1));
        } else {
          List<Integer> result = getStops(stops.get(i - 1), stations, i, maxs.get(i - 1),
              stations[i][0]);
          stops.put(i, result);
          int totalSum = getTotalFuelFromStops(result, startFuel, stations);
          maxs.put(i, totalSum);
        }
      }

    }
    return stops.get(stations.length).size();

  }


  private List<Integer> getStops(List<Integer> stops, int[][] stations, int i,
      Integer max,int goal2) {

    List<Integer> result = new ArrayList<>(stops);
    int goal = goal2;
    int soFar = max;

   while(soFar<goal){
      int maxIndex=i-1;
      int maxValue=0;
      for (int j = 0; j <= i - 1; j++) {
        if (!result.contains(j)) {
          if (stations[j][1] > maxValue) {
            maxIndex = j;
            maxValue = stations[j][1];
          }
        }
      }
      soFar = soFar+maxValue;
      result.add(maxIndex);
    }

    return result;
  }

  private int getTotalFuelFromStops(List<Integer> stops, int startFuel, int[][] stations){
    int sum =startFuel;
    for(int i=0;i<stops.size();i++){
      sum= sum + stations[stops.get(i)][1];
    }
    return sum;
  }

}
