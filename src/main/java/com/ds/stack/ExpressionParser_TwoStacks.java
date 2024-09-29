/*
 ** COPYRIGHT **
 */
package com.ds.stack;

import java.util.Stack;

public class ExpressionParser_TwoStacks {
    
    
    public static void main(String[] args) {
        
        String input = "5*3+6";
        input = "(5*3+6)";
        input = "(5*3)+6";
        input = "((5*3)+6)";
        input = "5*(3+6)";
        input = "(5*(3+6))";
        input = "5*(3+6)";
        char[] arr = input.toCharArray();
        int len = arr.length;
        
        int i = 0;
        
        Stack<OperatorNode> opStack = new Stack<>();
        Stack<Object> stack = new Stack<>();
        
        while (i < len) {
            char c = arr[i];
            if (isOperator(c)) {
                i++;
                OperatorNode op = new OperatorNode();
                op.operator = c;
                opStack.push(op);
                
            } else if (isDigit(c)) {
                i++;
                TerminalNode d = new TerminalNode();
                d.digit = (int) c - (int) '0';
                
                stack.push(d);
            } else if (c == '(') {
                i++;
                stack.push(c);
                
            } else if (c == ')') {
                i++;
                stack.push(c);
                
            } else {
            
            }
        }
        
        Stack<Character> closeBracketStack = new Stack<>(); // 5 * (3+6)
        while (!opStack.isEmpty()) {
            OperatorNode op = opStack.pop();
            Object obj = stack.pop();
            
            Node op1, op2;
            
            if(obj instanceof Character){// )
                char c = (Character)obj;
                
                if(c == ')') {
                    closeBracketStack.push(c);
                    op2 = getOperand(stack, opStack);
                }
                else {
                    closeBracketStack.pop(); //pop )
                    continue;
                }
            }
            else {
                op2 = (Node) obj;
            }
        
            
            op1 = getOperand(stack,opStack);
            
            
            op.op1 = op1;
            op.op2 = op2;
            System.out.println("pushing op");
            stack.push(op);
            System.out.println(stack);
            
        }
        
        System.out.println(stack.pop());
        
        
    }
    
    
    static Node getOperand(Stack<Object> stack, Stack<OperatorNode> opStack) {
        System.out.println(stack);
        Object obj = stack.pop();
        if (obj instanceof Character) {
            OperatorNode op = opStack.pop();
            
            Node op2 = getOperand(stack, opStack);
            
            Node op1 = getOperand(stack,opStack);
            
            stack.pop(); // )
            
            
            op.op1 = op1;
            op.op2 = op2;
            stack.push(op);
            return op;
            
        } else {
            return (Node) obj;
        }
        
    }
    
    static Node getOperand2(Stack<Object> stack, Stack<OperatorNode> opStack) {
        
        Object obj = stack.pop();
        if (obj instanceof Character) {
            Stack<Character> stk = new Stack<>();
            Stack<OperatorNode> opStk = new Stack<>();
            
            stk.push((Character) obj);
            opStk.push(opStack.pop());
            Node op2 = getOperand2(stack, opStack);
            
            obj = stack.pop();
            
            Node op1;
            if (obj instanceof Character) {
                stack.push(obj);
                op1 = getOperand2(stack, opStack);
            } else {
                op1 = (Node) obj;
            }
            
            stack.pop(); // )
            stk.pop();// (
            OperatorNode opNode = opStk.pop();
            opNode.op1 = op2;
            opNode.op2 = op1;
            
            return opNode;
            
        } else {
            return (Node) obj;
        }
        
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
