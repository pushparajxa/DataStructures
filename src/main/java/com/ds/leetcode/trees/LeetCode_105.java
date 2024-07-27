
package com.ds.leetcode.trees;

import java.util.HashMap;

public class LeetCode_105 {
    
    
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 

    
    static class Solution {
    
        HashMap<Integer, Integer> pTable = new HashMap<>();
        HashMap<Integer, Integer> iTable = new HashMap<>();
        HashMap<Integer, TreeNode> nTable = new HashMap<>();
    
        public TreeNode BuildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            if (preorder.length == 1) {
                return new TreeNode(preorder[0], null, null);
            }
            
            for (int i = 0; i < preorder.length; i++) {
                pTable.put(preorder[i],i);
                iTable.put(inorder[i],i);
            }
            
            return doBuildTree(preorder, 0, preorder.length-1, false);
        }
        
        
        
        public TreeNode doBuildTree(int[] preorder, int start, int end, boolean isLeft) {
            if(start == end){
                if(nTable.containsKey(preorder[start])) {
                    return nTable.get(preorder[start]);
                }
                else {
                    TreeNode node = new TreeNode(preorder[start], null, null);
                    nTable.put(preorder[start], node);
                    return node;
                }
            }
            
            int p = preorder[start], l, r;
            int pInd = iTable.get(p);
            if(isLeft)
                pInd = pInd + start;
            
            TreeNode parent, left, right;
            
            if(pInd == start) {
                left = null;
            }
            else {
                l = preorder[start+1];
                if(nTable.containsKey(l)){
                    left = nTable.get(l);
                }
                else {
                    left = new TreeNode(l, null, null);
                    nTable.put(l, left);
                }
            }
            
            if(iTable.get(p) == end) {
                right = null;
            }
            else {
                r = preorder[pInd+1];
                if(nTable.containsKey(r)) {
                    right = nTable.get(r);
                }
                else {
                    right = new TreeNode(r, null, null);
                    nTable.put(r, right);
                }
            }
            
            if(nTable.containsKey(p)){
                parent = nTable.get(p);
                parent.left = left;
                parent.right = right;
            }
            else {
                parent =  new TreeNode(preorder[start],left, right);
                nTable.put(start, parent);
            }
            
            if(left!=null){
                doBuildTree(preorder, start+1, pInd, true);
            }
            
            if(right!=null){
                doBuildTree(preorder,pInd+1, end,false);
            }
            
            return parent;
        }
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
      // TreeNode treeNode = solution.BuildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15,20,7});
    
       // TreeNode treeNode = solution.BuildTree(new int[]{3,4,6,10,11,12}, new int[]{4,6,3,11,10,12});
    
        TreeNode treeNode = solution.BuildTree(new int[]{3,4,6,10,11,12}, new int[]{6,4,3,11,10, 12});
        System.out.println(treeNode);
    }
}
