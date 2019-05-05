
package com.designPatterns.Behavioral;

import java.util.ArrayList;
import java.util.List;

/*

https://en.wikipedia.org/wiki/Visitor_pattern
look for double dispatch

https://www.javaworld.com/article/2077602/learn-java/java-tip-98--reflect-on-the-visitor-design-pattern.html
 */

public class VisitorPattern {

  public static void main(String[] args) {

    Visitor helloSayingVisitor = new HelloSayingVisitor();
    Visitor bySayingVisitor = new ByeSayingVisitor();

    List<Visitable> visitables = new ArrayList<>();
    Library library = new Library();
    Rack rack = new Rack();
    Book book = new Book();
    visitables.add(library);
    visitables.add(rack);
    visitables.add(book);

    for(Visitable visitable: visitables){
      visitable.accept(helloSayingVisitor);
    }


    for(Visitable visitable: visitables){
      visitable.accept(bySayingVisitor); //First dispatch to respective Visitable implementation
    }
  }

  private interface Visitor{
    void visit(Object object);//Second dispatch to respective Visitor implementation
  }

  private interface Visitable{
    void accept(Visitor visitor);
  }

  private static class Library implements Visitable{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

  }

  private static class Rack implements  Visitable{

    @Override
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }


  private  static class Book implements  Visitable{

    @Override
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }

  private static class HelloSayingVisitor implements  Visitor{

    @Override
    public void visit(Object object) {
          if(object instanceof  Rack){
            System.out.println("Hello Rack");
          }else if(object instanceof  Library){
            System.out.println("Hello Library");
          }else if(object instanceof  Book){
            System.out.println("Hello Book");
          }
    }
  }

  private static class ByeSayingVisitor implements  Visitor{

    @Override
    public void visit(Object object) {
      if(object instanceof  Rack){
        System.out.println("Bye Rack");
      }else if(object instanceof  Library){
        System.out.println("Bye Library");
      }else if(object instanceof  Book){
        System.out.println("Bye Book");
      }
    }
  }

}

