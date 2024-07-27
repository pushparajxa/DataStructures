
package com.ds.leetcode.linkedlist;

import java.util.HashMap;

public class FlattenMultiLevelLinkedList {
    
    static  class Node {
    
        Node next, down;
        int value;
    
        Node(int value) {
            this.value = value;
        }
        
        static Node createNode(int value){
            return new Node(value);
        }
        
        Node setNext(Node n){
            next = n;
            return this;
        }
        
        Node setDown(Node n) {
            down = n;
            return this;
        }
        
        @Override
        public String toString(){
            return "" + value;
        }
        
    }
    
    static  Node flatten(Node node) {
        Node temp = node;
        Node head = node;
        
        while (temp != null) {
            if (temp.down != null) {
                Node next = temp.next;
                Node last = flattenDown(temp);
                last.next = next;
                temp = next;
            } else {
                temp = temp.next;
            }
            
        }
        return head;
    }
    
    
    /*
        For the given input node, flatten the lists below it and return the last element of that
        flattened list.
     */
   static  Node flattenDown(Node node) {
        Node head = node.down;
        
        Node temp = head;
        
        Node nLast = null;
        
        while (temp != null) {
            if (temp.down != null) {
                Node next = temp.next;
                Node last = flattenDown(temp);
                last.next = next;
                temp = next;
                if(next == null) {
                    nLast = last;
                }
            } else {
                
                if (temp.next == null) {
                    nLast = temp;
                    temp = null;
                }
                else {
                    temp = temp.next;
                }
            }
        }
        
        node.next = head;
        node.down = null;
     
        return nLast;
    }
    

    
 
    
    public static void main(String[] args) {
       // Built this example for this source https://media.geeksforgeeks.org/wp-content/cdn-uploads/flattenList.png
        // The example is not exactly same. It has been trimmed down.
       int l1[] = new int[] {10,5,12,7,11};
       int l2[] = new int[] {4, 20, 13};
       int l3[] = new int[] {2};
       int l4[] = new int[] {3};
       int l5[] = new int[] {16};
        
        Node n1 = buildList(l1);
        Node n2 = buildList(l2);
        Node n3 = buildList(l3);
        Node n4 = buildList(l4);
        Node n5 = buildList(l5);
        
        map.get(10).down = n2;
        map.get(20).down = n3;
        map.get(13).down = n5;
        map.get(16).down = n4;
    
        System.out.println("Built the linkedList");
        
        Node value  = flatten(n1);
    
        System.out.println("After flattening down");
        
    }
    
    static HashMap<Integer, Node> map = new HashMap<>();
    
    private static Node buildList(int [] l1) {
        Node prev = Node.createNode(l1[0]);
        map.put(l1[0], prev);
        Node head = prev;
        for (int i = 1; i < l1.length; i++) {
            Node tmp = Node.createNode(l1[i]);
            map.put(l1[i], tmp);
            prev.next = tmp;
            prev = tmp;
        }
        prev.next = null;
        return head;
    }
    
}
