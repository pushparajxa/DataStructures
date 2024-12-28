/*
 ** COPYRIGHT **
 */
package com.ds.trees.binarySearchTree;

public class RanksAndScores {
    
    private static class Node {
        
        Node left, right, parent;
        int score;
        int num_children;
        String playerId;
        
        Node(int score) {
            this.score = score;
        }
        
        Node(int score, String playerId) {
            this.score = score;
            this.playerId = playerId;
        }
        
        Node() {
        
        }
        
        @Override
        public String toString() {
            return "playerId=" + playerId + ", score=" + score + ", num_children=" + num_children
                + ".\n";
        }
    }
    
    private Node root;
    
    private record Pair(int rank, int total_players) {
    
    }
    
    int total_players;
    
    
    private String printTree(Node node) {
        if (node == null) {
            return "";
        } else {
            StringBuffer stringBuffer = new StringBuffer(node.toString());
            stringBuffer.append(printTree(node.left));
            stringBuffer.append(printTree(node.right));
            return stringBuffer.toString();
        }
    }
    
    private Pair addScore(int score, String playerId) {
        if (root == null) {
            total_players++;
            
            root = new Node(score, playerId);
            root.parent = null;
            return new Pair(1, total_players);
        }
        
        Node n = root;
        Node parent = null;
        boolean isLeftChild = true;
        
        while (true) {
            if (score <= n.score) {
                if (n.left == null) {
                    parent = n;
                    break;
                } else {
                    n = n.left;
                }
            } else {
                if (n.right == null) {
                    parent = n;
                    isLeftChild = false;
                    break;
                } else {
                    n = n.right;
                }
            }
            
            
        }
        
        if (isLeftChild) {
            parent.left = new Node(score, playerId);
            parent.left.parent = parent;
            total_players++;
            
        } else {
            parent.right = new Node(score, playerId);
            parent.right.parent = parent;
            total_players++;
        }
        
        // update number of children for all ancestors.
        n = parent;
        while (n != null) {
            n.num_children++;
            n = n.parent;
        }
        
        // get rank
        n = root;
        int rank = 0;
        
        while (!n.playerId.equals(playerId)) {
            if (score <= n.score) {
                n = n.left;
            } else {
                if (n.left != null) {
                    rank = rank + n.left.num_children + 1;
                }
                
                n = n.right;
                
                rank++; // + 1 for n;
            }
            
            
        }
        
        if (n.left != null) {
            rank = rank + n.left.num_children;
        }
        rank = rank + 1;
        
        return new Pair(total_players - rank + 1, total_players);
    }
    
    
    public static void main(String[] args) {
        
        RanksAndScores ranksAndScores = new RanksAndScores();
        
        System.out.println(ranksAndScores.addScore(30, "p1"));
        System.out.println(ranksAndScores.addScore(18, "p2"));
        System.out.println(ranksAndScores.addScore(25, "p3"));
        System.out.println(ranksAndScores.addScore(103, "p4"));
        System.out.println(ranksAndScores.addScore(52, "p5"));
        
        System.out.println(ranksAndScores.printTree(ranksAndScores.root));
    }
    
}
