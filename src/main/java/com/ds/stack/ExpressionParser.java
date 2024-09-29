/*
 ** COPYRIGHT **
 */
package com.ds.stack;

import java.util.Stack;

public class ExpressionParser {
/*

Convert an expression in to parse tree


"(3+1)"


    +
  3   1


"(5+(3*1))"

      +
    5   *
      3   1


return rootNode()

Assumptions
-----------
Single digit
Operator ( - + * / )
Well formed expression
No precedence of operators.  (If precedence is required, we need to do it in two passes. First
pass, process *,/ since they are associative  and in second pass, process +,- since these two are
 associative.

((5+3)*1)

5 + 3 * 1.     *
           +.        1
        5.    3.
* (+ (5) (3)) (1)


(5+(3*1))



)
+ (5) * (3) (1)
(

 */
    
    
    public static void main(String[] args) {
        
        String input = "5*4";
        input = "(5*4)";
        input = "5*(3/1)";
        input = "(5*(3/1)";
        input = "(5*3)/1";
        input = "((5*3)/1)";
        input = "5*(4+(3*(6/7)))";
        input = "(5*(4+(3/1)))*(6/(7*(2/9)))";
        input = "(5*4/3)";
        
        char[] arr = input.toCharArray();
        int len = arr.length;
        
        int i = 0;
        
        Stack<Object> stack = new Stack<>();
        
        while (i < len) {
            char c = arr[i];
            if (isOperator(c)) {
                i++;
                OperatorNode op = new OperatorNode();
                op.operator = c;
                op.op1 = (Node) stack.pop();
                
                stack.push(op);
                
            } else if (isDigit(c)) {
                i++;
                TerminalNode d = new TerminalNode();
                d.digit = (int) c - (int) '0';
                if (stack.size() == 0) {
                    
                    stack.push(d);
                } else {
                    Object n = stack.pop(); //  (5+(3*(9/3)))
                    if (n instanceof OperatorNode) {
                        ((OperatorNode) n).op2 = d;
                        stack.push(n);
                    } else if (n instanceof Character) { // (
                        stack.push(n);
                        stack.push(d);
                    }
                }
                
            } else if (c == '(') {
                i++;
                stack.push(c);
                
            } else if (c == ')') {
                i++;
                //System.out.println(stack);
                Node op = (Node) stack.pop();
                Object obj = stack.pop();
                if (obj instanceof Character) { // pops (
                    stack.push(op);
                } else {
                    ((OperatorNode) obj).op2 = op;
                    stack.pop(); // pops (
                    stack.push(obj);
                }
                
            } else {
            
            }
            
            
        }
        
        if (stack.size() != 1) {
            Node op2 = (Node) stack.pop();
            OperatorNode op = (OperatorNode) stack.pop();
            op.op2 = op2;
            stack.push(op);
        }
        
        Node n = (Node) stack.pop();
        System.out.println(n.toString());
        
        
    }
    
    static boolean isOperator(char c) {
        return c == '*' || c == '+' || c == '/' || c == '-';
    }
    
    static boolean isDigit(char c) {
        int val = (int) c - (int) '0';
        return val >= 0 && val <= 9;
    }
    
    private static class Node {
    
    
    }
    
    private static class OperatorNode extends Node {
        
        public char operator;
        public Node op1;
        public Node op2;
        
        @Override
        public String toString() {
            return operator + " # " + op1.toString() + " # " + op2.toString();
        }
        
    }
    
    private static class TerminalNode extends Node {
        
        public int digit;
        
        @Override
        public String toString() {
            return "" + digit;
        }
        
    }
    
}
