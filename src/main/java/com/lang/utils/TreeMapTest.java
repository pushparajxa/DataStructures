/*
 ** COPYRIGHT **
 */
package com.lang.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    
    
    public static void main(String[] args) {
        Map<Order, Integer> orders = new TreeMap<>();
        Order o2 = new Order();
        o2.name = "Colgate";
        o2.price = 45;
        orders.put(o2, 1);
        
        o2 = new Order();
        o2.name = "Colgate";
        o2.price = 23;
        orders.put(o2, 1);
        
        o2 = new Order();
        o2.name = "Pepsodent";
        o2.price = 54;
        orders.put(o2, 1);
        
        System.out.println(orders);
        
        HashMap<String, List<Integer>> collect =
      orders.keySet()
            .stream()
            .collect(
                HashMap<String, List<Integer>>::new,
                (map, order) ->
                    map.compute(
                        order.name,
                        (key, count) -> {
                            if (count == null)
                                count = new ArrayList<>();
                            count.add(orders.get(order));
                            return count;
                        }
                    
                    ),
                (x, y) -> x.putAll(y)
            );
        
        System.out.println(collect);
        
    }
    
    
    
    // Without
    static class Order implements Comparable<Order> {
        String name;
        int price;
        
        @Override
        public int compareTo(Order o) {
            if(this.hashCode() == o.hashCode())
                return 0;
            else if (this.hashCode() > o.hashCode()) {
                return 1;
                
            }
            else {
                return -1;
            }
        }
        
        @Override
        public String toString(){
            return this.name+ "-"+ this.price;
        }
        
        @Override
        public int hashCode(){
            return Integer.valueOf(this.price).hashCode() * 31 + name.hashCode();
        }
    }
}
