package dp;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    /**
     * Naive algorithm for fibonacci
     * @param num
     * @return
     */
    public double fib(double num){
        if(num == 0 || num == 1){
            return 1;
        }else{
            return fib(num-1) + fib(num-2);
        }
    }

    /**
     * Memoized version of the fibonacci series
     * @param num
     * @return
     */
    public static double fib_memo(double num){
        return fib_memo(num, new HashMap<>());
    }

    /**
     * Memoization
     * @param num
     * @param memo
     * @return
     */
    private static double fib_memo(double num, Map<Double, Double> memo){
        if(memo.get(num)!= null) return memo.get(num);
        if(num == 0 || num == 1){
            memo.put(num,num);
        }else{
            memo.put(num, fib_memo(num-1, memo) + fib_memo(num-2, memo));
        }
        return memo.get(num);
    }

    /**
     * Tabulated version of the fibonacci series
     * @param num
     * @return
     */
    public static double fib_tabulated(double num){
        if (num < 0) return 0;
        double dp[] = new double[(int)num+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2;i < num+1;i++){
            dp[i] = dp[i-1] +dp[i-2];
        }
        return dp[(int) num];
    }
}
