class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] childNode = new TrieNode[26];

    public TrieNode() { }

    public TrieNode(char c) {
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode ws = root;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(ws.childNode[c -'a'] == null) {
                ws.childNode[c - 'a'] = new TrieNode(c);
            }
            ws = ws.childNode[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    public boolean match(char[] input, int inputCurrentIndex, TrieNode node) {
        if(input.length == inputCurrentIndex) return (node.val  !=  ' ' && node.isWord);
        if(input[inputCurrentIndex] != '.') {
            return (node.childNode[input[inputCurrentIndex] - 'a'] != null
                    && match(input, inputCurrentIndex +1, node.childNode[input[inputCurrentIndex] - 'a']));
        } else {
            for(int i = 0; i < node.childNode.length; i++) {
                if(node.childNode[i] != null) {
                    if(match(input, inputCurrentIndex + 1, node.childNode[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(ws.childNode[c -'a'] == null) return false;
            ws = ws.childNode[c - 'a'];
         }
         return true;
    }
}
