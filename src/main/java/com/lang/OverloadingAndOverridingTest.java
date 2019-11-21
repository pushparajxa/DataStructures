package com.lang;

public class OverloadingAndOverridingTest
{
    //https://software.rajivprab.com/2019/08/14/nuances-of-overloading-and-overriding-in-java/

    /**
     *
     * Hidden Override
     *
     * Given the following:
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     *
     * class Parent {
     *   void print(Object a) { log.info("Parent - Object"); }
     * }
     *
     * class Child extends Parent {
     *   void print(String a) { log.info("Child - String"); }
     * }
     *
     * What gets printed?
     * 1
     * 2
     * 3
     *
     * String string = "";
     * Parent parent = new Child();
     * parent.print(string);
     *
     * Answer:
     * 1
     *
     * parent.print(string);  // Prints: "Parent - Object"
     *
     * The actual instance type is Child, and the declared argument type is String,
     * and we do indeed have a method defined for Child::print(String).
     * In fact, that’s exactly what got picked in the previous example when calling parent.print(string).
     * However, that’s not the method that gets invoked here.
     *
     * It appears that Java first picks which method to invoke,
     * before checking for sub-class overrides. In this case, the declared instance type is Parent
     * and the only matching method in Parent is Parent::print(Object).
     * When Java then checks for any potential overrides of Parent::print(Object),
     * it does not find any, so that’s the method which gets executed.
     * Exposed Override
     *
     * Given the following:
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     *
     * class Parent {
     *   void print(Object a) { log.info("Parent - Object!"); }
     *   void print(String a) { throw new RuntimeException(); }
     * }
     *
     * class Child extends Parent {
     *   void print(String a) { log.info("Child - String!"); }
     * }
     *
     * What gets printed?
     * 1
     * 2
     * 3
     *
     * String string = "";
     * Parent parent = new Child();
     * parent.print(string);
     *
     * Answer:
     * 1
     *
     * parent.print(string);  // Prints: "Child - String!"
     *
     * Ambiguous Parameter
     *
     * Given the following class:
     * 1
     * 2
     * 3
     * 4
     *
     * class Foo {
     *   void print(Cloneable a) { log.info("I am cloneable!"); }
     *   void print(Map a) { log.info("I am Map!"); }
     * }
     *
     * What gets printed below?
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     *
     * HashMap cloneableMap = new HashMap();
     * Cloneable cloneable = cloneableMap;
     * Map map = cloneableMap;
     *
     * // What gets printed?
     * Foo foo = new Foo();
     * foo.print(map);
     * foo.print(cloneable);
     * foo.print(cloneableMap);
     *
     * Answers:
     * 1
     * 2
     * 3
     *
     * foo.print(map);           // Prints: "I am Map!"
     * foo.print(cloneable);     // Prints: "I am cloneable!"
     * foo.print(cloneableMap);  // Does not compile
     *
     * Similar to the single_dispatch example, what matters here is the declared type of the parameter,
     * not the actual type. In addition, if there are multiple methods that are equally valid for a given parameter,
     * Java throws a compile error and forces you to specify which one should be called.
     *
     *
     * Private Override
     *
     * Given the following:
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     *
     * class Parent {
     *   void print() { foo(); }
     *   private void foo() { log.info("I am Parent!"); }
     * }
     *
     * class Child extends Parent {
     *   void foo() { log.info("I am Child!"); }
     * }
     *
     * What gets printed?
     * 1
     *
     * new Child().print();
     *
     * Answer:
     * 1
     *
     * new Child().print();  // Prints: "I am Parent!"
     *
     * The setup is identical to the previous one, except for one difference. Parent.foo() is now declared to be private.
     * Because of this, when Parent.print() invokes foo(), this is hard-coded to be Parent.foo().
     * Regardless of any other implementations of foo() that may exist in the child class,
     * and regardless of the actual type of the instance that is invoking print().
     *
     * It is often assumed that changing a method from public to private, is a purely refactoring change,
     * as long as compilation still succeeds. The above example shows that this is false – even if compilation succeeds,
     * system behavior can change in dramatic ways.
     *
     * Using the @Override annotation on all override methods will help greatly in preventing such regressions,
     * by producing compile errors as soon as any base methods have their visibility changed.
     * Static Overrides
     *
     * Given the following:
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     *
     * class Parent {
     *   static void print() { log.info("I am Parent!"); }
     * }
     *
     * class Child extends Parent {
     *   static void print() { log.info("I am Child!"); }
     * }
     *
     * What gets printed?
     * 1
     * 2
     * 3
     * 4
     * 5
     *
     * Child child = new Child();
     * Parent parent = child;
     *
     * parent.print();
     * child.print();
     *
     * Answers:
     * 1
     * 2
     *
     * parent.print(); // Prints: "I am Parent!"
     * child.print();  // Prints: "I am Child!"
     *
     * Java does not allow for overriding static methods. If you have the same static method defined in both the parent
     * and child classes, the actual type of the instance does not matter at all. Only the declared type is used to
     * determine which of the two methods is invoked.
     *
     * This is the exact opposite of what happens with non-static methods where the declared type is ignored in
     * favor of the actual type. Hence why you need to be careful when changing a method from non-static to static or vice-versa.
     * Even if there are no compile errors, system behavior could change dramatically.
     *
     * This is another reason to mark all override methods with the @Override annotation.
     * In the above case, you will get a compile error when adding the annotation to Child::print,
     * telling you that the method cannot be overridden because it is static.
     *
     * This is also why it is good practice to never invoke static methods using an instance of the class – it can lead to
     * surprising behavior like the above, and fail to alert you when problematic refactoring changes are made.
     * Many IDEs like Intellij will warn you when calling a static-method from a non-static context,
     * and it is best to follow up on such warnings.
     * Static Linking
     *
     * Given the following:
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     * 10
     *
     * class Parent {
     *   void print() { staticMethod(); instanceMethod(); }
     *   static void staticMethod() { log.info("Parent::staticMethod"); }
     *   void instanceMethod() { log.info("Parent::instanceMethod"); }
     * }
     *
     * class Child extends Parent {
     *   static void staticMethod() { log.info("Child::staticMethod"); }
     *   void instanceMethod() { log.info("Child::instanceMethod"); }
     * }
     *
     * What gets printed?
     * 1
     * 2
     *
     * Child child = new Child();
     * child.print();
     *
     * Answer:
     * 1
     * 2
     *
     * Parent::staticMethod
     * Child::instanceMethod
     *
     * This is a combination of some different concepts we covered earlier. For instance methods,
     * the override takes effect, even when the caller is in the parent. However, for static methods,
     * even when the variable’s declared type is Child,
     * Parent::staticMethod is what gets invoked, because of the intermediary print() method.
     *
     *
     */

}
