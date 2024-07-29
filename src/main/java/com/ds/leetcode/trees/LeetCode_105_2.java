
package com.ds.leetcode.trees;

import java.util.Hashtable;

public class LeetCode_105_2 {
    
    
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
    }
    
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
        
        return doBuildTree(preorder, inorder, 0, preorder.length-1, 0, iTable);
    }
    
    private TreeNode doBuildTree(int [] preorder, int [] inorder, int pStart, int pEnd, int iStart, Hashtable<Integer, Integer> iTable) {
        if(pStart > pEnd){
            return null;
        }
        int root = preorder[pStart];
        TreeNode node = new TreeNode(root);
        if(root == inorder[iStart]) {
            node.left = null;
            node.right = doBuildTree(preorder, inorder, pStart + 1, pEnd, iStart + 1, iTable);
        }
        else {
            int iIndex = iTable.get(root);
            if(iIndex  == pStart) {
               
                node.left = doBuildTree(preorder, inorder, pStart+1, pStart + iIndex - iStart -1,
                    iStart,
                    iTable);
                
                node.right = doBuildTree(preorder, inorder, pStart + iIndex - iStart, pEnd,
                    iIndex+1, iTable);
                
            }
            else {
                node.left = doBuildTree(preorder, inorder, pStart + 1, iIndex, iStart, iTable);
                node.right = doBuildTree(preorder, inorder, iIndex + 1, pEnd, iIndex + 1, iTable);
            }
            
        }
        return node;
        
    }
    
    
    
    
    public static void main(String[] args) {
        LeetCode_105_2 test = new LeetCode_105_2();
        TreeNode treeNode;
        treeNode = test.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15,20,7});
    
        System.out.println(treeNode);
        
        treeNode = test.buildTree(new int[]{3,4,6,10,11,12}, new int[]{4,6,3,11,10,12});
    
        System.out.println(treeNode);
       
        treeNode = test.buildTree(new int[]{3, 4, 6, 10, 11, 12}, new int[]{6, 4, 3, 11, 10, 12});
        System.out.println(treeNode);
    }
}
