
package test;

public interface Client {

  String identifier();

  void notify(Diff diff);
}