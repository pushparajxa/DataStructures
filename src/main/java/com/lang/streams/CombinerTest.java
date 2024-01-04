
package com.lang.streams;

import java.util.Arrays;
import java.util.List;

//http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
// https://www.baeldung.com/java-stream-reduce
//ForkJoinPool is used for parallelStream

public class CombinerTest {
  public static void main(String[] args) {
    List<Person> persons = Arrays.asList(
        new Person("Max", 18),
        new Person("Peter", 23),
        new Person("Pamela", 23),
        new Person("David", 12));

    persons
        .stream().parallel()
        .reduce(0,
            (sum, p) -> {
              System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                  sum, p, Thread.currentThread().getName());
              return sum += p.age;
            },
            (sum1, sum2) -> {
              System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                  sum1, sum2, Thread.currentThread().getName());
              return sum1 + sum2;
            });
  }
   static class Person{
    int age;
    String name;
    Person( String name, int age){
      this.age =age;
      this.name = name;
    }

     @Override
     public String toString() {
       return name;
     }

   }
   
   static int combinerTest2(List<Person> persons) {
   
/*       return  persons
           .stream()
           .reduce(0,
               (sum, p) -> sum + p.age);
        Gives compilation error as the accumulator is this invocation of reduce function is
        expected to be of signature BiFunction<T, T, T>
               
                T reduce(T identity, BinaryOperator<T> accumulator);
                
                public interface BinaryOperator<T> extends BiFunction<T,T,T>
                
                where T in this case is Person. This is not the case when we use combiner as
                below, the accumulator definition changes to
                
                <U> U reduce(U identity,
                             BiFunction<U, ? super T, U> accumulator,
                             BinaryOperator<U> combiner);
          */

      
      return  persons
           .stream()
           .reduce(0,
               (sum, p) -> sum + p.age,
               Integer::sum);
   }
   
}
