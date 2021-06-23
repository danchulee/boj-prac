package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob1062_가르침 {
    static int N, K, ans;
    static String[] words;
    static HashSet<Character> set = new HashSet<>();
    static ArrayList<Character> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        String tmp;
        int len;
        char ch;
        for (int i = 0; i < N; i++) {
            tmp = br.readLine();
            words[i] = tmp.substring(4, tmp.length() - 4);
            len = words[i].length();
            for (int j = 0; j < len; j++) {
                ch = words[i].charAt(j);
                if (ch != 'a' && ch != 'n' && ch != 't' && ch != 'i' && ch != 'c')
                    set.add(words[i].charAt(j));
            }
        }
        list = new ArrayList<>(set);
        if (K >= 5)
            dfs(0, 1L | (1L << ('c' - 'a')) | (1L << ('n' - 'a')) | (1L << ('t' - 'a')) | (1L << ('i' - 'a')), 0);
        System.out.print(ans);
    }

    public static void dfs(int learned, long visit, int index) {
        if (learned == K - 5 || learned == set.size()) {
            ans = Math.max(ans, count(visit));
            return;
        }
        char ch;
        for (int i = index; i < list.size(); i++) {
            ch = list.get(i);
            dfs(learned + 1, visit | (1L << (ch - 'a')), i + 1);
        }
    }

    public static int count(long used) {
        int len, res = 0;
        boolean flag;
        for (int i = 0; i < N; i++) {
            len = words[i].length();
            flag = true;
            for (int j = 0; j < len && flag; j++)
                if ((used & (1L << (words[i].charAt(j) - 'a'))) == 0) flag = false;
            if (flag) res++;
        }
        return res;
    }
}
