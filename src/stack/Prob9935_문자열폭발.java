package stack;

import java.io.*;
import java.util.*;

public class Prob9935_문자열폭발 {
    static String str, cmp;
    static Stack<Character> stack = new Stack<>();
    static int strlen, cmplen;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        cmp = br.readLine();
        strlen = str.length();
        cmplen = cmp.length();

        char ch;
        for (int i = 0; i < strlen; i++) {
            ch = str.charAt(i);
            stack.add(ch);
            if (stack.size() >= cmplen && have())
                for (int j = 0; j < cmplen; j++) stack.pop();
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) ans.append(stack.pop());
        ans = ans.reverse();

        System.out.println(ans.length() == 0 ? "FRULA" : ans);
    }

    public static boolean have() {
        int size = stack.size();
        boolean flag = true;
        for (int i = 0; i < cmplen && flag; i++)
            if (cmp.charAt(i) != stack.get(i + size - cmplen)) flag = false;
        return flag;
    }
}
