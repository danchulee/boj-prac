package algo.boj;

import java.io.*;
import java.util.Stack;

public class Prob17299_오등큰수 {
    static int N;
    static int[] counts = new int[1000001];

    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] ans = new int[N];
        int[] done = new int[N];
        int ans_index = N - 1;
        int done_count = 0;

        for (String x : br.readLine().split(" ")) {
            Integer i = Integer.parseInt(x);
            stack.add(i);
            counts[i]++;
        }

//        ans[ans_index--] = -1;
//        done[done_count++] = stack.pop();
        while (stack.size() > 0) {
            int out = stack.pop();
            boolean found = false;
            for (int i = done_count - 1; i >= 0; i--)
                if (counts[done[i]] > counts[out]) {
                    ans[ans_index--] = done[i];
                    found = true;
                    break;
                }
            if (!found) ans[ans_index--] = -1;
            while (done_count > 0 && (counts[out] >= counts[done[done_count - 1]]))
                done_count--;
            done[done_count++] = out;
        }

        for (int a : ans)
            bw.write(a + " ");
        bw.flush();
        bw.close();
        br.close();
    }
}
