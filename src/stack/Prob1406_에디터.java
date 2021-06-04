package stack;

import java.io.*;
import java.util.*;

public class Prob1406_에디터 {
    static int M;

    // iterator를 써서 해야 한다..
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();
        M = Integer.parseInt(br.readLine());

        int len = tmp.length();
        LinkedList<Character> str = new LinkedList<>();
        for (int i = 0; i < len; i++)
            str.add(tmp.charAt(i));

        StringTokenizer st;
        ListIterator<Character> iter = str.listIterator(len);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "L":
                    if (iter.hasPrevious()) iter.previous();
                    break;
                case "D":
                    if (iter.hasNext()) iter.next();
                    break;
                case "B":
                    if(iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    break;
                case "P":
                    iter.add(st.nextToken().charAt(0));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : str)
            sb.append(ch);
        System.out.print(sb);
    }
}
