/*
 * 2021-01-30
 * https://www.acmicpc.net/problem/2206
 * 
 * 푸는중
 */

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int n;
    static int m;
    static int[][][] map;
    static final int[][] MOVE_POS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m][3];
        for(int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < m; col++) {
                map[row][col][2] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        // y, x, 벽 안 부숨 = 0, 이미 부숨 = 1
        q.add(new int[]{0, 0, 0});
        int[] cur = new int[3];
        int curDist;
        int y;
        int x;
        int nextBreakCnt;
        while (!q.isEmpty()) {
            cur = q.poll();
            curDist = map[cur[0]][cur[1]][cur[2]];
            for(int idx = 0; idx < 4; idx++) {
                y = cur[0] + MOVE_POS[idx][0];
                x = cur[1] + MOVE_POS[idx][1];
                nextBreakCnt = 0;
                if(y < 0 || y >= n || x < 0 || x >= m) continue;
                if(cur[2] == 0) {
                    if(map[y][x][2] == 0 && map[y][x][0] != 0 && curDist + 1 >= map[y][x][0])
                        continue;
                    if(map[y][x][2] == 1 && map[y][x][1] != 0 && curDist + 1 < map[y][x][1]) {
                        map[y][x][1] = curDist + 1;
                        nextBreakCnt = 1;
                    }
                } else {
                    if(map[y][x][2] == 1) continue;
                    if(map[y][x][1] != 0 && curDist + 1 < map[y][x][1]) {
                        map[y][x][1] = curDist + 1;
                        nextBreakCnt = 1;
                    }
                }
                q.add(new int[]{ y, x, nextBreakCnt});
            }
        }


    }
}