/*
 * 2020-10-24
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
 *
 * 0과 1로 이루어진 n * m matrix에서 cell의 값이 1인 인접한 cell들의 갯수가 젤 큰 것은?
 * 상하좌우대각선에 있는 경우 인접하다고 판단함.
 */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static final int EMPTY = 0;
    private static final int FILL = 1;
    private static final int VISITED = 2;
    private static final int POS_X = 0;
    private static final int POS_Y = 1;
    private static final int[][] MOVE_POS = {
        {-1, -1}, {0, -1}, {1, -1},
        {-1, 0}, {1, 0},
        {-1, 1}, {0, 1}, {1, 1}
    }

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        int max = 0;
        int region = 0;
        for(int y = 0; y < grid.length; y++) {
            for(int x = 0; x < grid[0].length; x++) {
                if(grid[y][x] == FILL) {
                    region = dfs(grid, y, x);
                    if(max < region) {
                        max = region;
                    }
                }
            }
        }
    }

    static int dfs(int[][] grid, int startPosX, int startPosY) {
        Stack<int[]> connectedCellStack = new Stack<>();
        
        connectedCellStac.push 
        while()
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
