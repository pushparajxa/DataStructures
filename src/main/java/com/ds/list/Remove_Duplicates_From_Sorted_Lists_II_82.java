/*
 ** COPYRIGHT **
 */
package com.ds.list;

public class Remove_Duplicates_From_Sorted_Lists_II_82 {
    
    
    
    public ListNode deleteDuplicates(ListNode head) {
        
        if (head == null || head.next == null)
            return head;
        
        ListNode prev = new ListNode(), cur = head;
        prev.next = cur;
        
        while (true) {
            if (cur.next != null && cur.val != cur.next.val) {
                prev = cur;
                cur = cur.next;
                continue;
            }
            
            if (cur.next == null)
                return head;
            
            else {
                ListNode temp = cur.next;
                //System.out.println(temp.val);
                while (temp != null && temp.val == cur.val){
                    temp = temp.next;
                }
                //System.out.println(temp.val);
                //System.out.println(prev.val);
                if (temp == null) {
                    if (prev.next == head) {
                        head = null;
                        return null;
                    } else {
                        prev.next = null;
                        return head;
                    }
                } else {
                    if (prev.next == head) {
                        head = temp;
                        prev.next = head;
                        cur = temp;
                    } else {
                        prev.next = temp;
                        System.out.println(prev.val);
                        cur = temp;
                    }
                }
                
            }
        }
        
    }
    
}
