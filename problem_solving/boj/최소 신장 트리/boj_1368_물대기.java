/*
 * 2021-03-03
 * https://www.acmicpc.net/problem/1368
 * 백준 mst 골드 2 풀이 중
 *
프림 알고리즘으로 풀어보고 있는데 계속 18%에서 틀린다. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int answer = 0;
        int cost;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        boolean[] hasWater = new boolean[n]; // 논 물 보유
        int[] nodes = new int[n]; // 논에 우물 파는 비용
        Edge[][] edges = new Edge[n][n];

        for (int idx = 0; idx < n; idx++) {
            nodes[idx] = Integer.parseInt(br.readLine());
        }
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                cost = Integer.parseInt(st.nextToken());
                if(row == col) continue;
                if(row < col) {
                    edges[row][col] = new Edge(row, col, cost);
                } else {
                    edges[row][col] = edges[col][row];
                }
            }
        }

        int count = 0;
        Edge curEdge, nextEdge;

        while (count < n) {
            int minIdx = 0;
            int minCost = Integer.MAX_VALUE;
            for (int idx = 0; idx < n; idx++) {
                if (!hasWater[idx] && minCost > nodes[idx]) {
                    minCost = nodes[idx];
                    minIdx = idx;
                }
            }
            hasWater[minIdx] = true;
            answer += minCost;
            count++;

            for (int nextIdx = 0; nextIdx < n; nextIdx++) {
                if (minIdx == nextIdx) continue;
                nextEdge = edges[minIdx][nextIdx];
                if (!nextEdge.isVisited && !hasWater[nextIdx] && nextEdge.c < nodes[nextIdx]) {
                    nextEdge.setOppositeFrom(minIdx);
                    pq.add(nextEdge);
                }
            }

            while (!pq.isEmpty()) {
                curEdge = pq.poll();
                if (curEdge.isVisited) continue;
                curEdge.isVisited = true;
                if (hasWater[curEdge.dest]) continue;
                hasWater[curEdge.dest] = true;
                answer += curEdge.c;
                count++;

                for (int nextIdx = 0; nextIdx < n; nextIdx++) {
                    if (curEdge.dest == nextIdx) continue;
                    nextEdge = edges[curEdge.dest][nextIdx];
                    if (!nextEdge.isVisited && nextEdge.c < nodes[nextIdx]) {
                        nextEdge.setOppositeFrom(curEdge.dest);
                        pq.add(nextEdge);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int c;
    int dest;
    boolean isVisited;

    public Edge(int u, int v, int c) {
        this.u = u;
        this.v = v;
        this.c = c;
        isVisited = false;
    }

    public void setOppositeFrom(int cur) {
        this.dest = cur == u ? v : u;
    }

    @Override
    public int compareTo(Edge o) {
        return c - o.c;
    }
}