package dp;

public class Knapsack01 {
    static int count =0;
    public static void main(String[] args) {

       /* System.out.println((new Knapsack01()).knapsack_dp_tabular(new int[]{1, 2, 4, 2, 5}, new int[]{5, 3, 5, 3, 2}, 10));
        System.out.println("Count:"+count);
        count =0;
        System.out.println((new Knapsack01()).knapsack_dp_tabular(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50));
        System.out.println("Count:"+count);
        count =0;*/
        System.out.println((new Knapsack01()).knapsack_dp_memo(new int[]{1, 2, 4, 2, 5}, new int[]{5, 3, 5, 3, 2}, 10));
        System.out.println("Count:"+count);
        count =0;
        System.out.println((new Knapsack01()).knapsack_dp_memo(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50));
        System.out.println("Count:"+count);
        count =0;

    }

    /**
     * Complexity of the below naive algorithm is O(2^n)
     * @param weight
     * @param profit
     * @param knapsackSize
     * @return
     */
    public int knapsack(int []weight, int []profit, int knapsackSize){
        if(weight.length != profit.length) return -1;
        return knapsack(weight, profit, profit.length, knapsackSize);
    }

    /**
     *  weight[]  = [1, 2, 4, 2, 5]
     *  profit[]  = [5, 3, 5, 3, 2]
     *  knapsackSize    = 10
     * @param weight is the weight
     * @param profit is the value of each item against the array
     * @param knapsackSize is the total permissible weight in the knapsack
     * @param N is the size of the items
     * @return
     */
    private int knapsack(int []weight, int []profit, int N, int knapsackSize){
        count++;
         if( N == 0 || knapsackSize == 0) // base case when everything has been picked i.e. knapsackSize=0 or N =0 all items have been picked
            return 0;
         if(weight[N-1] > knapsackSize)
            return knapsack(weight, profit, N - 1, knapsackSize);
         else {
             int value1 = knapsack(weight, profit, N - 1, knapsackSize);
             int value2 = profit[N - 1] + knapsack(weight, profit, N - 1, knapsackSize - weight[N - 1]);
             return Math.max(value1, value2);
         }
    }

    /**
     * A Dynamic approach which uses memoization
     * @param weight
     * @param profit
     * @param knapsackSize
     * @return
     */
    public int knapsack_dp_memo(int []weight, int []profit, int knapsackSize){
        if(weight.length != profit.length) return -1;
        int memo[][] = new int[weight.length][knapsackSize+1];
        return knapsack_dp_memo(weight, profit, weight.length, memo, knapsackSize);
    }

    /**
     *  weight[]  = [1, 2, 4, 2, 5], profit[]  = [5, 3, 5, 3, 2], knapsackSize    = 10 : MaxProfit : 16
     *  weight[]  = [10, 20, 30],    profit[]  = [60, 100, 120],  knapsackSize    = 50 : MaxProfit : 220
     * @param weight is the weight
     * @param profit is the value of each item against the array
     * @param knapsackSize is the total permissible weight in the knapsack
     * @param N is the size of the items
     * @return
     */
    private int knapsack_dp_memo(int []weight, int []profit, int N, int [][]memo, int knapsackSize){
        count++;
        if(memo[N-1][knapsackSize]!=0) return memo[N-1][knapsackSize];
        if( N == 0 || knapsackSize == 0) // base case when everything has been picked i.e. knapsackSize=0 or N =0 all items have been picked
            return 0;
        if(weight[N-1] > knapsackSize)
            return knapsack(weight, profit, N - 1, knapsackSize);
        else {
            int value1 = knapsack(weight, profit, N - 1, knapsackSize);
            int value2 = profit[N - 1] + knapsack(weight, profit, N - 1, knapsackSize - weight[N - 1]);
            memo[N-1][knapsackSize] = Math.max(value1, value2);
        }
        return memo[N-1][knapsackSize];
    }


    public int knapsack_dp_tabular(int []weight, int []profit,int knapsackSize){
        if(weight.length != profit.length) return -1;
        int table[][] = new int[weight.length + 1][knapsackSize];
        for(int i =1;i<=weight.length;i++){
            for(int j=1;j<knapsackSize;j++){
               /* if(i==1 || j==0){
                    table[i][j] = 0;
                }else */if( weight[i-1] <= knapsackSize){
                    table[i][j] = Math.max(profit[i-1] + table[i-1][j-weight[i-1]],  table[i-1][j]);
                }else{
                    table[i][j] = table[i-1][j];
                }
            }
        }
        return table[weight.length][knapsackSize];

    }
}
