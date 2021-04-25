import java.io.*;
import java.util.*;

public class Prob1038_감소하는수 {
    static ArrayList<Long> list = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N <= 10) System.out.print(N);
        else if (N > 1022) System.out.print(-1);
        else {
            for (int i = 0; i < 10; i++)
                dfs(i, 1);
            Collections.sort(list);
            System.out.println(list.get(N));
        }

    }

    public static void dfs(long num, int len) {
        if (len > 10) return;
        list.add(num);
        for (int i = 0; i < 10; i++)
            if (num % 10 > i) // 가장 끝자리보다 작은 i만 끝에 추가
                dfs((num * 10) + i, len + 1);
    }
}
