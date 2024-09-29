/*
 ** COPYRIGHT **
 */
package com.ds.binarySearch;

public class GetPivot_Array {
    
    
    // [5,0,1] ---> 0
    // [5,6,0] ---> 1
    // [5,6,7] ---> 2
    private static int getPivot2(int start, int end, int[] nums) {
    
        int n,p=0;
        
        n = (int)Math.pow(2,p);
        if(start+n>end){
        
        }
        else {
        
        }
        return 0;
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
        
        int[] nums = new int[]{4, 5, 6, 0, 1, 2, 3};
        //            {0,1,2,3,4,5,6};
        nums = new int[]{6, 15, 3, 4};
        //last = 2, n = 4
        // get(2,4)
        // get(2,3)
        // new int[]{6,0,1}
        // get(0,2)
        //get (0,1)
        
        // new int[]{6,5,1}
        // get(0,2)
        //get (1,2)
        
        nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        nums = new int[]{1, 3, 5};
        nums = new int[]{57, 58, 59, 62, 63, 66, 68, 72, 73, 74, 75, 76, 77, 78, 80, 81, 86, 95, 96,
            97, 98, 100, 101,
            102, 103, 110, 119, 120, 121, 123, 125, 126, 127, 132, 136, 144, 145, 148, 149, 151,
            152, 160, 161, 163,
            166, 168, 169, 170, 173, 174, 175, 178, 182, 188, 189, 192, 193, 196, 198, 199, 200,
            201, 202, 212, 218,
            219, 220, 224, 225, 229, 231, 232, 234, 237, 238, 242, 248, 249, 250, 252,
            253, 254, 255, 257, 260, 266, 268, 270, 273, 276, 280, 281, 283, 288, 290, 291, 292,
            294, 295, 298,
            299, 4, 10, 13, 15, 16, 17, 18, 20, 22, 25, 26, 27, 30, 31, 34, 38, 39, 40, 47, 53, 54};
        
        int res = getPivot(0, nums.length - 1, nums);
        
        System.out.println(res + "--" + nums[res]);
    }
    

}
