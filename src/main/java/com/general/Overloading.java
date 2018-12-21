package com.general;

public class Overloading {
    public static void main(String[] args) throws CustomExeption{
        Overloading overloading = new Overloading();
        char in = 65535;
        overloading.methd(in);
    }

    public void methd(){

    }

    public void methd(int args){
        System.out.println("int is called");
    }

    public void methd(short asdas){
        System.out.println("short is called");
    }

    public static class CustomExeption extends Exception{

    }

}
