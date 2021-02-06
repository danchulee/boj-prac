package algo.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Prob2504_x {
    static Stack<Character> stack = new Stack<Character>();

    public static void main(String[] args) throws IOException {
        boolean inside = true;
        int answer = 0, score = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (char ch : br.readLine().toCharArray()) {
            if (stack.empty() && (ch == ']' || ch == ')')) break;
            if (ch == '(' || ch == '[') {
                if (!stack.empty()) {
                    if (stack.peek() == '(')
                        answer += 2 * score;
                    else if (stack.peek() == '[')
                        answer += 3 * score;
                }
                score = 0;
                inside = true;
                stack.add(ch);
            } else if (ch == ')' && stack.peek() == '(' || ch == ']' && stack.peek() == '[') {
                stack.pop();
                if (inside) {
                    inside = false;
                    if (ch == ')') score += 2;
                    else if (ch == ']') score += 3;
                } else {
                    if (ch == ')') score *= 2;
                    else if (ch == ']') score *= 3;
                }
            }
            if (stack.empty()) {
                answer += score;
            }
        }
        if (stack.empty())
            System.out.println(answer);
        else
            System.out.println(0);

    }
}
