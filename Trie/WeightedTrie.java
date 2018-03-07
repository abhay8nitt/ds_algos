package Trie;

class WeightedTrie extends Trie{

    public void add(String word, int weight){
        TrieNode wordNode = insert(word);
        wordNode.name = word;
        wordNode.weight = weight;
    }

    // Get the maximum weight word
    @Override
    public void startsWith(String prefix) {
        super.startsWith(prefix);
    }
}
