package mix;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanCoding {
    private Map<Character, String> code;
    private Map<String, Character> reverseCode;

    public HuffmanCoding() {
        code = new HashMap<>();
        reverseCode = new HashMap<>();
    }

    public static void main(String[] args) {
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        String compressed = huffmanCoding.compress("TheEssentialFeature");
        String decompressed = huffmanCoding.decompress(compressed);
        System.out.println(compressed);
        System.out.println(decompressed);
    }

    public void reset(){
        code.clear();
        reverseCode.clear();
    }

    public String compress(String compressed){
        reset();
        HNode root = build(compressed);
        readCode(root, "");
        StringBuilder builder = new StringBuilder();
        for(Character c : compressed.toCharArray()){
            builder.append(code.get(c));
        }
        return builder.toString();
    }

    public String decompress(String decompressed){
        StringBuilder builder = new StringBuilder();
        String buffer = "";
        for(Character c : decompressed.toCharArray()){
            buffer= buffer + c;
            if(reverseCode.containsKey(buffer)){
                builder.append(reverseCode.get(buffer));
                buffer = "";
            }
        }
        return builder.toString();
    }

    private void readCode(HNode root, String s){
        if (root.left== null && root.right == null && Character.isLetter(root.c)) {
            code.put(root.c, s);
            reverseCode.put(s, root.c);
            return;
        }
        readCode(root.left,  s + "0");
        readCode(root.right, s + "1");
    }

    private HNode build(String text){
        PriorityQueue<HNode> queue = new PriorityQueue<>();
        Map<Character, Integer> frequency = new HashMap<>();
        for(char c : text.toCharArray()){
            if(frequency.containsKey(c)){
                frequency.put(c, 1+ frequency.get(c));
            }else
                frequency.put(c,1);
        }

        for(Character c : frequency.keySet()){
            queue.add(new HNode(c, frequency.get(c)));
        }

        while(queue.size() > 1){
            HNode M = queue.poll(); // first minimum
            HNode N = queue.poll(); // second minimum
            HNode temp = new HNode(' ',M.f + N.f);
            temp.left = M;
            temp.right = N;
            queue.add(temp);
        }
        HNode root = queue.poll();
        frequency.clear();
        return root;
    }
}

class HNode implements Comparable<HNode>{
    char c =' ';
    Integer f = 0; // frequency
    HNode left = null;
    HNode right = null;

    public HNode(char c, int f) {
        this.c = c;
        this.f = f;
    }

    @Override
    public int compareTo(HNode o) {
        return f.compareTo(o.f);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HNode hNode = (HNode) o;
        return c == hNode.c &&
                Objects.equals(f, hNode.f) &&
                Objects.equals(left, hNode.left) &&
                Objects.equals(right, hNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, f, left, right);
    }
}
