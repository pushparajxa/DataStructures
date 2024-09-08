/*
 ** COPYRIGHT **
*/
package com.ds.graph.bipartite;

/*
    Modelling weighted matching as Linear programming is explained very well here
        https://www.youtube.com/watch?v=02Y0ONTLwpg
       
   Explanation of the algorithm
    https://www.youtube.com/watch?v=FCaD34z--bY&t=249s


 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Hashtable;

public class HungarianAlgorithm implements Serializable {
    
    
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.keySet();
        System.out.println("hello");
    }
    
    public void readObject(ObjectInputStream objectInputStream)
        throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        objectInputStream.readShort();
    }
}
