
package com.trees;

public class B_Tree {

  /*
      // https://en.wikipedia.org/wiki/B-tree#cite_note-3
      A non leaf node can have minimum L children and maximum U children
      It means it can have L-1 elements (minimum) and U-1 maximum elements(keys)
      All leaf nodes should be on same level.
      B-Tree stores keys and values at internal nodes
      B+Tree stores only keys at internal nodes. All values are stored in the leaf nodes and each
      leaf node will have pointer to the next leaf node.

      //Insert Algorithm
      1. Proactive Algorithm
       Initialise X=root
       Find the child of X (Y) where would be going next.
       If Y is full,spilt it and moved the median element  a level up.(To X)
       Now find which of the two spilts we need to look and set that X.
       Repeat it until we find a non-full , leaf node.

https://www.geeksforgeeks.org/b-tree-set-1-insert-2/
 Insertion
1) Initialize x as root.
2) While x is not leaf, do following
..a) Find the child of x that is going to to be traversed next. Let the child be y.
..b) If y is not full, change x to point to y.
..c) If y is full, split it and change x to point to one of the two parts of y. If k is smaller than mid key in y, then set x as first part of y. Else second part of y. When we split y, we move a key from y to its parent x.
3) The loop in step 2 stops when x is leaf. x must have space for 1 extra key as we have been splitting all nodes in advance. So simply insert k to x.

Time for insertion is explained here::

https://cs.stackexchange.com/questions/59453/why-is-b-tree-search-olog-n

 You have introduced 𝑛 n  and 𝑚 m  as the order of B-tree, I will stick to 𝑚 m .  Their height will be in the best case
  ⌈𝑙𝑜𝑔𝑚(𝑁+1)⌉ ⌈ l o g m ( N + 1 ) ⌉ , and the worst case is height ⌈𝑙𝑜𝑔𝑚2(𝑁)⌉ ⌈ l o g m 2 ( N ) ⌉  but there
   is also a saturation factor 𝑑 d , that you have not mentioned. The height will be 𝑂(𝑙𝑜𝑔𝑁) O ( l o g N ) ,
   please notice that 𝑚 m  disappeared, because it effectively is multiplication by a constant. Now at every node you have at most 𝑚 m  sorted elements,
    so you can perform binary search giving 𝑙𝑜𝑔2(𝑚) l o g 2 ( m ) , so the proper complexity is 𝑂(𝑙𝑜𝑔(𝑁)∗𝑙𝑜𝑔(𝑚)) O ( l o g ( N ) ∗ l o g ( m ) ) .
     Since 𝑚<<𝑁 m << N , and what is more important, is that it does not depend on 𝑁 N ,
 so it should not be mixed, or it might be given explicitly (with 𝑚 m  not 𝑁 N  or appearing 𝑛 n ).

  //Delete Algorithm
  https://www.youtube.com/watch?v=JZhdUb5F7oY


   */

  public static void main(String[] args) {

  }

}
