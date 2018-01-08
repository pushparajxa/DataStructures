
package com.sort;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class SortTestCases {

    public static int[] generateSample(int size, int randomness) {
        int[] sample = new int[size];

        Random random = new Random(System.currentTimeMillis());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            if (random.nextInt(100) >= randomness) {
                sample[i] = random.nextInt(100);
            } else {
                sample[i] = random.nextInt(size);
            }
        }

        return sample;
    }


    @DataProvider(name = "algorithms")
    public  Object[][] primeNumbers() {
       // String sortClassName = "com.sort.RadixSort";
        //String sortClassName = "com.sort.MergeSort";
        //String sortClassName = "com.sort.QuickSort";
      // String sortClassName = "com.sort.ParallelMergeSort";
       // String sortClassName = "com.sort.HeapSort";
        String sortClassName = "com.sort.BucketSort";

        return new Object[][]{

                { sortClassName, new int[]{3,2,1} },

                { sortClassName, new int[]{3,2,2} },

                { sortClassName, new int[]{0}},

                { sortClassName, new int[]{} },

                { sortClassName, new int[]{1,1} },

                { sortClassName, new int[]{1,1,1} },

                { sortClassName, new int[]{1,2,3}},

                { sortClassName, new int[]{3,7,1}}

        };

    }

    @Test(dataProvider="algorithms")
    void testSortBasic(String className, int [] input)  {
        int[] expectedResult = Arrays.copyOf(input,input.length);
        System.out.print(Arrays.toString(expectedResult) + "   ");
        Arrays.sort(expectedResult);
        int[] myResult = sortUsingGivenAlgorithm(input, className);
        System.out.println(Arrays.toString(myResult));
        Assert.assertTrue(Arrays.equals(myResult,expectedResult));
    }

    @Test
    void testRandomSamples(){
        //String sortClassName = "com.sort.SelectionSort";
        //String sortClassName = "com.sort.HeapSort";
        //String sortClassName = "com.sort.InsertionSort";
        //String sortClassName = "com.sort.BucketSort";
        //String sortClassName = "com.sort.CountingSort";
        //String sortClassName = "com.sort.RadixSort";
        //String sortClassName = "com.sort.MergeSort";
        //String sortClassName = "com.sort.QuickSort";
       // String sortClassName = "com.sort.ParallelMergeSort";
       // String sortClassName = "com.sort.HeapSort";
        String sortClassName = "com.sort.BubbleSort";

        for(int i=0;i<20;i++){
            int[] input = generateSample(10,100);
            int[] expectedResult = Arrays.copyOf(input,input.length);
            Arrays.sort(expectedResult);

            System.out.println("Input="+Arrays.toString(input));
            int[] myResult = sortUsingGivenAlgorithm(input,sortClassName);

            System.out.println("Output="+Arrays.toString(myResult));

            Assert.assertTrue(Arrays.equals(myResult,expectedResult));
        }
    }

    static int [] sortUsingGivenAlgorithm(int [] arr, String className){
        //System.out.println("The input className is "+className);
        try {
            Class  sorter = Class.forName(className);
            Method method = sorter.getDeclaredMethod("sortArray",new Class[]{int[].class});
            method.setAccessible(true);
            method.invoke(null,arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

}
