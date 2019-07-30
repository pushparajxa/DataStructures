
package test;

public class ConcreteDiff implements  Diff {

  private String identifer;

  private Object oldContents;

  private Object newContents;

  public ConcreteDiff(String identifer, Object oldContents, Object newContents) {
    this.identifer = identifer;
    this.oldContents = oldContents;
    this.newContents = newContents;
  }

  @Override
  public String identifier() {
    return identifer;
  }

  @Override
  public Object oldContents() {
    return oldContents;
  }

  @Override
  public Object newContents() {
    return newContents;
  }
}
