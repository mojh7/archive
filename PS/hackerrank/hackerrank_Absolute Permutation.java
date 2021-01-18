/*
 * 2021-01-18
 * https://www.hackerrank.com/challenges/absolute-permutation/problem?isFullScreen=true
 * Medium, Implementation
 * 
두 정수 n과 k를 입력받고 다음 조건을 만족시키면 int[] 리턴하고 안되면 -1 리턴
pos 배열내의 모든 원소가 |pos[idx1] - idx2| = k 를 만족하면 pos 배열 출력
n, k, i, diff의 관계를 보며 식을 세우다가 일정 법칙을 찾아서 만들 수 없는 경우 -1 리턴하고
그 외에는 int[] 만들어서 리턴함.

ex
n = 4, k =2
pos[i] i |diff|
3 1 2
4 2 2
1 3 2
2 4 2

1 <= t <= 10
1 <= n <= 10^5
0 <= k < n

-----

히든 케이스에서 통과가 안되서 6, 12 결제해서 인풋보고 예외처리 해서 해결함.

k가 0이 아닐 때
5 2 처럼 n이 k의 배수가 아니면 -1
9 3 처럼 n이 k의 배수인데 n/k가 홀수이면 -1
 */



static int[] NOT_EXISTS = new int[]{-1};
// Complete the absolutePermutation function below.
static int[] absolutePermutation(int n, int k) {
    int[] res = new int[n];
    int sign = -1;
    if (k != 0 && ((n % k == 0 && (n / k) % 2 == 1) || n % k != 0)) {
        return NOT_EXISTS;
    }

    if(k == 0) {
        for(int idx = 0; idx < n; idx++) {
            res[idx] = idx + 1;
        }
    } else {
        for(int idx = 0; idx < n; idx++) {
            sign *= idx % k == 0 ? -1 : 1;
            res[idx] = idx + 1 + k * sign;
        }
    }

    return res;
}
