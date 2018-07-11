
package com.lang.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptioanlTest {
  public static void main(String[] args) {
 List<Optional<String>> list = Collections.singletonList(Optional.of("hello"));
    List<Optional<Optional<Integer>>> collect = list.stream().map(x -> x.map
        (OptioanlTest::getLength2))
        .collect(Collectors.toList());
    List<Optional<Integer>> collect2 = list.stream().map(x -> x.flatMap(OptioanlTest::getLength2))
        .collect(Collectors.toList());
    String str = "tt";
    String substring = str.substring(1, str.length() - 1);
    System.out.println(substring);
  }

  static int getLength(String leng){
    return leng.length();
  }

  static Optional<Integer> getLength2(String leng){
    return Optional.of(leng.length());
  }

  static int getList(List<String> list){
    return 1;
  }
}
