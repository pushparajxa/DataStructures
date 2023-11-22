
package com.ds.sort;
//Not Stable
public class HeapSort {
   //Binary Heap
  /*
  A binary heap is a heap data structure that takes the form of a binary tree.
     Binary heaps are a common way of implementing priority queues.[1]:162–163
     The binary heap was introduced by J. W. J. Williams in 1964, as a data structure for heapsort.[2]

    A binary heap is defined as a binary tree with two additional constraints:[3]

    Shape property: a binary heap is a complete binary tree;
    that is, all levels of the tree, except possibly the last one (deepest) are fully filled, and,
    if the last level of the tree is not complete, the nodes of that level are filled from left to right.

    Heap property: the key stored in each node is either greater than or equal to (≥) or less than or equal to (≤) the keys in the node's children,
    according to some total order.
   */

    /*
    returns array sorted in ascending order by building a max-heap.
    //The link below explains why the binary-heap needs to be a complete binary tree.
    https://courses.cs.washington.edu/courses/cse373/06sp/handouts/lecture10.pdf
     */
    private static void sortArray(int[] arr) {
        if(arr.length==0)
            return;
        //Build (max)heap either using top-down or bottom-up fashion
       // heapify_topdown(arr);
       // heapify_bottomup(0,arr);
        heapify_floyd(arr);

        //Sort the heap
        int i = 0;
        while (i < arr.length) {
            swap(0, arr.length - 1 - i, arr);
            siftDown(0, arr.length - 1 - i, arr);
            i++;
        }
        //Arrays.toString(arr);
    }

    static void heapify_topdown(int[] arr) {
        int i = 1;
        while (i < arr.length) {
            siftUp(i, arr);
            i++;
        }
    }

    /*
       Floyd runs in o(n) time https://www.cs.duke.edu/courses/spring05/cps130/lectures/skiena.lectures/lecture4.pdf
       http://lcm.csa.iisc.ernet.in/dsa/node139.html
     */
    static void heapify_floyd(int[] arr) {
        int i = (arr.length-1)/2;
        while (i >=0) {
           siftDown(i,arr.length,arr);
            i--;
        }
    }

    //Bottom-up heap construction takes O(n) time ..read Section 2.4.4 Goodrich (Algorithm Design)
    // For bottom-up heap construction we should have all the elements in hand before
    // constructing it.
    // The proof for it is https://stackoverflow.com/a/18295327/1171533
    static void heapify_bottomup(int parentIndex, int[] arr) {
        if (parentIndex > arr.length - 1)
            return;
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;

        heapify_bottomup(leftChildIndex, arr);
        heapify_bottomup(rightChildIndex,arr);

        siftDown(parentIndex,arr.length,arr);

    }

    static void swap(int from, int to, int[] arr) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    static void siftUp(int i, int[] arr) {
        int parent = (i - 1) / 2;
        if (arr[parent] < arr[i]) {
            swap(parent, i, arr);
        } else {
            return;
        }
        if (parent != 0)
            siftUp(parent, arr);
    }

    static void siftDown(int parent, int maxLength, int[] arr) {
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int leftChildValue = 0, rightChildValue = 0, parentValue = arr[parent];
        int length = maxLength;
        boolean leftExist = leftChild < length ? true : false;
        boolean rightExist = rightChild < length ? true : false;
        if (leftExist) leftChildValue = arr[leftChild];
        if (rightExist) rightChildValue = arr[rightChild];

        if (leftExist && rightExist) {
            if (leftChildValue >= rightChildValue) {

                if (parentValue < leftChildValue) {
                    swap(parent, leftChild, arr);
                    siftDown(leftChild, maxLength, arr);
                } else {
                    return;
                }

            } else {

                if (parentValue < rightChildValue) {
                    swap(parent, rightChild, arr);
                    siftDown(rightChild, maxLength, arr);
                } else {
                    return;
                }

            }
        } else if (leftExist) {

            if (parentValue < leftChildValue) {
                swap(parent, leftChild, arr);
                siftDown(leftChild, maxLength, arr);
            } else {
                return;
            }

        } else if (rightExist) {
            //only rightExist .. this should never happen as heap is a complete binary tree, at the bottom level nodes exist to the left
            throw new RuntimeException("Unexpected flow");
        } else {
            //We reached an external node..so just return
            return;
        }

    }

    public static void main(String[] args) {
        sortArray(new int[]{13, 17, 0, 1, 3, 2, 16, 2, 5, 12, 3, 9, 6, 14, 5, 1, 14, 1, 11, 9});
    }

}

