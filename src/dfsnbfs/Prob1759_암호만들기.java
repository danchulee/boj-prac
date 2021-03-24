import java.io.*;
import java.util.*;

public class Prob1759_암호만들기 {
    static int L, C;
    static char[] alphabets;
    static StringBuilder sb = new StringBuilder();

    // 최소 1개의 모음, 최소 2개의 자음
    // 증가순 (조합)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++)
            alphabets[i] = st.nextToken().charAt(0);
        Arrays.sort(alphabets);
        dfs(0, 0, 0, "");
        System.out.print(sb);
    }

    public static void dfs(int index, int jcnt, int mcnt, String str) {
        if (str.length() == L) {
            if (jcnt >= 1 && mcnt >= 2)
                sb.append(str).append("\n");
            return;
        }
        if (index >= C) return;
        char ch = alphabets[index];
        int select = ((ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') ? 1 : 0);
        dfs(index + 1, jcnt + select, mcnt + (Math.abs(select - 1)), str + ch);
        dfs(index + 1, jcnt, mcnt, str);

    }
}
