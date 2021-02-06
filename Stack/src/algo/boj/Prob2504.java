package algo.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Prob2504 {
    static Stack<String> stack = new Stack<>();
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmpStr = br.readLine();
        if (tmpStr.length() % 2 != 0 || tmpStr.charAt(0) == ')' || tmpStr.charAt(0) == ']') {
            System.out.println(0);
            return;
        }
        String[] strs = new String[tmpStr.length()];
        for (int i = 0; i < tmpStr.length(); i++)
            strs[i] = Character.toString(tmpStr.charAt(i));
        int score = 1;
        for (int i = 0; i < strs.length; i++) {
            switch (strs[i]) {
                case "(":
                    score *= 2;
                    stack.add(strs[i]);
                    break;
                case ")":
                    if (stack.empty() || !stack.peek().equals("(")) {
                        System.out.println(0);
                        return;
                    }
                    if (strs[i - 1].equals("("))
                        answer += score;
                    stack.pop();
                    score /= 2;
                    break;
                case "[":
                    score *= 3;
                    stack.add(strs[i]);
                    break;
                case "]":
                    if (stack.empty() || !stack.peek().equals("[")) {
                        System.out.println(0);
                        return;
                    }
                    if (strs[i - 1].equals("["))
                        answer += score;
                    stack.pop();
                    score /= 3;
            }
        }
        System.out.println(answer);
    }
}
