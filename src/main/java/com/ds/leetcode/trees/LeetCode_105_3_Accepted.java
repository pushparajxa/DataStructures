
package com.ds.leetcode.trees;

import java.util.Hashtable;

public class LeetCode_105_3_Accepted {
    
    
    public static class TreeNode {
        
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {
        }
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString() {
            return "" + val;
        }
    }
    
    
    private int[] p, i;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        Hashtable<Integer, Integer> iTable = new Hashtable();
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0], null, null);
        }
        
        for (int i = 0; i < preorder.length; i++) {
            iTable.put(inorder[i],i);
        }
        
        p = new int[preorder.length];
        i = new int[inorder.length];
        
        return doBuildTree(preorder, inorder, 0, preorder.length-1, iTable, 0);
        
    }
    
    TreeNode doBuildTree(int[] preorder, int[] inorder, int start, int end, Hashtable<Integer, Integer> iTable, int hashOffset){
        
        if(start > end) {
            return null;
        }
        else{
            if (start == end) {
                return new TreeNode(preorder[start]);
            }
        }
        
        int node = preorder[start];
        int iIndex  = iTable.get(node);
        iIndex = iIndex - hashOffset;
        
        TreeNode root = new TreeNode(node);
        
        System.arraycopy(inorder, start, i, 0, iIndex-start);
        System.arraycopy(preorder, start+1, p, 0, iIndex-start);
        root.left = doBuildTree(p, i, 0, iIndex-start-1, iTable, hashOffset + start);
        
        
        System.arraycopy(inorder, iIndex + 1, i, 0, end - iIndex );
        System.arraycopy(preorder, iIndex + 1, p, 0, end - iIndex);
        root.right = doBuildTree(p, i, 0, end - iIndex-1 , iTable, hashOffset + iIndex + 1);
        
        
        return root;
        
        
    }
    
    
    public static void main(String[] args) {
        LeetCode_105_3_Accepted test = new LeetCode_105_3_Accepted();
        TreeNode treeNode;
        treeNode = test.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        
        System.out.println(treeNode);
        
        treeNode = test.buildTree(new int[]{3, 4, 6, 10, 11, 12}, new int[]{4, 6, 3, 11, 10, 12});
        
        System.out.println(treeNode);
        
        treeNode = test.buildTree(new int[]{3, 4, 6, 10, 11, 12}, new int[]{6, 4, 3, 11, 10, 12});
        System.out.println(treeNode);
    }
}
