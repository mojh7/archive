/*
 * 2021-02-08
 * https://www.acmicpc.net/problem/12846
 * 백준 분할 정복 골드 1
 * 풀이 중
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long prevSalary = 0;
        long currentSalary;
        long max = 0;
        long total;
        Info info;
        Stack<Info> s = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            currentSalary = Long.parseLong(st.nextToken());
            if (prevSalary < currentSalary) {
                s.push(new Info(idx, currentSalary));
            } else if (prevSalary > currentSalary) {
                if (!s.isEmpty()) {
                    info = s.peek();
                    total = info.salary * (idx - info.startDay);
                    if (max < total) {
                        max = total;
                    }
                }
            }
        }
        while(!s.isEmpty()) {
            
        }
        System.out.println(max);
    }
}

class Info {
    int startDay;
    long salary;
    public Info(int startDay, long salary) {
        this.startDay = startDay;
        this.salary = salary;
    }
}