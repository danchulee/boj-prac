package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Prob1918_후위표기식_미완성 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String before_fixed = br.readLine();
        StringBuilder fixed = new StringBuilder();
        Stack<Character> tmp = new Stack<>();

        int inside = 0;
        for (int i = 0; i < before_fixed.length(); i++) {
            char ch = before_fixed.charAt(i);
            switch (ch) {
                case '(':
                    inside++;
                    break;
                case ')':
                    inside--;
                    while (!tmp.isEmpty())
                        fixed.append(tmp.pop());
                    break;
                case '+':
                case '-':
                    while (!tmp.isEmpty())
                        fixed.append(tmp.pop());
                    tmp.push(ch);
                    break;
                case '*':
                case '/':
                    tmp.push(ch);
                    break;
                default:
                    fixed.append(ch);
            }
        }
        while (!tmp.isEmpty())
            fixed.append(tmp.pop());
        System.out.println(fixed);
    } // end of Main
} // end of Class
