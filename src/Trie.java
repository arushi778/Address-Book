
import java.util.ArrayList;

public class Trie {
    static class Node {
        Node children[] = new Node[29];
        boolean eow = false;
        ArrayList<AddressEntry> entries = new ArrayList<>();
        
        Node() {
            for (int i = 0; i < 29; i++) {
                children[i] = null;
            }
        }
    }

    public static Node root = new Node();

    public static void insert(String word, AddressEntry entry) {
        Node curr = root;
        word = word.toLowerCase();
    
        for (int level = 0; level < word.length(); level++) {
            char c = word.charAt(level);
            int idx;
    
            if (c >= 'a' && c <= 'z') {
                idx = c - 'a';
            } else if (c == ' ') {
                idx = 26;
            } else if (c == '\'') {
                idx = 27;
            } else if (c == '.') {
                idx = 28;
            } else {
                continue;
            }
    
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
    
        curr.eow = true;
        curr.entries.add(entry);
    }    

    public static ArrayList<AddressEntry> exactSearch(String key) {
        Node curr = root;
        key = key.toLowerCase();
    
        for (int level = 0; level < key.length(); level++) {
            char c = key.charAt(level);
            int idx;
    
            if (c >= 'a' && c <= 'z') {
                idx = c - 'a';
            } else if (c == ' ') {
                idx = 26;
            } else if (c == '\'') {
                idx = 27;
            } else if (c == '.') {
                idx = 28;
            } else {
                return new ArrayList<>();
            }
    
            if (curr.children[idx] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[idx];
        }
    
        return curr.eow ? curr.entries : new ArrayList<>();
    }    

    public static ArrayList<AddressEntry> prefixSearch(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {

            char c = prefix.charAt(i);
            int idx = prefix.charAt(i) - 'a';

            if (c >= 'a' && c <= 'z') {
                idx = c - 'a';
            } else if (c == ' ') {
                idx = 26;
            } else if (c == '\'') {
                idx = 27;
            } else if (c == '.') {
                idx = 28;
            } else {
                continue;
            }

            if (curr.children[idx] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[idx];
        }
        ArrayList<AddressEntry> result = new ArrayList<>();
        collectEntries(curr, result);
        return result;
    }

    private static void collectEntries(Node node, ArrayList<AddressEntry> result) {
        if (node == null) {
            return;
        }
        if (node.eow) {
            result.addAll(node.entries);
        }
        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null) {
                collectEntries(node.children[i], result);
            }
}
}
}
