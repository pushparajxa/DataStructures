package com;

public class binaryTreeUtils {

    boolean checkValidityOfABinarySearchTree(Node node,int min, int max  ){

        if(node!=null){
            if(node.value>=min && node.value <=max){
                return checkValidityOfABinarySearchTree(node.left, min,node.value) &&
                        checkValidityOfABinarySearchTree(node.right,node.value,max);
            }else{
                return false;
            }
        }else{
            return true;
        }

    }

    static class Node{
        Node parent,left,right;
        int value;
    }
}
