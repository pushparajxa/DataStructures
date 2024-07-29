
package com.ds.leetcode.trees;

import java.util.Hashtable;

public class LeetCode_105_4 {
    
    
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
    
    
    int preOrderIndex=0;
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
        
        return doBuildTree(preorder,0, preorder.length-1, iTable);
        
    
        
    }
    
    TreeNode doBuildTree(int[] preorder, int start, int end, Hashtable<Integer, Integer> iTable) {
        
        if(start>end) {
            return null;
        }
        int root = preorder[preOrderIndex];
        preOrderIndex++;
        TreeNode node = new TreeNode(root);
        int iIndex = iTable.get(root);
        
        node.left = doBuildTree(preorder, start, iIndex -1, iTable );
        node.right = doBuildTree(preorder, iIndex +1, end, iTable);
        
        return node;
        
    }
    

    
    
    
    public static void main(String[] args) {
        LeetCode_105_4 test = new LeetCode_105_4();
        TreeNode treeNode;
        treeNode = test.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
        
        test.preOrderIndex = 0;
        treeNode = test.buildTree(new int[]{3, 4, 6, 10, 11, 12}, new int[]{4, 6, 3, 11, 10, 12});
        System.out.println(treeNode);
    
        test.preOrderIndex = 0;
        treeNode = test.buildTree(new int[]{3, 4, 6, 10, 11, 12}, new int[]{6, 4, 3, 11, 10, 12});
        System.out.println(treeNode);
    }
}
