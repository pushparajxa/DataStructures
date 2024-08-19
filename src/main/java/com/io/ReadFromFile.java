package com.io;

import java.io.*;

public class ReadFromFile {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("/tmp/out")));
            try {
                String read;
                while((read = reader.readLine())!=null){
                    System.out.println("Read string "+read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //Yhis is test
        }
    }

    static class writeToFile{
        public static void main(String[] args) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/tmp/out")));
                char[] chars = new char[]{'2','3','4'};
                writer.write(chars,0,chars.length);
                writer.write("helloWorld",0,"helloWorld".length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ObjectReader{
        public static void main(String[] args) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("/tmp/in1")));
                objectInputStream.readLong();
                try {
                    objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ObjectWriter{
        public static void main(String[] args) {
            try {
              ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("/tmp/out1")));
              objectOutputStream.write(2);
              objectOutputStream.writeObject("This is a string");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
