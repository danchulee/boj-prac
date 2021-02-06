package algo.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Prob2504_xx {
    static Stack<String> stack = new Stack<>();
    static String[] strs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmpStr = br.readLine();
        strs = new String[tmpStr.length()];
        for (int i = 0; i < tmpStr.length(); i++)
            strs[i] = Character.toString(tmpStr.charAt(i));

        for (String str : strs) {
            if ((stack.empty() && (str.equals(")") || str.equals("]"))) || (strs.length % 2 != 0)) {
                stack.add("0");
                break;
            }
            if (str.equals("(") || str.equals("["))
                stack.add(str);
            else if ((str.equals(")") && stack.peek().equals("(")) || (str.equals("]") && stack.peek().equals("["))) {
                stack.pop();
                if (str.equals(")")) stack.add("2");
                else stack.add("3");
            } else if (str.equals(")") || str.equals("]")) {
                if (!stack.peek().equals(")") && !stack.peek().equals("]")) {
                    int tmp = 0;
                    while (!stack.empty() && !stack.peek().equals(")") && !stack.peek().equals("]") && !stack.peek().equals("(") && !stack.peek().equals("[")) {
                        tmp += Integer.parseInt(stack.pop());
                    }
                    if ((str.equals(")") && stack.peek().equals("(")) || (str.equals("]") && stack.peek().equals("["))) {
                        int score;
                        if (str.equals(")")) score = 2;
                        else score = 3;
                        stack.pop();
                        tmp *= score;
                        while (!stack.empty() && !stack.peek().equals(")") && !stack.peek().equals("]") && !stack.peek().equals("(") && !stack.peek().equals("[")) {
                            tmp += Integer.parseInt(stack.pop());
                        }
                        stack.add(Integer.toString(tmp));
                    } else {
                        stack.clear();
                        stack.add("0");
                        break;
                    }
                }
            }
        }
        System.out.println(stack.pop());
    } // end of Main
} // end of Class
