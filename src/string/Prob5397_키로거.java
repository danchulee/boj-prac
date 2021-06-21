package string;

import java.io.*;
import java.util.*;

// 에디터와 유사한 문제
public class Prob5397_키로거 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> pwd = new LinkedList<>();
        String str;
        int T = Integer.parseInt(br.readLine());
        int len;
        char now;
        for (int tc = 0; tc < T; tc++) {
            str = br.readLine();
            pwd.clear();
            ListIterator<Character> iter = pwd.listIterator();

            len = str.length();
            for (int i = 0; i < len; i++) {
                now = str.charAt(i);
                if (now == '<') {
                    if(iter.hasPrevious()) iter.previous();
                } else if (now == '>') {
                    if(iter.hasNext()) iter.next();
                } else if (now == '-') {
                    if(iter.hasPrevious()){
                        iter.previous();
                        iter.remove();
                    }
                } else {
                    iter.add(now);
                }
            }
            for(char x : pwd) sb.append(x);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
