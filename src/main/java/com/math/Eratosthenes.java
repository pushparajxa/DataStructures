package com.math;

/*
  Given a number n, print all primes smaller than or equal to n.
  http://www.geeksforgeeks.org/sieve-of-eratosthenes/
*/
public class Eratosthenes {
    public static void main(String[] args) {
        int n = 20;
        boolean isPrime[] = new boolean[n];
        init(isPrime);
        for(int i = 2;i*i<=n;i++) {
            if(isPrime[i-1]){
                for(int j=i*i;j<=n;j=j+i){
                    isPrime[j-1]=false;
                }
            }
        }
        for(int i = 0;i<isPrime.length;i++) {
            System.out.println(i+1+"="+isPrime[i]);
        }
    }

    private static void init(boolean[] isPrime) {
        for(int i = 0;i<isPrime.length;i++) {
            isPrime[i] = true;
        }
        isPrime[0]=false;
    }


}
