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
        return lcs_dp(X.toCharArray(), Y.toCharArray(), X.length(), Y.length())[X.length()][Y.length()];
    }

    private int[][] lcs_dp(char[] X, char[] Y, int M, int N) {
        int table[][] = new int[M + 1][N + 1];
        for(int i = 0;i < M;i++){
            for(int j=0;j < N;j++){
                if(i == 0 || j ==0)
                    table[i][j] = 0;
                else
                    if(X[i] == Y[j]) table[i][j] = 1 + table[i-1][j-1];
                else
                    table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
            }
        }
        return table;
    }

    public String print_lcs_dp(String X, String Y, boolean print_lcs_table){
        int M = X.length();
        int N = Y.length();
        char []charX  = X.toCharArray();
        char []charY  = Y.toCharArray();
        int lcs[][] =  lcs_dp(charX, charY, M, N);

        if(print_lcs_table){
            print_lcs_table(M, N, charX, charY, lcs);
        }

        StringBuilder lcs_string = new StringBuilder();
        int i = M;
        int j = N;
        while(i > 0 && j > 0){
            if(charX[i-1] == charY[j-1]){
                lcs_string.append(charX[i-1]);
                i--;j--;
            }else if (lcs[i-1][j] > lcs[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        return lcs_string.reverse().toString();
    }

    private void print_lcs_table(int M, int N, char[] charX, char[] charY, int[][] lcs) {
        System.out.print(" ");
        for(int k = 0;k <= charX.length;k++) {
            System.out.print("  " +charY[k]);
        }
        System.out.println();
        for(int i = 0; i < M; i++){
            System.out.print(charX[i]);
            for(int j = 0; j < N; j++){
                System.out.print("  "+ lcs[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
