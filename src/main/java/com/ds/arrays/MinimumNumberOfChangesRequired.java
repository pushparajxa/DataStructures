
package com.ds.arrays;

import static com.ds.arrays.MinimumNumberOfChangesRequired.DIRECTION.DOWN;
import static com.ds.arrays.MinimumNumberOfChangesRequired.DIRECTION.LEFT;
import static com.ds.arrays.MinimumNumberOfChangesRequired.DIRECTION.RIGHT;
import static com.ds.arrays.MinimumNumberOfChangesRequired.DIRECTION.UP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumNumberOfChangesRequired {


  public static void main(String[] args) {

    //Given input matrix where each cell has a direction up,down, left or right. Given a
    // destination and sourece (assuming there is no path between them, what is the minimum
    // number of changes(change the direction in the cell) required so as to get a path between
    // the destination and the source.

    int [][] input = {{3,3,3},
        {0,1,2}
    };

    MinimumNumberOfChangesRequired minimumNumberOfChangesRequired =
        new MinimumNumberOfChangesRequired();
    int result = minimumNumberOfChangesRequired.getMinimumNumberOfChangesRequired(input,0,0,1,2);
    System.out.println(result);

  }


  int getMinimumNumberOfChangesRequired(int [][]  input, int srcX, int srcY, int destX, int destY){

    Pair destPair = new Pair(destX,destY);
    List<Pair> neghrs = getNghbrs(destPair,input);
    HashMap<Pair,HashMap<DIRECTION,Integer>> changesMap = new HashMap<>();
    Set<Pair> predecessors = new HashSet<>();
    predecessors.add(destPair);

    int min=Integer.MAX_VALUE;

    for(Pair pair: neghrs){
      DIRECTION neghbrShouldBeDirection = getWhereNghbrDirectionShouldBeForDest(destPair, pair);
      DIRECTION  neghbrDirection = DIRECTION.getDirection(input[pair.x][pair.y]);
      int count=0;
      if(neghbrDirection!=neghbrShouldBeDirection){
        count=1;
      }

      min = Math.min(min,
          getChanges(pair, input,changesMap, predecessors,  srcX,  srcY,neghbrShouldBeDirection)+ count);

    }
    return min;
  }


  int getChanges(Pair pair, int [][] input, HashMap<Pair,HashMap<DIRECTION,Integer>> changesMap,
      Set<Pair> predescrs
      , int srcX, int srcY, DIRECTION direction){

    if(pair.x == srcX && pair.y== srcY){
      return 0;
    }
    List<Pair> neghrs = getNghbrs(pair,input);
    Set<Pair> predecessors = new HashSet<>(predescrs);
    predecessors.add(pair);
    int min = Integer.MAX_VALUE;

    for(Pair pair1: neghrs){
      if(!predecessors.contains(pair1)){
        DIRECTION  neghbrDirection = DIRECTION.getDirection(input[pair1.x][pair1.y]);
        int count=0;
        DIRECTION nghbrDirectionShouldBe = getWhereNghbrDirectionShouldBe(pair,pair1,direction);
        if(nghbrDirectionShouldBe!=neghbrDirection){
          count=1;
        }
       if(changesMap.containsKey(pair1) && changesMap.get(pair1).containsKey(direction)){
         min = Math.min(min, changesMap.get(pair1).get(direction)+count);
       }else{
         min = Math.min(min,
             getChanges(pair1,input,changesMap,predecessors,srcX,srcY,nghbrDirectionShouldBe)+count);
       }
      }
    }

    if(changesMap.containsKey(pair)){
      changesMap.get(pair).put(direction,min);
    }else{
      HashMap<DIRECTION, Integer > map = new HashMap<>();
      map.put(direction,min);
      changesMap.put(pair,map);
    }

    return min;
  }


  List<Pair> getNghbrs(Pair pair, int [][] input){
    int x= pair.x, y= pair.y;
    List<Pair> result =  new ArrayList<>();
    if(x-1>=0){
      result.add(new Pair(x-1,y));
    }
    if(x+1<=input[0].length-1){
      result.add(new Pair(x+1,y));
    }
    if(y-1>=0){
      result.add(new Pair(x,y-1));
    }
    if(y+1<=input.length-1){
      result.add(new Pair(x,y+1));
    }

    return result;
  }


  DIRECTION getWhereNghbrDirectionShouldBeForDest(Pair pair1,Pair pair2){

    DIRECTION neghbrPosition = getNeighbourPosition(pair1,pair2);
    if(neghbrPosition== LEFT){
      return RIGHT;
    }else if(neghbrPosition == RIGHT){
      return LEFT;
    }else if(neghbrPosition == UP){
      return DOWN;
    }else if(neghbrPosition == DOWN){
      return UP;
    }
    return null;
  }

  DIRECTION getWhereNghbrDirectionShouldBe(Pair pair1,Pair pair2, DIRECTION direction){


      DIRECTION neghbrPosition = getNeighbourPosition(pair1,pair2);

      if(neghbrPosition== LEFT){
        if(direction== UP || direction== DOWN){
          return RIGHT;
        }else{
          return direction;
        }
      }else if(neghbrPosition== RIGHT){
        if(direction== UP || direction== DOWN){
         return  LEFT;
        }else{
          return direction;
        }
      } else if (neghbrPosition == UP) {
        if (direction == LEFT || direction == RIGHT) {
          return DOWN;
        } else{
          return direction;
        }
      }else if(neghbrPosition == DOWN){
        if (direction == LEFT || direction == RIGHT) {
          return UP;
        } else {
          return direction;
        }
      }

      return null;
  }

  DIRECTION getNeighbourPosition(Pair dest, Pair neghbr){
    if(dest.y==neghbr.y){
      if(dest.x>neghbr.x){
        return LEFT;
      }else{
        return RIGHT;
      }
    }else if(dest.x==neghbr.x){
      if(dest.y>neghbr.y){
        return DOWN;
      }else{
        return UP;
      }
    }

    return null;
  }

  static  class Pair{
    private int x,y;

    Pair(int x, int y){
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object otherPair){
      if(otherPair==null || !(otherPair instanceof Pair)){
        return false;
      }
      Pair other = (Pair)otherPair;

      return this.x==other.x && this.y == other.y;

    }

    @Override
    public int hashCode(){
      return 10*x+y;
    }

  }

  enum DIRECTION{
    LEFT(0), RIGHT(1), UP(2), DOWN(3), EMPTY(4);

    int value;
    DIRECTION(int value){
    this.value = value;
    }

    static DIRECTION getDirection(int value){
      switch (value){
        case 0:
          return LEFT;
        case 1:
          return RIGHT;
        case 2:
          return  UP;
        case 3:
          return DOWN;
      }
      return EMPTY;
    }

  };

}
