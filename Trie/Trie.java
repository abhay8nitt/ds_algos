package Trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
public class Trie {
    protected static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord = false;
        String name;
        Contact contact;
        int weight;

        TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }

        Map<Character, TrieNode> getChildren() {
            return children;
        }

        boolean isWord() {
            return isWord;
        }

        TrieNode next(char ch) {
            return children.get(ch);
        }

        int size() {
            return children.size();
        }

        void clear() {
            children.clear();
        }

        public void setNext(Map<Character, TrieNode> next) {
            this.children = next;
        }

        public Map<Character, TrieNode> getNext() {
            return children;
        }
    }

    protected TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String value) {
        TrieNode wordNode = insert(value);
        wordNode.name = value;
    }

    protected TrieNode insert(String value) {
        TrieNode curr = root;
        for (Character ch : value.toCharArray()) {
            Map<Character, TrieNode> children = curr.getChildren();
            if (children.containsKey(ch))
                curr = children.get(ch);
            else {
                TrieNode temp = new TrieNode();
                children.put(ch, temp);
                curr = temp;
            }
        }
        curr.isWord = true;
        return curr;
    }

    public boolean search(String word) {
        TrieNode node = search(root, word);
        if (node == null) {
            return false;
        } else {
            return node.isWord();
        }
    }

    private TrieNode search(TrieNode root, String text) {
        TrieNode curr = root;
        for (Character ch : text.toCharArray()) {
            if (curr.getChildren().containsKey(ch))
                curr = curr.children.get(ch);
            else
                return null;
        }
        return curr.isWord() ? curr : null;
    }

    public void words() {
        words(root, "");
    }

    protected void words(TrieNode node, String word) {
        print(node, word);

        for (Character ch : node.getChildren().keySet())
            words(node.getChildren().get(ch), word + ch);
    }

    protected void print(TrieNode node, String word) {
        if (node.isWord())
            System.out.println(node.name + ":" + word);
    }

    public void startsWith(String prefix) {
        System.out.println("Contacts starting with:" + prefix);
        startsWith(root, prefix);
        System.out.println();
    }

    protected void startsWith(TrieNode node, String prefix) {
        int size = prefix.length();
        for (int i = 0; i < size; i++) {
            TrieNode next = node.getChildren().get(prefix.charAt(i));
            if (next == null) {
                System.out.println("No data found with the prefix:" + prefix);
                return;
            } else
                node = next;
        }
        words(node, prefix);
    }

    public void delete(String word) {
        Stack<TrieNode> stack = new Stack();
        TrieNode current = root; // root points to the first set of TrieNode children, itself doesn't have any data though
        stack.push(current);
        for (char ch : word.toCharArray()) {
            current = current.next(ch);
            if (current == null) { // the word does not exist e.g. [abc, abcd, abk] : todelete 'abf'
                System.out.println("'" + word + "'" + " doesn't exist in the trie, skipping");
                return;
            }
            stack.push(current);
        }

        if (!current.isWord()) { // the desired word was never found [abc, abcd] : to be deleted 'ab'
            System.out.println("'" + word + "'" + " doesn't exist in the trie, skipping");
            return;
        }

        current.isWord = false; // else at lease the word is found and we need to firstly set it to false and then see how far it can be deleted
        // e.g. [abc,abcdef, abk] : tobedeleted is abcdef : then we need to delete the following characters [def]
        if (!current.getChildren().isEmpty()) { // if the current{i.e. the word node] has any children then just set the name to null and return
            current.name = null;
            return;
        }
        stack.pop(); // else visit each node in the stack and then check if it has just one child and clear the children.. if a word is found no more nodes need to be considered
        current = stack.pop();
        while (!stack.isEmpty() && current.size() == 1) { // keep on popping the stack and exit if a node has multiple children or if it is a word
            current.clear();
            if (current.isWord()) {
                return;
            }
            current = stack.pop();
        }
    }
}