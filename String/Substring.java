package String;

public class Substring {
    private String T;

    Substring(String T){
        this.T = T;
    }

    /**
     *  Search for a pattern P in text T using naive library
     * @param P
     * @return
     */
    public int naive(String P){
        int i = 0;
        int j;
        for(; i< T.length(); i++){
            int k = i;
            for(j = 0;j< P.length();j++){
                if(T.charAt(k) == P.charAt(j)){
                    k++;
                }else
                    break;
            }
            if(j == P.length()) return i;
        }
        return -1;
    }

    /**
     * Computes the prefix table for the given pattern P
     * @param P
     * @return
     */
    private int[] computePrefixTable(String P){
        // compute prefix function
        int size = P.length();
        int L [] = new int[size];
        int k = 0;
        for(int i = 1; i < P.length(); ){
            if(P.charAt(k) == P.charAt(i)){
                L[i] = k + 1;
                k++;
                i++;
            }else{
                if(k != 0){
                    k = L[k-1];
                }else{
                    L[i] = 0;
                    i++;
                }
            }
        }
        return L;
    }

    /**
     * Search using KMP algorithm
     * @param P
     * @return
     */
    public int KMP(String P){
        int lengthT = T.length();
        int lengthP = P.length();
        int prefix[] = computePrefixTable(P);
        int q = 0;
        for(int i = 0;i < lengthT; i++){
            while(q > 0 && (P.charAt(q) != T.charAt(i)))
                q = prefix[q-1];
            if(P.charAt(q) == T.charAt(i))
                q++;
            if(q == lengthP)
                return i + 1 - lengthP;
        }
        return -1;
    }

    public static void main(String[] args) {
        Substring algo = new Substring("ababababacacaca");
        System.out.println(algo.naive("ababaca"));
        algo.computePrefixTable("ababaca");
        System.out.println(algo.KMP("ababaca"));
        System.out.println(algo.KMP("ababacad"));
    }
}
