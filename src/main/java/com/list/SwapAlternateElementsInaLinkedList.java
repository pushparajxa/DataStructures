package com.list;

import static com.list.LinkedListUtils.*;
public class SwapAlternateElementsInaLinkedList {
    //Input: 1 2 3 4
    //Output : 2 1 4 3
    static Node swapAlternate(Node node){
        Node n1,n2,n3,prev=null;
        Node head = node;
        n1= head;
        while(n1!=null){
            n2 = n1.next;
            if(n2!=null){
                n3= n2.next;
                n1.next = n3;
                n2.next = n1;
                if(prev!=null){
                    prev.next=n2;
                    prev = n1;
                }else{
                    head = n2;
                    prev = n1;
                }
                n1 = n3;
            }else{
                if(prev!=null){
                    prev.next=n1;
                }
                n1 = null;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        printList(node1);
        System.out.println("Swapping");
        Node alternate = swapAlternate(node1);
        printList(alternate);
    }
}
