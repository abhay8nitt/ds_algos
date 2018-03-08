package String;

import java.util.HashMap;
import java.util.Map;

public class StringQs {

    public static void main(String[] args) {
        StringQs examples = new StringQs();
        System.out.println(examples.isCircular("MRMRMRM"));
        System.out.println(examples.isCircular("MRMRMRML"));
        System.out.println(examples.isCircular("MRMRMRMLM"));
        examples.permute(new char[]{'A','B','C'});
        examples.permute(new char[]{'A','B','A'});
        System.out.println("Is interleaved:"+ examples.isInterLeaved("ABCDE","AB","CD"));
        System.out.println("Is interleaved:"+ examples.isInterLeaved("ABCD","AB","CD"));
        examples.allInterLeaved("AB","CD", "");
    }

    /**
     * Check if given set of moves is circular or not
     *
     *          |N
     *          |
     *          |
     * W        |(0,0)      E
     * ---------------------
     *          |
     *          |
     *          |
     *          |S
     *
     * @param moves
     * @returnZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
     */
    public boolean isCircular(String moves){
        int x = 0, y = 0;
        char dir ='N';
        Map<Character,Map<Character,Character>> directions = new HashMap<>();
        Map<Character,Character> right = new HashMap<>();
        Map<Character,Character> left  = new HashMap<>();

        right.put('N','E');
        right.put('E','S');
        right.put('S','W');
        right.put('W','N');

        left.put('N','W');
        left.put('W','S');
        left.put('S','E');
        left.put('E','N');

        directions.put('L',left);
        directions.put('R',right);
        for(char move : moves.toCharArray()){
            switch (move){

                case 'M': {
                    if(dir =='N') y++;
                    else if(dir == 'S') y --;
                    else if(dir == 'E') x++;
                    else if(dir == 'W') x--;
                    break;
                }
                case 'R': dir = directions.get('R').get(dir); break;
                case 'L': dir = directions.get('L').get(dir); break;
            }
        }
        return (x == 0) && (y == 0);
    }


    public void permute(char A[]){
        permute(A,0,A.length);
    }
    public void permute(char []A, int i, int n){
        if(i == n){
            System.out.println(A);
            return;
        }else{
            for(int j =i;j< n;j++){
                if(i != j && A[i] == A[j]) continue; // Avoid duplicate elements e.g. {A, B, A}
                swap(A, i, j);
                permute(A, i+1, n);
                swap(A, i, j);
            }
        }
    }

    private void swap(char[] A, int i, int j) {
        char temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public char maxOccuringCharacter(char text[]){
        int []buffer = new int[256];
        for(int i = 0;i < text.length;i++){
            buffer[text[i]]++;
        }
        int max = 0;
        char maxChar = ' ';
        for(int i = 0;i < buffer.length;i++){
            if(max < buffer[i]){
                max = buffer[i];
                maxChar = (char) i;

            }
        }
        return maxChar;
    }

    public void reverse(char text[]){
        int start = 0;
        int end  = text.length-1;
        while(start <= end){
            char temp = text[start];
            text[start] = text[end];
            text[end] = temp;
            start++;
            end--;
        }
    }

    public int words(char text[], char delimiter){
        int count = 0;
        boolean space = false;
        int idx = 0;
        while(text[idx++] == delimiter);
        for(;idx < text.length;idx++){
            if(text[idx] == ' '){
                count++;
                space = true;
                while(idx < text.length && text[idx++] == delimiter);
            }else space = false;
        }
        return space ? count : count + 1;
    }

    public void compress(char text[], char delimiter){
        int counter = -1;
        int idx = 0;
        for(; idx < text.length ;idx++){
            if(text[idx] == delimiter){
                if(counter == -1) counter = idx; // first space encountered
                while(idx < text.length && text[idx] == delimiter) idx++;
                idx--;
            }else if(counter > -1){
                while(idx < text.length && text[idx] != delimiter) text[counter++] = text[idx++];
            }
        }
        while(counter < text.length) text[counter++] = ' ';
    }


    public boolean isInterLeaved(String S, String S1, String S2){
        if(S.length() != S1.length() + S2.length()) return false;
        int s1 =0, s2 =0;
        for(char c : S.toCharArray()){
             if(s1 < S1.length() && S1.charAt(s1) == c){
                 s1++;
             }else if(s2 < S2.length() && S2.charAt(s2) == c){
                 s2++;
             }else
                 return false;
        }
        return true;
    }


    public void allInterLeaved(String S1, String S2, String S){
        if(S1.length() == 0 && S2.length() == 0){
            System.out.println(S);
            return;
        }
        if(S1.length()!=0){
            allInterLeaved(S1.substring(1), S2, S + S1.charAt(0));
        }
        if(S2.length()!=0){
            allInterLeaved(S1, S2.substring(1), S + S2.charAt(0));
        }
    }
}
