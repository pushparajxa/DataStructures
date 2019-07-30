
package com.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MinimumNumberOfMachinesRequired {

  public static void main(String[] args) {
    ArrayList<Schedule> schedules = new ArrayList<>();
    schedules.add(new Schedule(1,3));
    schedules.add(new Schedule(1,4));
    schedules.add(new Schedule(2,5));
    schedules.add(new Schedule(3,7));
    schedules.add(new Schedule(4,7));
    schedules.add(new Schedule(6,9));
    schedules.add(new Schedule(7,8));

    ArrayList<Schedule> schedules2 = new ArrayList<>(schedules);

   int machinesRequired = processSchedules(schedules);
    System.out.println(machinesRequired);

    machinesRequired = processSchedules2(schedules2);
    System.out.println(machinesRequired);

  }

  public static  int processSchedules(List<Schedule> schedules){
    if(schedules.isEmpty()){
      return 0;
    }
    Collections.sort(schedules, (schedule1, schedule2) -> {
      if(schedule1.startTime==schedule2.startTime){
        return 0;
      }else if(schedule1.startTime>schedule2.startTime){
        return 1;
      }else{
        return -1;
      }
    });

    PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        int int1=o1, int2=o2;
        if(int1 == int2){
          return 0;
        }else if(int1>int2){
          return 1;
        }else{
          return -1;
        }

      }
    });
    int  startTime, endTime, min;
    heap.add(schedules.get(0).endTime);
    int result=1;
    for(int i=1;i<schedules.size();i++){
      startTime = schedules.get(i).startTime;
      endTime = schedules.get(i).endTime;
      min = heap.peek();
      if(startTime>=min){
        heap.poll();
      }else{
        result++;
      }
      heap.add(endTime);

    }
    return  result;
  }


  //Approach 2
  public static int processSchedules2(List<Schedule> schedules) {


    List<Timing> timingStream = schedules.stream()
        .flatMap(schedule -> Arrays.asList(new Timing(schedule.startTime, true),
            new Timing(schedule.endTime, false)).stream()).collect(Collectors.toList());

   Collections.sort(timingStream, (timing1, timing2) -> {
     if(timing1.time==timing2.time){
       return 0;
     }else if(timing1.time > timing2.time){
       return 1;
     }else{
       return -1;
     }
   });

   int machinesNeeded=0;
   int max=0;

   for(Timing timing: timingStream){

     if(timing.isArrival){
       machinesNeeded++;
       if(machinesNeeded>max){
         max = machinesNeeded;
       }
     }else{
       machinesNeeded--;
     }

   }

    return max;


  }

  private static class Schedule {
    public Schedule(int startTime, int endTime){
      this.startTime = startTime;
      this.endTime = endTime;
    }
    private int startTime,endTime;

  }

  private static class Timing{
    public Timing(int time, boolean isArrival){
      this.time = time;
      this.isArrival = isArrival;
    }
    private int time;
    boolean isArrival;
  }



}
