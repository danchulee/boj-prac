package stack;

import java.io.*;
import java.util.*;

// https://dundung.tistory.com/133
// 차량기지 알고리즘
public class Prob1918_후위표기식 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder edit = new StringBuilder();

        Stack<Character> operator = new Stack<>();
        int len = str.length();
        char now, ch;
        for (int i = 0; i < len; i++) {
            now = str.charAt(i);
            if (now == '+' || now == '-' || now == '*' || now == '/') {
                while (!operator.isEmpty() && value(operator.peek()) >= value(now))
                    if (operator.peek() == '(') break;
                    else edit.append(operator.pop());
                operator.push(now);
            } else if (now == '(') {
                operator.push(now);
            } else if (now == ')') {
                while (!operator.isEmpty() && (ch = operator.pop()) != '(')
                    edit.append(ch);
            } else {
                edit.append(now);
            }
        }
        while (!operator.isEmpty()) edit.append(operator.pop());
        System.out.println(edit);
    } // end of Main

    public static int value(char ch) {
        if (ch == '(') return 2;
        else if (ch == '*' || ch == '/') return 1;
        else return 0;
    }
} // end of Class
