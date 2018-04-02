package dp;

import java.util.HashMap;
import java.util.Map;

public class CountWaysToFormNumber {
   static int count =0;
    public static void main(String[] args) {
        System.out.println(new CountWaysToFormNumber().ways(10));
        System.out.println(count);
        count =0;
        System.out.println(new CountWaysToFormNumber().ways_memoized(10));
        System.out.println(count);
        count =0;
    }

    /**
     * Find the number of ways to form N using 1,3,5
     *
     * 1+1+1+1+1+1
     * 1+1+1+3
     * 1+1+3+1
     * 1+3+1+1
     * 3+1+1+1
     * 3+3
     * 1+5
     * 5+1
     *
     * @param N
     * @return
     */
    public int ways(int N){
        count++;
        if(N < 0) return 0;
        if(N == 0) return 1;
        return ways(N-1)+ ways(N-3)+ ways(N-5);
    }

    /**
     * Memoized version
     * @param N
     * @return
     */
    public int ways_memoized(int N){
        return ways_memoized(N, new HashMap<>());
    }

    private int ways_memoized(int N, Map<Integer,Integer> memo){
        count++;
        if(memo.containsKey(N)) return memo.get(N);

        if(N < 0) return 0;
        if(N == 0) return 1;
        memo.put(N, ways_memoized(N-1, memo) + ways_memoized(N-3, memo) + ways_memoized(N-5, memo));
        return memo.get(N);
    }
}
