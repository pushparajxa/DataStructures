
package com.lang;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class AckoTest {

  public static void main(String[] args) {
    List<Integer> integers = new ArrayList<>();

    integers.add(5);
    integers.add(1);
    integers.add(3);
    integers.add(4);
    integers.add(6);
    integers.add(2);

    finalPrice(integers);

  }

  public static void finalPrice(List<Integer> prices) {

    Hashtable<Integer,Integer> hashtable = new Hashtable<Integer, Integer>();
    List<Integer> orders = new ArrayList<Integer>();
    List<Integer> unaltered = new ArrayList<Integer>();
    int small=prices.get(prices.size()-1);
    int smallIndex=prices.size()-1;
    orders.add(small);
    unaltered.add(prices.size()-1);
    hashtable.put(small,smallIndex);

    for(int i=prices.size()-2;i>=0;i--){
      int val = prices.get(i);
      if(val<small){
        small=val;
        smallIndex = i;
        orders.add(val);
        unaltered.add(i);
        hashtable.put(val,i);
      }
      else if(val==small){
        smallIndex=i;
        orders.add(0);
        hashtable.put(val,i);
      }else{
        if(hashtable.containsKey(val)){
          int hashIndex = hashtable.get(val);
          if(hashIndex<smallIndex){
            orders.add(0);
            hashtable.put(val,i);
          }else{
            hashtable.put(val,i);
            orders.add(val-small);
          }
        }else{
          hashtable.put(val,i);
          orders.add(val-small);
        }
      }

    }

    int sum = orders.stream().reduce(0, (a, b) -> a + b);
    System.out.println(sum);
    for(int i=unaltered.size()-1; i>=0;i--){
      System.out.print(unaltered.get(i));
    }
  }
}
