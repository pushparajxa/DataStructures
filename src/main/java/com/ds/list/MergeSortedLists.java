/*
 ** COPYRIGHT **
 */
package com.ds.list;

public class MergeSortedLists {
    
    public static void main(String[] args) {
        
        Node n4 = new Node(4);
        
        Node n7 =  new Node(7);
        Node n19 =  new Node(19);
        
        n4.down = n7;
        n7.down = n19;
        
        Node n5 = new Node(5);
        
        Node n11 =  new Node(11);
        Node n14 =  new Node(14);
        Node n15 =  new Node(15);
        
        n11.down = n14;
        n14.down = n15;
        
        n4.next = n5;
        n5.next = n11;
        
        MergeSortedLists mergeSortedLists = new MergeSortedLists();
       Node sorted =  mergeSortedLists.mergeLinkedList(n4);
       
       mergeSortedLists.printList(sorted);
    }
    
    Node mergeLinkedList(Node n){
        if(n==null)
            return null;
        
        Node cur = n;
        Node next = cur.next, temp;
        cur = flatten(cur);
        
        if(next == null)
            return cur;
        
        while(true) {
            if (next == null)
                return cur;
            
            temp = next.next;
            cur = sortDowns(cur, next);
           // printList(cur);
            next = temp;
            
        }
    }
    // n1 would be already sorted and flattened with down values as zero and chained with "next"
    Node sortDowns(Node n1, Node n2){
        Node n1cur=n1, n2cur = n2;
        Node res = new Node(Integer.MAX_VALUE);
        Node cur = res;
        
        
        boolean done = false;
        while(!done){
            
            if(n1cur.val <= n2cur.val){
                cur.next = n1cur;
                cur = cur.next;
                n1cur = n1cur.next;
                cur.down = null;
                
            }
            else {
                cur.next = n2cur;
                cur = cur.next;
                n2cur = n2cur.down;
                cur.down = null;
            }
            
            if(n1cur == null ){
                if(n2cur == null){
                    return res.next;
                }
                else {
                   cur.next = flatten(n2cur);
                   done = true;
                }
            }
            else {
                if(n2cur==null){
                    
                    cur.next = n1cur;
                    done = true;
                }
            }
        }
    
        return res.next;
    
    }
    
    Node flatten(Node n){
        
        Node cur = n;
        
       while(cur!=null) {
           cur.next = cur.down;
           cur.down = null;
           cur = cur.next;
       }
       
       return n;
    }
    
    Node getEndOf(Node n){
        Node cur = n;
        
        while(cur.next!=null)
            cur = cur.next;
        
        return cur;
    }
    
    void printList(Node n){
        
        Node cur = n;
        
        while(cur!=null) {
            System.out.print(cur.val + ",");
            cur = cur.next;
        }
        
        System.out.println();
    }
    
   static  class Node{
        int val;
        Node next, down;
        
        public Node(int val){
            this.val = val;
        }
        
        @Override
       public String toString(){
            return ""+val;
        }
    }
    
    
}
