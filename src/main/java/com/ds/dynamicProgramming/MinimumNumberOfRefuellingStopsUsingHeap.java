
package com.ds.dynamicProgramming;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/minimum-number-of-refueling-stops/
Accepted with timing 20ms faster than 82.35% Java submissions
 */
public class MinimumNumberOfRefuellingStopsUsingHeap {

  public static void main(String[] args) {

  MinimumNumberOfRefuellingStopsUsingHeap minimumNumberOfRefuellingStops =
      new MinimumNumberOfRefuellingStopsUsingHeap();
  int minRefuelStops ;

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

    //Max_Heap
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer int1, Integer int2) {
        if(int1 == int2){
          return 0;
        }
        if(int1>int2){
          return -1;
        }
        else return 1;
      }
    });

    priorityQueue.add(stations[0][1]);

    int fuelAvailable = startFuel;
    int stops =0;

    for(int i=1;i<stations.length;i++){
      int goal = stations[i][0];

      if(goal<=fuelAvailable){

        priorityQueue.add(stations[i][1]);

      }else{

        while(fuelAvailable<goal){

          Integer poll = priorityQueue.poll();
          if(poll==null){
            return -1;
          }
          fuelAvailable = fuelAvailable+poll;
          stops=stops+1;
        }

        priorityQueue.add(stations[i][1]);

      }

    }

    while(fuelAvailable<target){
      Integer poll = priorityQueue.poll();
      if(poll==null){
        return -1;
      }
      fuelAvailable = fuelAvailable+poll;
      stops=stops+1;
    }

    return stops;
  }


}
