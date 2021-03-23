package string;

import java.io.*;

public class Prob_IOIOI_시간초과 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        StringBuilder sb = new StringBuilder("I");
        for (int i = 0; i < N; i++)
            sb.append("OI");
        String Pn = sb.toString();
        int answer = 0;
        for (int i = S.indexOf(Pn); i <= M - Pn.length(); i++)
            if (S.substring(i, i + Pn.length()).equals(Pn)) answer++;
        System.out.print(answer);
    }
}
