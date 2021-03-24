import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1번. 상하반전
 * 2번. 좌우반전
 * 3번. 오른쪽으로 90도 회전
 * 4번. 왼쪽으로 90도 회전
 * 5,6번. 일단 4개의 부분배열로 나눔.
 * 5번. 1번위치 -> 2번위치 -> 3번위치 -> 4번위치 -> 1번위치
 * 6번. 1번위치 -> 4번위치 -> 3번위치 -> 2번위치 -> 1번위치
 */
public class Prob16935_배열돌리기3 {
    static int N, M, R;
    static int[][] map;
    static int[][] tmpMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        tmpMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        } // end of Input

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            switch (st.nextToken()) {
                case "1":
                    case1();
                    break;
                case "2":
                    case2();
                    break;
                case "3":
                    case3();
                    break;
                case "4":
                    case4();
                    break;
                case "5":
                    case5();
                    break;
                case "6":
                    case6();
            }
//            for (int j = 0; j < map.length; j++) {
//                for (int k = 0; k < map[i].length; k++)
//                    System.out.print(map[j][k] + " ");
//                System.out.println("");
//            }
//            System.out.println("\n***************");
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++)
                sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    } // end of Main

    public static void case1() {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                tmpMap[i][j] = map[map.length - 1 - i][j];
        copy();
    }

    public static void case2() {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                tmpMap[i][j] = map[i][map[0].length - 1 - j];
        copy();
    }

    public static void case3() {
        tmpMap = new int[map[0].length][map.length];
        for (int i = 0; i < map[0].length; i++)
            for (int j = 0; j < map.length; j++)
                tmpMap[i][j] = map[map.length - 1 - j][i];
        copy();
    }

    public static void case4() {
        tmpMap = new int[map[0].length][map.length];
        for (int i = 0; i < map[0].length; i++)
            for (int j = 0; j < map.length; j++)
                tmpMap[i][j] = map[j][map[0].length - 1 - i];
        copy();
    }

    public static void case5() {
        int rLen = map.length / 2;
        int cLen = map[0].length / 2;
        for (int i = 0; i < rLen; i++)
            for (int j = 0; j < cLen; j++)
                tmpMap[i][j + cLen] = map[i][j];
        for (int i = 0; i < rLen; i++)
            for (int j = cLen; j < map[0].length; j++)
                tmpMap[i + rLen][j] = map[i][j];
        for (int i = rLen; i < map.length; i++)
            for (int j = cLen; j < map[0].length; j++)
                tmpMap[i][j - cLen] = map[i][j];
        for (int i = rLen; i < map.length; i++)
            for (int j = 0; j < cLen; j++)
                tmpMap[i - rLen][j] = map[i][j];
        copy();
    }

    public static void case6() {
        int rLen = map.length / 2;
        int cLen = map[0].length / 2;
        for (int i = 0; i < rLen; i++)
            for (int j = 0; j < cLen; j++)
                tmpMap[i + rLen][j] = map[i][j];
        for (int i = rLen; i < map.length; i++)
            for (int j = 0; j < cLen; j++)
                tmpMap[i][j + cLen] = map[i][j];
        for (int i = rLen; i < map.length; i++)
            for (int j = cLen; j < map[0].length; j++)
                tmpMap[i - rLen][j] = map[i][j];
        for (int i = 0; i < rLen; i++)
            for (int j = cLen; j < map[0].length; j++)
                tmpMap[i][j - cLen] = map[i][j];
        copy();
    }

    public static void copy() {
        if (tmpMap.length != map.length)
            map = new int[tmpMap.length][tmpMap[0].length];
        for (int i = 0; i < tmpMap.length; i++)
            System.arraycopy(tmpMap[i], 0, map[i], 0, tmpMap[i].length);
    }

} // end of Class
