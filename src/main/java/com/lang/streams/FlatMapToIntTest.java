
package com.lang.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

//https://www.logicbig.com/how-to/code-snippets/jcode-java-8-streams-stream-flatmaptoint.html
public class FlatMapToIntTest {

  public static void main(String[] args) {

    List<String> strings = Arrays.asList("First","second");

    IntStream intStream = strings.stream().mapToInt(string -> string.length());
    intStream.forEach(x-> System.out.println(x));

    List<List<String>> matrix = Arrays.asList(Arrays.asList("11","12","13"),
        Arrays.asList("21","22","23"),
        Arrays.asList("31","32","33")
    );

    IntStream intStream1 = matrix.stream().mapToInt(list->list.size());
    intStream1.forEach(x-> System.out.println(x));

    IntStream intStream2 = matrix.stream()
        .flatMapToInt(list -> list.stream().mapToInt(string -> string.length()));

    intStream2.forEach(x-> System.out.println(x));


  }

}
