/*
 ** COPYRIGHT **
 */
package com.ds.recursion;

public class QuadTree_427 {

// Definition for a QuadTree node.
    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    
        
        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }


    static record Pair(int row, int col){};
    
    public Node construct(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if(rows == 1) {
            return new Node(grid[0][0]==1? true:false, true);
        }
        
        if(rows == 2){
            Node root = new Node();
            int tl = grid[0][0];
            int tr = grid[0][1];
            int bl = grid[1][0];
            int br = grid[1][1];
            
            if(checkIfAllAreSame(tl,tr, bl,br) ){
                root.val = tl == 1? true: false;
                root.isLeaf = true;
                return root;
            }
            
            
            Node tlN = new Node(tl == 1? true: false, true);
            Node trN = new Node(tr == 1? true: false, true);
            Node blN = new Node(bl == 1? true: false, true);
            Node brN = new Node(br == 1? true: false, true);
            
            root.isLeaf = false;
            root.topLeft = tlN;
            root.topRight = trN;
            root.bottomLeft = blN;
            root.bottomRight = brN;
            
            return root;
            
        }
        
        return doBuildTree(grid, new Pair(0,0), new Pair(rows-1, cols-1));
        
    }
    
    
    
    Node doBuildTree(int grid[][], Pair a, Pair b){
        System.out.println(a +" " +b);
        if(Math.abs(a.row - b.row) ==1){
            // System.out.println(a.row +"---" +b.row);
            Node root = new Node();
            int tl = grid[a.row][a.col];
            int tr = grid[a.row][b.col];
            int bl = grid[b.row][a.col];
            int br = grid[b.row][b.col];
            // System.out.println(tl+"-"+tr+"-"+bl+"-"+br+"::"+(tl & tr & bl & br) +"::"+ (~tl & ~tr & ~bl & ~br));
            
            if(checkIfAllAreSame(tl,tr, bl,br)){
                System.out.println("This will be a leaf node");
                root.val = tl ==1 ? true:false;
                root.isLeaf = true;
                return root;
            }
            
            
            Node tlN = new Node(tl == 1? true: false, true);
            Node trN = new Node(tr == 1? true: false, true);
            Node blN = new Node(bl == 1? true: false, true);
            Node brN = new Node(br == 1? true: false, true);
            
            root.isLeaf = false;
            root.topLeft = tlN;
            root.topRight = trN;
            root.bottomLeft = blN;
            root.bottomRight = brN;
            
            return root;
        }
        
        Node root = new Node();
        
        int midR = (a.row + b.row)/2;
        int midC = (a.col + b.col)/2;
        
        Node tl = doBuildTree(grid, a, new Pair(midR, midC));
        Node tr = doBuildTree(grid, new Pair(a.row, midC+1), new Pair(midR,b.col));
        Node bl = doBuildTree(grid, new Pair(midR+1, a.col), new Pair(b.row,midC));
        Node br = doBuildTree(grid, new Pair(midR+1, midC+1), b);
        
        boolean tlVal = tl.val;
        boolean trVal = tr.val;
        boolean blVal = bl.val;
        boolean brVal = br.val;
        
        if(tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf){
            if((tlVal && trVal && blVal && brVal) ||(!tlVal && !trVal && !blVal && !brVal) ){
                root.val = tlVal;
                root.isLeaf = true;
                return root;
            }
            else {
                root.val = true;
                root.isLeaf = false;
                root.topLeft = tl;
                root.topRight = tr;
                root.bottomLeft = bl;
                root.bottomRight = br;
            }
        }
        else {
            root.isLeaf = false;
            root.val = true;
            root.topLeft = tl;
            root.topRight = tr;
            root.bottomLeft = bl;
            root.bottomRight = br;
        }
        
        
        return root;
        
    }
    
    boolean checkIfAllAreSame(int tl, int tr, int bl,int br){
        //System.out.println(tl+"-"+tr+"-"+bl+"-"+br+"::"+(tl & tr & bl & br) +"::"+ (~tl & ~tr & ~bl & ~br));
        int sum =  (tl + tr + bl + br );
        return sum == 4 || sum == 0;
        
    }
}