package simulation;

import java.io.*;
import java.util.*;

public class Prob2239_스도쿠 {
    static int[][] sudoku = new int[9][9];
    static ArrayList<int[]> zeros = new ArrayList<>();
    //static ArrayList<String> answers = new ArrayList<>();
    static String answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp;
        for (int i = 0; i < 9; i++) {
            tmp = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = tmp.charAt(j) - '0';
                if (sudoku[i][j] == 0)
                    zeros.add(new int[]{i, j});
            }
        }
        dfs(0);
        //Collections.sort(answers);
        //System.out.print(answers.get(0));
        System.out.print(answer);
    }

    public static boolean dfs(int num) {
        if (num == zeros.size()) {
            answer = makeString();
            return true;
        }
        //int size;
        int[] pos = zeros.get(num);
        //ArrayList<Integer> candidates = findCandidates(pos[0], pos[1]);

        //size = candidates.size();
        //if (size == 0) return false;
        for (int j = 1; j < 10; j++) {
            if (validCheck(pos[0], pos[1], j)) {
                //sudoku[pos[0]][pos[1]] = candidates.get(i);
                sudoku[pos[0]][pos[1]] = j;
                if (dfs(num + 1)) return true;
                sudoku[pos[0]][pos[1]] = 0;
            }
        }
        return false;
    }

//    public static ArrayList<Integer> findCandidates(int r, int c) {
//        ArrayList<Integer> res = new ArrayList<>();
//        int[] counts = new int[10];
//        // 가로, 세로
//        for (int i = 0; i < 9; i++) {
//            counts[sudoku[i][c]]++;
//            counts[sudoku[r][i]]++;
//        }
//        // 정사각형
//        int start_r = (r / 3) * 3;
//        int start_c = (c / 3) * 3;
//        for (int i = start_r; i < start_r + 3; i++)
//            for (int j = start_c; j < start_c + 3; j++)
//                counts[sudoku[i][j]]++;
//        for (int i = 1; i < 10; i++)
//            if (counts[i] == 0) res.add(i);
//        return res;
//    }

    public static boolean validCheck(int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][c] == num) return false;
            if (sudoku[r][i] == num) return false;
        }
        // 정사각형
        int start_r = (r / 3) * 3;
        int start_c = (c / 3) * 3;
        for (int i = start_r; i < start_r + 3; i++)
            for (int j = start_c; j < start_c + 3; j++)
                if (sudoku[i][j] == num) return false;
        return true;
    }

    public static String makeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sb.append(sudoku[i][j]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
