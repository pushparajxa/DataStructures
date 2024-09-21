/*
 ** COPYRIGHT **
 */
package com.ds.matrix;

class ValidateSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean grid[] = new boolean [9];
        
        for(int r=0;r<9;r++){
            grid = new boolean [9];
            for(int c=0;c<9;c++){
                if(board[r][c] == '.')
                    continue;
                
                if(grid[(int)board[r][c] - 49])
                    return false;
                else
                    grid[(int)board[r][c] - 49] = true;
            }
        }
        
        for(int c=0;c<9;c++){
            grid = new boolean[9];
            for(int r=0;r<9;r++){
                if(board[r][c] == '.')
                    continue;
                
                if(grid[(int)board[r][c] - 49])
                    return false;
                else
                    grid[(int)board[r][c] - 49] = true;
            }
        }
        
        
        for(int r=0;r<=6;r=r+3){
            for(int c=0;c<=6;c=c+3){
                if(!validateGrid(r,c, board))
                    return false;
            }
        }
        
        return true;
    }
    
    private boolean validateGrid(int r, int c, char[][]board){
        // System.out.println("row="+r+", col="+c);
        boolean grid[] = new boolean [9];
        for(int i=r;i<r+3;i++){
            for(int j=c;j<c+3;j++){
                //System.out.println("i="+i+",j="+j);
                if(board[i][j]=='.')
                    continue;
                
                if(grid[(int)board[i][j] - 49])
                    return false;
                else
                    grid[(int)board[i][j] - 49] = true;
            }
        }
        return true;
    }
    
    
}