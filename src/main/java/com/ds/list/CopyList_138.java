/*
 ** COPYRIGHT **
 */
package com.ds.list;

// Copy List, Copy Linked List
class CopyList_138
{
    
    private static class Node {
        int val;
        Node next;
        Node random;
        
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    
    public Node copyRandomList(Node head) {
        
        if(head ==null)
            return null;
        
        if(head.next == null) {
            Node n =  new Node(head.val);
            if(head.random == head)
                n.random = n;
            else
                n.random = null;
                
            return n;
        }
        
        boolean done = false;
        
        // Insert the clone of a node next to itself.
        Node n = head,temp;
        while(!done){
            temp = n.next;
            n.next = new Node(n.val);
            n.next.next = temp;
            n = temp;
            if(temp == null)
                done = true;
        }
        
        
        // Assign the random pointers to the cloned nodes.
        n = head;
        done = false;
        while(!done){
            temp = n.random;
            if(temp == null) {
                n.next.random = null;
            }
            // random pointer referencing to itself.
            else if(temp == n){
                n.next.random = n.next;
            }
            else {
                n.next.random = n.random.next;
            }
            n = n.next.next;
            if(n == null)
                done = true;
        }
        
        
        // Now extract cloned nodes from the chain.
        
        n = head;
        Node result = head.next;
        done = false;
        while(!done){
            temp = n.next;
            n.next = n.next.next;
            
            if(n.next !=null)
                temp.next = n.next.next;
            
            n = n.next;
            
            if(n == null)
                done = true;
        }
        
        return result;
        
    }
    
    public static void main(String[] args) {
        
        Node n = new Node(-1);
        n.random = n;
        
        CopyList_138 copyList138 = new CopyList_138();
        
        Node node = copyList138.copyRandomList(n);
        
        System.out.println(node);
        
    }
    
}