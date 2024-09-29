/*
 ** COPYRIGHT **
 */
package com.ds.binarySearch;

public class BinarySearchInRotatedSortedArray_33 {
   
    static  int search(int[] nums, int target) {
        int len = nums.length;
        if(len ==0){
            return -1;
        }
        
        if(len ==1){
            return target == nums[0] ? 0: -1;
        }
        
        if(len ==2){
            if(target == nums[0])
                return 0;
            else if (target == nums[1])
                return 1;
            else
                return -1;
        }
        
        int pI = getPivot(0, len-1, nums);
        
        // binary search between 0, to pI and pI to len-1 based on pI value
    
        if(pI == len-1){
            return target<=nums[pI] && target>=nums[0] ? binarySearch(0, pI,nums, target) : -1;
        }
        
        
        if(target<=nums[pI] && target>=nums[0]){
            return binarySearch(0, pI,nums, target);
        }
        else {
            return binarySearch(pI+1, len-1, nums, target);
        }
        
    }
    
   static  int binarySearch(int start, int end, int nums[], int target) {
       System.out.println("B.S "+ start +"--" +end+ "--"+ target);
        int mid = (start+end)/2;
        
        if(start == end){
            return nums[start] == target ? start : -1;
        }
        
        if(target<= nums[mid])
            return binarySearch(start, mid, nums, target);
        else
            return binarySearch(mid+1, end, nums, target);
    }
    
    
    static  int getPivot(int start, int end, int nums[]) {
       System.out.println(start+","+end + "::" + nums[start] +"--" + nums[end]);
        int n, last=start, p=0;
        
        // This will happen in this case , [1,3,5] --> pivot will be 5
        if(start == end)
            return end;
        
        if(end == start +1) {
           if(nums[end]<nums[start])
               return start;
           else
               return end;
        }
        
        while(true){
            n = (int)Math.pow(2,p);
            if(start+n<=end){
                if(nums[start+n]<nums[start]){
                    return getPivot(last, start+n, nums);
                }
                else{
                    p = p+1;
                    last = start + n;
                }
            }
            else {
                return  getPivot(last,end, nums);
            }
            
        }
        
    }
    
    public static void main(String[] args) {
       
       int [] nums = new int[]{4,5,6,0,1,2,3};
                //            {0,1,2,3,4,5,6};
        nums = new int[] {6,15,3,4};
       //last = 2, n = 4
        // get(2,4)
        // get(2,3)
       // new int[]{6,0,1}
        // get(0,2)
        //get (0,1)
        
        // new int[]{6,5,1}
        // get(0,2)
        //get (1,2)
       
        nums = new int[]{4,5,6,7,0,1,2};
        nums = new int[] {1,3,5};
        nums = new int[] {57,58,59,62,63,66,68,72,73,74,75,76,77,78,80,81,86,95,96,97,98,100,101,
            102,103,110,119,120,121,123,125,126,127,132,136,144,145,148,149,151,152,160,161,163,
            166,168,169,170,173,174,175,178,182,188,189,192,193,196,198,199,200,201,202,212,218,
            219,220,224,225,229,231,232,234,237,238,242,248,249,250,252,
            253,254,255,257,260,266,268,270,273,276,280,281,283,288,290,291,292,294,295,298,
            299,4,10,13,15,16,17,18,20,22,25,26,27,30,31,34,38,39,40,47,53,54};
        
        
       int res = getPivot(0, nums.length-1, nums);
        
        
        System.out.println(res +"--" + nums[res]);
        
       int r = search(nums, 30);
        System.out.println(r);
        
       
    }

}
