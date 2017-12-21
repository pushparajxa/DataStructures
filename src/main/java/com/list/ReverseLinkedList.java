package com.list;
/*
Reverse a singly linked list.
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

        ReverseLinkedList linkedList = new ReverseLinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        linkedList.printList(node1);
        System.out.println("After Reversing");
        Node head = linkedList.reverse(node1);
        linkedList.printList(head);
    }

    private Node reverse(Node head){
        Node previous = null;
        Node current=head,temp2;
        while(current!=null){
            temp2=current.next;
            current.next=previous;
            previous=current;
            current=temp2;
        }
        return previous;
    }

    void printList(Node node){
        while(node!=null){
            System.out.println(node.value);
            node = node.next;
        }
    }

    static class Node{
        int value;
        Node next;

        public Node(int i) {
            this.value=i;
        }
    }
}
