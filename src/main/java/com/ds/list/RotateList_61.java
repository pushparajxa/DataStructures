/*
 ** COPYRIGHT **
 */
package com.ds.list;

import java.util.HashMap;
import java.util.LinkedList;

// Rotate given linked list to the right by K places.
public class RotateList_61 {
    
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null) {
            return head;
        }
        
        int len=0;
        ListNode cur = head;
        ListNode kNode;
        int j=0;
        
        while(cur.next!=null){
            len++;
            cur = cur.next;
        }
        len++;
        
        ListNode end = cur;
        
        
        if(len == 1)
            return head;
        
        int r = k%len;
        
        if(r==0)
            return head;
        
        end.next = head;
        
        cur = head;
        int l=1;
        
        while(l<=len-r-1){
            cur= cur.next;
            l++;
        }
        head = cur.next;
        cur.next = null;
        
        return head;
        
        
    }
    
    public ListNode rotateRight2(ListNode head, int k) {
        
        
        if(head == null) {
            return head;
        }
        
        int len=0;
        ListNode cur = head;
        
        HashMap<Integer, ListNode> map = new HashMap();
        while(cur!=null){
            len++;
            map.put(len,cur);
            cur = cur.next;
        }
        
        if(len == 1)
            return head;
        
        int r = k%len;
        
        if(r==0)
            return head;
        
        // System.out.println(map);
        ListNode pivot = map.get(len -r+1);
        ListNode bpivot = map.get(len -r);
        // System.out.println(bpivot + "   "+ len);
        bpivot.next = null;
        
        ListNode lastNode = map.get(len);
        
        lastNode.next = head;
        head = pivot;
        return head;
        
        
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.clear();
    }
}