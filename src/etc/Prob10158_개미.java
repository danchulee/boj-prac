package etc;

import java.io.*;
import java.util.*;

public class Prob10158_개미 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(br.readLine());

        int tmp_T;
        tmp_T = T - (W - P);
        if ((tmp_T / W) % 2 == 0)
            P = W - tmp_T % W;
        else P = tmp_T % W;
        tmp_T = T - (H - Q);
        if ((tmp_T / H) % 2 == 0)
            Q = H - tmp_T % H;
        else Q = tmp_T % H;

        System.out.print(P + " " + Q);
    }
}
