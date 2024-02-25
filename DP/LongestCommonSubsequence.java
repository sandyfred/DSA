/**
Leetcode link: https://leetcode.com/problems/longest-common-subsequence
Brute Force Approach
Generate all subsequences of both strings. 2^N, 2^M
Compare them and find the longest;
Here TC = O(max(2^N, 2^M))

Another approach is recursion:
Here, we represent f(i,j) = LCS of s1 ending at i and s2 ending at j,
where i and j represent the indexes at which the string ends.
So recursion starts with f(s1.length()-1, s2.length()-1)

Now, if characters at i and j matches, we essentially have one character of LCS,
so we return 1 + f(i-1,j-1)

If characters dont match, we have to recursively find out in 2 ways.
1. Keep i constant and reduce j by 1
2. Keep j constant and reduce i by 1;
and return the max of these 2.

So Math.max( f(i-1,j), f(i,j-1) )

This would give us the result, but TC is O(2^N * 2^M), since we're trying out all
possible combinations of s1 and s2, worst case if both strings does not have any common
subsequence.

So to minimize this, memoize it with a dp[][] matrix.
Now, TC = O(N*M);

*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int dp[][] = new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                dp[i][j] = -1;
            }
        }
        return memo(text1, text2, n-1, m-1, dp);
    }

    private int memo(String text1, String text2, int i, int j, int dp[][]) {
        if(i < 0 || j < 0) {
            return 0;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        if(text1.charAt(i) == text2.charAt(j)) {
            return dp[i][j] = 1 + memo(text1, text2, i-1, j-1, dp);
        }

        return dp[i][j] = Math.max(memo(text1, text2, i-1, j, dp), memo(text1, text2, i, j-1, dp));
    }
}
