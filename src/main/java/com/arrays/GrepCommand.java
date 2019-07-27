
package com.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GrepCommand {

  public static void main(String[] args) {

    GrepCommand microsoft = new GrepCommand();

    List<String> list = new ArrayList<>();
    // list.add("apple");
    list.add("cat");
    list.add("cat");
    list.add("TERM")
   /* list.add("cat");
    list.add("ball");
    list.add("egg");
    list.add("rat")*/;

    for(String word : list){

      microsoft.process(word);
    }

  }


  int c = 2;
  int len = 2*c+1;
  LinkedList<Wrapper> list = new LinkedList<Wrapper>();

  String searchWord = "cat";

// egg cat cat ret cat

  void process(String word){
    if(word.equals("TERM")){
      if(list.size()<len){
        terminateWhenListIsNotFull();
      }else{
        extraProcess(true);
      }
    }else{
      if(list.size()<len){
        list.addFirst(new Wrapper(word,false));
      }else if(list.size()==len){
        extraProcess(false);
        list.removeLast();
        list.addFirst(new Wrapper(word,false));
        extraProcess(false);

      }
    }

  }

  private void terminateWhenListIsNotFull(){
    for(int i=list.size()-1;i>=0;i--){
      if(list.get(i).word.equals(searchWord) && !list.get(i).isProcessed()){
        list.get(i).setProcessed(true);
        if(i+2<=list.size()-1){
          System.out.println("--------------------------");
          for (int j = i+2;  j>=i+1; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println(searchWord);
          for (int j = i-1; j >=0 && j>=i-2 ; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println("--------------------------");
        }else{

          System.out.println("--------------------------");
          for (int j = list.size()-1;  j>=i+1; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println(searchWord);
          for (int j = i-1; j >=0 && j>=i-2 ; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println("--------------------------");

        }

      }


    }


  }

  private void extraProcess(boolean terminate) {
    for (int i = len-1; i >=0; i--) {
      if(list.get(i).word.equals(searchWord)){
        if( i == c && !list.get(i).isProcessed()){
          list.get(i).setProcessed(true);
          System.out.println("--------------------------");
          for (int j = len-1;  j>i; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println(searchWord);
          for (int j = i-1; j >=0 ; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println("--------------------------");
        }else if(i>c && !list.get(i).isProcessed() ){
          //
          list.get(i).setProcessed(true);
          System.out.println("--------------------------");
          for (int j = i+1;  j< len; j++) {
            System.out.println(list.get(j).word);
          }
          System.out.println(searchWord);
          for (int j = i-1; j >=0 && j>=i-c ; j--) {
            System.out.println(list.get(j).word);
          }
          System.out.println("--------------------------");
        }else{
          if(terminate){
            list.get(i).setProcessed(true);
            System.out.println("--------------------------");
            for (int j = i+1;  j<= i+c; j++) {
              System.out.println(list.get(j).word);
            }
            System.out.println(searchWord);
            for (int j = i-1; j >=0 && j>=i-c ; j--) {
              System.out.println(list.get(j).word);
            }
            System.out.println("--------------------------");

          }else{
            //wait for more tokens.
          }

        }

      }

    }
  }

  static class Wrapper{
    private String word;
    private boolean processed;

    Wrapper(String word, boolean isProcessed){
      this.word = word;
      this.processed = isProcessed;
    }

    public void setProcessed(boolean val){
      processed = val;

    }

    public boolean isProcessed(){
      return processed;
    }

  }

}

