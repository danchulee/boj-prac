package stack;

import java.io.*;
import java.util.*;

public class Prob1918_후위표기식 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder edit = new StringBuilder();

        Queue<Character> operand = new LinkedList<>();
        Stack<Character> operator = new Stack<>();

        int len = str.length();
        char now;
        for (int i = 0; i < len; i++) {
            now = str.charAt(i);
            if (now == '+' || now == '-' || now == '*' || now == '/') {
                operator.push(now);
            } else if (now == '(') {
                edit.append(operand.poll());
            } else if (now == ')') {
                edit.append(operand.poll()).append(operand.poll()).append(operator.pop());
            } else {
                operand.offer(now);
            }
        }
        while (!operator.isEmpty())
            edit.append(operator.pop());
        System.out.println(edit);
    } // end of Main
} // end of Class
