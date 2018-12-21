
package com.lang.Serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestMethodChange implements Serializable {

  private int data;

  public TestMethodChange(int data){
    this.data= data;
  }

  public static void main(String[] args) {

 /*   try {
      TestMethodChange methodChange = new TestMethodChange(20);
      FileOutputStream outputStream = new FileOutputStream("/tmp/file_serialize");
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
      objectOutputStream.writeObject(methodChange);
      objectOutputStream.flush();
    } catch (IOException e) {
      System.out.println("Exception while writing");
    }*/

    try {
      FileInputStream fileInputStream = new FileInputStream("/tmp/file_serialize");
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
      TestMethodChange testMethodChange = (TestMethodChange) objectInputStream.readObject();
      System.out.println(testMethodChange.data);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public String toString(){
    return String.valueOf(data);
  }


}
