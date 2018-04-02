package dp;

public class CoinChange {

    public static void main(String[] args) {
        System.out.println(new CoinChange().minimumCoins(new int[]{1,2,5,10,20}, 5, 23));
    }

    /**
     * Naive exponential time for minimum number of coins
     * @param coins
     * @param length
     * @param value
     * @return
     */
    public int minimumCoins(int []coins, int length, int value){
        if(value == 0) return 0;
        int result = Integer.MAX_VALUE;
        System.out.println("F("+value+")");
        for(int i = 0;i < length; i++){
            if(coins[i] <= value){
                System.out.print("  F("+ (value - coins[i]+")"));
                int minCoins = minimumCoins(coins, length - 1, value - coins[i]);

                if(minCoins != Integer.MAX_VALUE && minCoins + 1 < result){
                    result = minCoins + 1;
                }

            }
        }
        System.out.println();
        return result;
    }
}
