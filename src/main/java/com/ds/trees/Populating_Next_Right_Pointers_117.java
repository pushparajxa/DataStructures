/*
 ** COPYRIGHT **
 */
package com.ds.trees;

import java.util.LinkedList;

public class Populating_Next_Right_Pointers_117 {
    
    // Definition for a Node.
    private static class Node {
        
        public int val;
        public Node left;
        public Node right;
        public Node next;
        
        public Node() {
        }
        
        public Node(int _val) {
            val = _val;
        }
        
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    
    ;
    
    
    // Using LinkedList at the level. Constant Space solution.
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        boolean done = false;
        Node cur, prev, head = root;
        
        while (!done) {
            cur = head;
            prev = null;
            head = null;
            while (cur != null) {
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    }
                    
                    prev = cur.left;
                    if (head == null) {
                        head = prev;
                    }
                }
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    }
                    
                    prev = cur.right;
                    
                    if (head == null) {
                        head = prev;
                    }
                }
                
                cur = cur.next;
            }
            if (head == null) {
                done = true;
            }
            
            
        }
        return root;
    }
    
    
    // Using single queue, breadth first search
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        
        LinkedList<Node> q1 = new LinkedList();
        
        q1.add(root);
        
        boolean done = false;
        Node before = null;
        Node n;
        int i, size;
        
        while (!done) {
            
            before = null;
            i = 0;
            size = q1.size();
            if (size == 0) {
                return root;
            }
            
            while (i < size) {
                n = q1.pollFirst();
                if (n != null) {
                    if (n.left != null) {
                        q1.add(n.left);
                    }
                    if (n.right != null) {
                        q1.add(n.right);
                    }
                }
                if (before != null) {
                    before.next = n;
                    before = n;
                } else {
                    before = n;
                }
                
                i++;
            }
            
        }
        return root;
    }
    
    // Using 2 queues, breadth first search
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        
        LinkedList<Node> q1 = new LinkedList();
        LinkedList<Node> q2 = new LinkedList();
        
        boolean isFQ = true;
        q1.add(root);
        
        boolean done = false;
        LinkedList<Node> fQ, sQ;
        
        while (!done) {
            
            if (isFQ) {
                q2.clear();
                fQ = q1;
                sQ = q2;
                
            } else {
                q1.clear();
                fQ = q2;
                sQ = q1;
            }
            
            Node before = null;
            while (fQ.size() > 0) {
                Node n = fQ.pollFirst();
                if (n != null) {
                    if (n.left != null) {
                        sQ.add(n.left);
                    }
                    if (n.right != null) {
                        sQ.add(n.right);
                    }
                }
                if (before != null) {
                    before.next = n;
                    before = n;
                } else {
                    before = n;
                }
            }
            
            if (sQ.isEmpty()) {
                return root;
            }
            
            if (isFQ) {
                isFQ = false;
            } else {
                isFQ = true;
            }
        }
        return root;
    }
}