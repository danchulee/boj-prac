package etc;

import java.io.*;
import java.util.*;

public class Prob2527_직사각형 {
    static class Square {
        int x, y, p, q;

        Square(int x, int y, int p, int q) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.q = q;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            Square s1, s2;
            st = new StringTokenizer(br.readLine());
            s1 = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            s2 = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));


            if ((s1.p == s2.x && s1.q == s2.y) || (s2.p == s1.x && s2.q == s1.y) ||
                    (s1.p == s2.x && s1.y == s2.q) || (s2.p == s1.x && s2.y == s1.q))
                sb.append("c").append("\n");
            else if (s1.p == s2.x || s1.y == s2.q || s1.q == s2.y || s1.x == s2.p)
                sb.append("b").append("\n");
            else if (s2.x < s1.p || s1.x < s2.p || s1.y < s2.q || s2.y < s1.q)
                sb.append("a").append("\n");
            else sb.append("d").append("\n");
        }
        System.out.print(sb);
    }
}
