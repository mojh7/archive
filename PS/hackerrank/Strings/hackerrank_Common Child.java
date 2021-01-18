/*
 * 2021-01-19
 * https://www.hackerrank.com/challenges/common-child/problem?isFullScreen=true
 * Difficulty - Medium
 * 
 * 첫 시도 11/15 tc failed
 */

// Complete the commonChild function below.
static int commonChild(String s1, String s2) {
    int cnt1 = calc(s1, s2);
    int cnt2 = calc(s2, s1);
    return Math.max(cnt1, cnt2);
}

static int calc(String src, String dest) {
    int cntMax = 0;
    int len = src.length();
    int recentMatchedCharIdx = -1;
    int destIdx = 0;
    int[] cnts = new int[src.length()];
    char recentMatchedChar = ' ';
    char srcChar;

    for (int idx = 0; idx < len; idx++) {
        srcChar = src.charAt(idx);
        destIdx = recentMatchedChar == srcChar
                ? recentMatchedCharIdx + 1
                : 0;
        while(destIdx < len) {
            if(destIdx > 0 && cnts[destIdx - 1] > cnts[destIdx]) {
                cnts[destIdx] = cnts[destIdx - 1];
            }
            if(srcChar == dest.charAt(destIdx)) {
                if (cntMax < ++cnts[destIdx]) {
                    cntMax = cnts[destIdx];
                }
                recentMatchedCharIdx = destIdx;
                recentMatchedChar = dest.charAt(recentMatchedCharIdx);
                break;
            }
            destIdx++;
        }
    }
    return cntMax;
}