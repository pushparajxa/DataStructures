/*
 ** COPYRIGHT **
 */
package com.ds.list;

class ListNode {
    
    int val;
    ListNode next;
    
    ListNode() {
    }
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseBetweenGivenStartAndEndPositions_92 {
    
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)
            return head;
        
        int i=1;
        ListNode cur,bstart=null, start, end;
        if(left==1){
            start = head;
        }
        else {
            cur = head;
            while(i!=left-1){
                cur = cur.next;
                i++;
            }
            
            bstart = cur;
            start = cur.next;
        }
        
        cur = start.next;
        i=left+1;
        ListNode temp, prev = start;
        
        while(i!=right){
            temp = cur.next;
            cur.next = prev;
            prev= cur;
            cur = temp;
            i++;
        }
        
        end = cur;
        temp = cur.next;
        cur.next = prev;
        if(left!=1)
            bstart.next = end;
        start.next = temp;
        
        
        if(left==1)
            return end;
        else
            return head;
        
    }
    
    
    
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        
        int i = 1;
        ListNode cur, bstart = null, start, end;
        if (left == 1) {
            start = head;
        } else {
            cur = head;
            while (i != left - 1) {
                cur = cur.next;
                i++;
            }
            
            bstart = cur;
            start = cur.next;
        }
        
        cur = start.next;
        i = left + 1;
        while (i != right) {
            i = i + 1;
            cur = cur.next;
        }
        
        end = cur;
        
        cur = start.next;
        ListNode temp, prev = start;
        
        while (cur != end) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        
        temp = cur.next;
        cur.next = prev;
        if (left != 1) {
            bstart.next = end;
        }
        start.next = temp;
        
        if (left == 1) {
            return end;
        } else {
            return head;
        }
        
    }
    
    
}