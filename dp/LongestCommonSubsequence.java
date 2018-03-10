package dp;

public class LongestCommonSubsequence {

    /**
     * Naive recusion to find the lcs of two strings - this is NP( non -polymonial) solution
     * @param X
     * @param Y
     * @return
     */
    public int lcs(String X, String Y){
        return _lcs(X.toCharArray(), Y.toCharArray(), X.length(), Y.length());
    }

    private int _lcs(char[] X, char[] Y, int M, int N) {
        if(M == 0 || N == 0) return 0;
        if(X[M-1] == Y[N-1])
            return 1 + _lcs(X, Y, M -1, N -1);
        else
            return Math.max(_lcs(X, Y , M - 1, N), _lcs(X, Y, M, N-1));
    }

    /**
     * This approach uses dynamic programming to find the longest common subsequence. Polynomial time complexity O(MN)
     * @param X
     * @param Y
     * @return
     */
    public int lcs_dp(String X, String Y){
        return lcs_dp(X.toCharArray(), Y.toCharArray(), X.length(), Y.length());
    }

    private int lcs_dp(char[] X, char[] Y, int M, int N) {
        int table[][] = new int[M + 1][N + 1];
        for(int i = 0;i < M;i++){
            for(int j=0;j < N;j++){
                if(i == 0 || j ==0)
                    table[i][j] = 0;
                else
                    if(X[i] == Y[j]) table[i][j] = 1 + table[i-1][j-1];
                else
                    table[i][j] = Math.max(lcs_dp(X, Y, M-1, N), lcs_dp(X, Y, M, N-1));
            }
        }
        return table[M][N];
    }
}
