
package com.ds.leetcode.trees;


public class Leetcode_543_Diameter_Of_A_tree {
    
    private class TreeNode {
        
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
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return getData(root)[1];
    }
    
    int[] getData(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new int[]{0, 0};
        }
        
        if (node.left == null) {
            int res[] = getData(node.right);
            res[0] = res[0] + 1;
            if (res[1] < res[0]) {
                res[1] = res[0];
            }
            return res;
        } else if (node.right == null) {
            int res[] = getData(node.left);
            res[0] = res[0] + 1;
            if (res[1] < res[0]) {
                res[1] = res[0];
            }
            return res;
        } else {
            int[] l = getData(node.left);
            int[] r = getData(node.right);
            
            int height = 1 + java.lang.Math.max(l[0], r[0]);
            int diameter = java.lang.Math.max(java.lang.Math.max(l[1], r[1]),
                2 + l[0] + r[0]);
            return new int[]{height, diameter};
            
        }
        
    }
}
