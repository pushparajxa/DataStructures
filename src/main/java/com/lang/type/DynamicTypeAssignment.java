/*
 ** COPYRIGHT **
 */
package com.lang.type;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

public class DynamicTypeAssignment {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
         Type t = String.class.arrayType();
         
         Object s = "hello";
        
     //   System.out.println(s.getClass().getTypeName());
        
       // System.out.println(t);
        
      //  System.out.println(Object.class);
/*        Field filed = new Field("Hello");
        
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(
            new FileOutputStream(
            "/Users/pushparaj/Desktop/code/DataStructures/src/main/resources/Java/out")));
        
        objectOutputStream.writeObject(filed);
        
        objectOutputStream.close();
        
        ObjectInputStream objectInputStream =
            new ObjectInputStream(new BufferedInputStream(new FileInputStream(("/Users/pushparaj"
                + "/Desktop/code/DataStructures/src/main/resources/Java/out"))));
        
        
        Field res = (Field) objectInputStream.readObject();
        System.out.println(res.value);*/
        
        Field2 field2 = new Field2("Hellow World");
        
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(
            new FileOutputStream(
                "/Users/pushparaj/Desktop/code/DataStructures/src/main/resources/Java/out2")));
        
        objectOutputStream.writeObject(field2);
        
        objectOutputStream.close();
        
        ObjectInputStream objectInputStream =
            new ObjectInputStream(new BufferedInputStream(new FileInputStream(("/Users/pushparaj"
                + "/Desktop/code/DataStructures/src/main/resources/Java/out2"))));
        
        
        Field2 res = (Field2) objectInputStream.readObject();
        System.out.println(res.val);
    }
    
    static class Field implements Externalizable {
        Object value;
        
        public Field(){
        
        }
        
        public Field(Object value) {
            this.value = value;
        }
        
        
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            
            out.writeObject(value);
        }
        
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
               this.value  =  in.readObject();
        }
    }
    
    static class Field2 implements Serializable{
        
        Object val;
        
        Field2(String val){
            this.val = val;
        }
        
       
        public void writeObject(ObjectOutputStream outputStream) throws IOException {
           // outputStream.defaultWriteObject();
            outputStream.writeObject(val);
        }
        
        
        public void readObject(ObjectInputStream inputStream)
            throws IOException, ClassNotFoundException {
            this.val = inputStream.readObject();
            
            
        }
        
        
    }
    
    
    
}
