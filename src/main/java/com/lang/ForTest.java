
package com.lang;

public class ForTest {
    public static void main(String[] args) {
        boolean a = true, b= true, c=true;
        for(int i=0;i<5;i++){
            System.out.println("Executing i="+i);
            if(a){
                if(b){
                    if(c){
                        continue;
                    }

                }
            }
        }

        int i,j=10;
        for( i=0,j=10;j<0;i++){
            System.out.println(i);
        }
        System.out.println(i);
    }
}
