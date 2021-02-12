package algo.boj;

import java.util.Scanner;
import java.util.Stack;

public class Prob2493_탑 {
    static Stack<Integer> towers = new Stack<>();
    static Stack<Integer> pos = new Stack<>();
    static int N;
    static int[] ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int tmp = sc.nextInt();

            while (!towers.empty() && towers.peek() < tmp) {
                towers.pop();
                pos.pop();
            }
            if (towers.empty())
                System.out.print("0 ");
            else
                System.out.println(pos.peek() + " ");
            towers.add(tmp);
            pos.add(i + 1);
        }

        sc.close();
    } // end of Main
} // end of Class
