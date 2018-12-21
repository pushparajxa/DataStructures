package com.list;

public class LinkedListUtils {

    public static void printList(Node node){
        while(node!=null){
            System.out.println(node.value);
            node = node.next;
        }
    }

    public static class Node{
        int value;
        Node next;

        public Node(int i) {
            this.value=i;
        }
    }
}
