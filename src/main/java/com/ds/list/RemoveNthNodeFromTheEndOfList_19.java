/*
 ** COPYRIGHT **
 */
package com.ds.list;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        if(head ==null)
            return null;
        
        if(head.next == null)
            return null;
        
        // We keep pointer to the nth Node from the current node.
        
        // We move forward the current node,  one node,  at a time until it reaches the end of the
        // list.
        
        // We move nthNode pointer one node at a time after we move current node has moved  first
        // "n"
        // steps forward.
        
        ListNode nThNode = head;
        ListNode cur = head;
        ListNode nodeBeforeNthNode = new ListNode(0);
        nodeBeforeNthNode.next = head;
        int i=0;
        
        while(cur!=null){
            if(i>=n){
                nThNode = nThNode.next;
                cur = cur.next;
                nodeBeforeNthNode = nodeBeforeNthNode.next;
                nodeBeforeNthNode.next = nThNode;
            }
            else {
                i++;
                cur = cur.next;
            }
        }
        
        if(i>=n){
            if(nodeBeforeNthNode.next == head){
                return head.next;
            }
            else {
                if(nThNode.next!=null){
                    nodeBeforeNthNode.next = nThNode.next;
                    return head;
                }
                else {
                    nodeBeforeNthNode.next = null;
                    return head;
                }
            }
        }
        else {
            return head;
        }
        
    }
}