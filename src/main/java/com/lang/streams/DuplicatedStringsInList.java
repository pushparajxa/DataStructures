
package com.lang.streams;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicatedStringsInList {
    
    public static void main(String[] args) {
        List<String> vals = List.of("First",
            "Second",
            "First",
            "Second",
            "First",
            "Three");
    
        Map<String, Long> stringLongMap = vals.stream()
            .collect(groupingBy(Function.identity(), counting()));
        List<Entry<String, Long>> entryList = stringLongMap.entrySet().stream()
            .filter(x -> x.getValue() > 1).collect(Collectors.toList());
    
        List<Entry<String, Long>> collect = vals.stream()
            .collect(groupingBy(Function.identity(),
                     summingLong(x->1)))
            .entrySet()
            .stream()
            .filter(x -> x.getValue() > 1).collect(Collectors.toList());
    
        System.out.println(entryList.toString());
    
        System.out.println(collect);
    
        System.out.println(vals.stream().collect(Collectors.counting()));
        
    }
    
}
