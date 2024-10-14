/*
 ** COPYRIGHT **
 */
package com.ds.list;

public class Partition_List_LeetCode_86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode bh = new ListNode(), prev = bh, cur = bh;
        bh.next = head;
        boolean init = false;
        
        while (cur.next != null) {
            if (cur.next.val < x) {
                if (init) {
                    ListNode temp = prev.next;
                    prev.next = cur.next;
                    ListNode temp2 = cur.next.next;
                    cur.next.next = temp;
                    cur.next = temp2;
                    prev = prev.next;
                }
                else {
                    cur= cur.next;
                }
            } else {
                if(!init){
                    init = true;
                    prev = cur;
                }
                cur = cur.next;
            }
        }
        
        return bh.next;
        
    }
}
