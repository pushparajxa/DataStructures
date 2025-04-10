/*
 ** COPYRIGHT **
 */
package com.ds.list;

public class MergeTwoSortedLists_21 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        
        
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            
            if(list1==null)
                return list2;
            
            if(list2==null)
                return list1;
            
            ListNode t1 = list1, t2=list2, h1 = list1, h2 = list2;
            
            ListNode s, start;
            
            // Logic:: We pick one node at a time from the given two lists
            
            // The head of the list is one of the heads of the two lists.
            if(h2.val <= h1.val){
                start = h2;
                t2 = t2.next;
            }
            else {
                start = h1;
                t1 = t1.next;
            }
            
            s = start;
            
            // We go over until t1 and t2 become null.
            // Each time, we compare the heads of the two lists, and select the smaller one and
            // make it as the next element of the result "s".
            // Then we move the head of the selected element's list.
            while(t1!=null && t2!=null){
                if(s.val == t2.val){
                    s.next = t2;
                    t2 = t2.next;
                    s = s.next;
                }
                else if(s.val == t1.val){
                    s.next = t1;
                    t1 = t1.next;
                    s = s.next;
                }
                else if(t2.val == t1.val){
                    s.next = t2;
                    s = s.next;
                    t2 = t2.next;
                }
                else if(t2.val > t1.val){
                    s.next = t1;
                    s = s.next;
                    t1 = t1.next;
                }
                else if(t2.val < t1.val){
                    s.next = t2;
                    s = s.next;
                    t2 = t2.next;
                }
                else {
                    System.out.println("We should not reach this level");
                }
                
            }
            
            
            if(t1 == null){
                if(t2!=null){
                    s.next = t2;
                    return start;
                }
                else {
                    return start;
                }
            }
            else {
                if(t2 == null){
                    s.next = t1;
                    return start;
                }
                else {
                    // This case should not arise.
                    System.out.println("This case should not araise.");
                    return start;
                }
            }
           
        }
        
    }
    }