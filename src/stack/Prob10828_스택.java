package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Prob10828_스택 {
    static Stack<Integer> stack = new Stack<>();
    static int N;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String turn = br.readLine();
                if (turn.contains("push")) {
                    stack.push(Integer.parseInt(turn.split(" ")[1]));
                } else if (turn.contains("pop")) {
                    System.out.println(stack.empty() ? -1 : stack.pop());
                } else if (turn.contains("size")) {
                    System.out.println(stack.size());
                } else if (turn.contains("empty")) {
                    System.out.println(stack.empty() ? 1 : 0);
                } else {
                    System.out.println(stack.empty() ? -1 : stack.peek());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    } // end of Main
} // end of Class
