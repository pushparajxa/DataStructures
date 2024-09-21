/*
 ** COPYRIGHT **
 */
package com.lang.hashcode;


/*
    Look for hashCode implementation of String class.
    
    // Using 31 as a multiplier, which is common practice
    int result = Integer.hashCode(n);  // hash code for the number
    result = 31 * result + Integer.hashCode(c);  // hash code for the suit
    return result;
 */
public class HashCodeWithTwoIntegers {
    static  class Card {
        int n;  // Value of the card (e.g., 1 for Ace, 2-10 for numbered cards, 11 for Jack, etc.)
        int c;  // Suit of the card (e.g., 1 for Hearts, 2 for Diamonds, etc.)
        
        // Constructor
        public Card(int n, int c) {
            this.n = n;
            this.c = c;
        }
        
        // Getter for 'n'
        public int getNumber() {
            return n;
        }
        
        // Getter for 'c'
        public int getSuit() {
            return c;
        }
        
        // Setter for 'n'
        public void setNumber(int n) {
            this.n = n;
        }
        
        // Setter for 'c'
        public void setSuit(int c) {
            this.c = c;
        }
        
        // equals method: Compares two Card objects for equality
        @Override
        public boolean equals(Object o) {
            // Check if it's the same object
            if (this == o) return true;
            // Check if the object is an instance of Card
            if (o == null || getClass() != o.getClass()) return false;
            
            Card card = (Card) o;
            // Compare the fields n and c
            return n == card.n && c == card.c;
        }
        
        // hashCode method: Generates a hash code for the Card object
        @Override
        public int hashCode() {
            // Using 31 as a multiplier, which is common practice
            int result = Integer.hashCode(n);  // hash code for the number
            result = 31 * result + Integer.hashCode(c);  // hash code for the suit
            return result;
        }
        
        // ToString method for easy printing of the card
        @Override
        public String toString() {
            return "Card{" +
                "n=" + n +
                ", c=" + c +
                '}';
        }
        
        public static void main(String[] args) {
            Card card1 = new Card(1, 3);  // Create a card with value 1 (Ace) and suit 3 (e.g., Clubs)
            Card card2 = new Card(1, 3);  // Create another card with the same value and suit
            Card card3 = new Card(2, 4);  // Create a card with a different value and suit
            
            System.out.println(card1.equals(card2));  // true, since they have the same n and c
            System.out.println(card1.equals(card3));  // false, since they have different n and c
            System.out.println(card1.hashCode());     // prints hash code of card1
            System.out.println(card2.hashCode());     // prints hash code of card2, should be the same as card1
        }
    }
    
}
