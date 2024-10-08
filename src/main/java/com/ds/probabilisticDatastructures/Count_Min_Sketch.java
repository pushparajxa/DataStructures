/*
 ** COPYRIGHT **
 */
package com.ds.probabilisticDatastructures;



/*
   Given a stream of integer/keys, answer following queries
    1. insert(key)
    2. frequency(key)

in constant amount of space and O(1) time.


 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

// Refer: https://vivekbansal.substack.com/p/count-min-sketch
public class Count_Min_Sketch {
    
    int num_hash_functions = 3;
    int num_width = 5;
    int [][] count = new int[num_hash_functions][num_width];
    
    public  void insert(String key) throws NoSuchAlgorithmException {
        count[0][Math.abs(generateSha1(key))%num_width] ++;
        count[1][Math.abs(generateSha256(key))%num_width] ++;
        count[2][Math.abs(generateMD5(key))%num_width] ++;
        
    }
    
    public int query(String key) throws NoSuchAlgorithmException {
        return Math.min(
                Math.min(count[0][Math.abs(generateSha1(key))%num_width],
                         count[1][Math.abs(generateSha256(key))%num_width]),
                count[2][Math.abs(generateMD5(key))%num_width]
        );
    }
    
    
    private int generateSha1(String key) throws NoSuchAlgorithmException {
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = sha1Digest.digest(key.getBytes());
        BigInteger bigInteger   = new BigInteger(hash);
        return bigInteger.intValue();
    }
    
    private int generateMD5(String key) throws NoSuchAlgorithmException {
        MessageDigest sha1Digest = MessageDigest.getInstance("MD5");
        byte[] hash = sha1Digest.digest(key.getBytes());
        BigInteger bigInteger   = new BigInteger(hash);
        return bigInteger.intValue();
    }
    
    
    private int generateSha256(String key) throws NoSuchAlgorithmException {
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = sha1Digest.digest(key.getBytes());
        BigInteger bigInteger   = new BigInteger(hash);
        return bigInteger.intValue();
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Count_Min_Sketch countMinSketch = new Count_Min_Sketch();
        
        countMinSketch.insert("Apple");
        countMinSketch.insert("Apple");
        countMinSketch.insert("Banana");
        countMinSketch.insert("Orange");
        countMinSketch.insert("Mango");
        
        System.out.println(Arrays.deepToString(countMinSketch.count));
        
        System.out.println(countMinSketch.query("Apple"));
        System.out.println(countMinSketch.query("Banana"));
        System.out.println(countMinSketch.query("Orange"));
        
        // We may get frequency higher than the actual due to hash collisions.
        // To increase the accuracy we need to increase the number of hash functions and width.
    }
    
    
}
