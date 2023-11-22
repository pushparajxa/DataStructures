
package com.ds.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

//https://www.baeldung.com/java-fork-join
public class InPlaceMergeSortUsingForkJoinPool {

  public static void main(String[] args) {

    int input[] = new int[]{7,8,8,1,11,1,24,5};
   // int input[] = new int[]{7,8,1,24,5};
    sort(input);
    System.out.println(Arrays.toString(input));
  }

  public static int [] sort(int [] input){

    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    //ForkJoinTask.invokeAll(Collections.singletonList((new MergeSubTask(input, 0,input.length - 1))));
    int[] invoke = forkJoinPool.invoke(new MergeSubTask(input, 0, input.length - 1));

   // return input;
    return invoke;
  }

  static class MergeSubTask extends RecursiveTask<int []>{

    private int start, end;
    private int[] input;

    public MergeSubTask(int [] input, int start, int end){
      this.start = start;
      this.end = end;
      this.input = input;
    }

    @Override
    protected int[] compute() {
      System.out.println("Thread ="+Thread.currentThread().getName()+" processing from "+start+" "
          + "to "+end+" inclusive.");
      if(start==end){
        return input;
      }else{
        MergeSubTask mergeSubTask1 = new MergeSubTask(input,start, (start+end)/2);
        MergeSubTask mergeSubTask2 = new MergeSubTask(input, ((start+end)/2)+1, end);

        ForkJoinTask.invokeAll(mergeSubTask1,mergeSubTask2);

        int mid = (start+end)/2,temp;
        int start2 = mid+1;
        while(start<=mid && start2<=end){
          if(input[start]<=input[start2]){
            start++;
          }else{
            temp = input[start2];
            int index = start2;
            while(index!=start){
              input[index] = input[index-1];
              index--;
            }
            input[start]=temp;
            start++;start2++;mid++; // The case of merge of [1,4,7] and [2,3,5]
          }
        }

      }
      return input;
    }
  }
}
