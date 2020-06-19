/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.designPatterns.Behavioral;

// https://en.wikipedia.org/wiki/Template_method_pattern
// Also called Generatiob gap pattern
// The template pattern is useful when working with auto-generated code.
// The challenge of working with generated code is that changes to the source
// code will lead to changes in the generated code; if hand-written
// modifications have
// been made to the generated code, these will be lost. How, then,
// should the generated code be customized?

// The Template pattern provides a solution.
// If the generated code follows the template method pattern,
// the generated code will all be an abstract superclass.
// Provided that hand-written customizations are confined to a subclass,
// the code generator can be run again without risk of over-writing
// these modifications.
// When used with code generation, this pattern is sometimes referred to as
// the generation gap pattern.[7]*/
public class TemplateMethodPattern {

  private static abstract class BaseClass
  {

    // Template method should not be overriden by the subclassed.
    public final void templateMethod()
    {
      //do below as part of a workflow ..which doesn't change.
      doThisFirst();
      doThisSecond();
      doThisThird();
      doThisFourth();
    }

    private void doThisFirst()
    {
      System.out.println("This is the first step in the workflow");
    }
    protected void doThisSecond()
    {
      // This is a hook method
      // Empty body. Subclasses can override this method.
    }

    abstract protected void doThisThird();

    abstract protected void doThisFourth();

  }


  private static class Variant extends BaseClass
  {

    @Override
    protected void doThisThird()
    {
      System.out.println("Doing this in a subclass ");
    }

    @Override
    protected void doThisFourth()
    {
      System.out.println("Doing this in a subclass");
    }
  }


  public static void main(String[] args)
  {

  }

}
