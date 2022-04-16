
package com.lang;

public class ContinueInNestedIfBlocksTest
{
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if(true) {
                if(true) {
                    if(true){
                        if(i%2==0){
                            continue;
                        }
                        else {
                            System.out.println("i="+i);
                        }
                    }
                }
            }
            
        }
    }
}
