package string;

import java.io.*;

public class Prob1541_잃어버린괄호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        String tmp;
        String[] tmps;
        int pos, result = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch >= '0' && ch <= '9')
                sb.append(ch);
            else {
                if (sb.length() != 0) {
                    result += Integer.parseInt(sb.toString());
                    sb.setLength(0);
                }
                if (ch == '-') {
                    pos = input.indexOf('-', i + 1);
                    if (pos == -1)
                        pos = input.length();
                    tmp = input.substring(i + 1, pos);
                    tmps = tmp.split("\\+");
                    for (String x : tmps)
                        result -= Integer.parseInt(x);
                    i = pos - 1;
                }
            }
        }
        if (sb.length() != 0)
            result += Integer.parseInt(sb.toString());
        System.out.print(result);
    }
}
