
package com.ds.leetcode.trees;

public class LeetCode_114_Flatten_Binary_Tree {
    

      public class TreeNode {
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
     
        public void flatten2(TreeNode root) {
            if(root == null)
                return ;
            flattenChild(root);
        }
        
        TreeNode flattenChild(TreeNode node) {
            if(node.right == null && node.left == null) {
                return node;
            }
            if(node.left == null){
                return flattenChild(node.right);
                
            }
            if(node.right == null){
                TreeNode temp = flattenChild(node.left);
                node.right = node.left;
                node.left = null;
                return temp;
            }
            
            TreeNode last = flattenChild(node.left);
            last.right = node.right;
            node.right = node.left;
            node.left = null;
            return flattenChild(last.right);
        }
    
    //With Morris travel
    public void flatten(TreeNode root) {
        if(root == null)
            return ;
        TreeNode next = root;
        while(next!=null) {
            if(next.left == null && next.right == null) {
                next = null;
            }
            else if(next.left == null){
                next = next.right;
            }
            else if(next.right == null) {
                next.right = next.left;
                next.left = null;
                next = next.right;
            }
            else {
                TreeNode firstRightMost = findFirstRightNodeUnder(next.left);
                firstRightMost.right = next.right;
                next.right = next.left;
                next.left = null;
                next = next.right;
            }
        }
    }
    
    TreeNode findFirstRightNodeUnder(TreeNode node) {
        if(node.right == null)
            return node;
        
        while(node.right!=null){
            node = node.right;
        }
        
        return node;
    }
}
