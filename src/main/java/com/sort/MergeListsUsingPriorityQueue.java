package com.sort;

import com.google.common.collect.Iterables;

import java.util.*;

public class MergeListsUsingPriorityQueue {

    public static void mergeLists(List<Integer> ...lists) {

        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1,List<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        int totalLength = 0;
        for(List<Integer> list : lists) {
            totalLength += lists.length;
            if(!list.isEmpty())
                priorityQueue.add(list);
        }

        List<Integer> mergedList = new ArrayList<>(totalLength);

        while(!priorityQueue.isEmpty()) {
            List<Integer> list = priorityQueue.remove();
            Integer remove = list.remove(0);
            mergedList.add(remove);
            if(!list.isEmpty())
                priorityQueue.add(list);
        }

        Iterables.toString(mergedList);
        System.out.println(mergedList.toString());
    }

    public static void main(String[] args) {
        mergeLists(
                new ArrayList<Integer>(Arrays.asList(1,2,3)),
                new ArrayList<Integer>(Arrays.asList(5,6,7)),
                new ArrayList<Integer>(Arrays.asList(1,2,3))
        );
    }
}
