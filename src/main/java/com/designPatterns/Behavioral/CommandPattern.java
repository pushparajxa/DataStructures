/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.designPatterns.Behavioral;

import java.util.HashMap;
import java.util.Map;

//https://en.wikipedia.org/wiki/Command_pattern
public class CommandPattern {

  public static void main(String[] args) {

    Invoker invoker = new Invoker();
    Receiver receiver1 = new Receiver1();
    Receiver receiver2 = new Receiver2();
    Command command1 = new Command1(receiver1);
    Command command2 = new Command2(receiver2);

    invoker.registerCommand(command1, "command1");
    invoker.registerCommand(command2, "command2");

    invoker.executeCommand("command1");


  }

  private static class Invoker {
    private Map<String, Command> commands = new HashMap<>();

    void registerCommand(Command command, String name) {
      commands.put(name, command);

    }

    void executeCommand(String commandName) {
      commands.get(commandName).execute();
    }

  }


  private static class Command1 implements Command {

    private Receiver receiver;

    Command1(Receiver receiver) {
      this.receiver = receiver;
    }

    @Override
    public void execute() {
      receiver.doAction();
    }
  }

  private static class Command2 implements Command {

    private Receiver receiver;

    Command2(Receiver receiver) {
      this.receiver = receiver;
    }

    @Override
    public void execute() {
      receiver.doAction();
    }
  }

  private interface Command {

    void execute();
  }

  private interface Receiver {

    void doAction();
  }

  private static class Receiver1 implements  Receiver{

    public void doAction() {  
      System.out.println("Doing some action in the receiver1");
    }

  }

  private static class Receiver2 implements  Receiver{

    public void doAction() {
      System.out.println("Doing some action in the receiver2");
    }

  }
}