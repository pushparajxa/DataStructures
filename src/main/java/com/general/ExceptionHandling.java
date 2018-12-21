package com.general;

public class ExceptionHandling {
    public static void main(String[] args) {
        try{
            int[] arr = {2,3};
            System.out.println(arr[0]);
            throw new ChildException("Child exception");
        }catch (ParentException e) {
            System.out.println("parent Exception");
        }catch (Exception e){
            System.out.println("Main Exception");
        }
    }

    public static class ParentException extends Exception{
        public ParentException(String msg){
            super(msg);
        }
    }

    public static class ChildException extends ParentException{


        public ChildException(String msg) {
            super(msg);
        }
    }
}
