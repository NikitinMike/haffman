package com.company;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static java.lang.System.out;
import static java.util.Comparator.comparingInt;

public class Huffman {

    final char Z = '0';
    final char ZC = '\0';

    // traverse the Huffman Tree and store Huffman Codes in a map.
    Map<Character, String> encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null) return null;
        // found a leaf node
        if (root.getLeft() == null && root.getRight() == null) huffmanCode.put(root.getCh(), str);
        encode(root.getLeft(), str + Z, huffmanCode);
        encode(root.getRight(), str + '1', huffmanCode);
        return huffmanCode;
    }

    // traverse the Huffman Tree and decode the encoded string
    int decode(Node root, int index, StringBuilder sb) {
        if (root == null) return index;
        // found a leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            out.print(root.getCh());
            return index;
        }
        index++;
        index = (sb.charAt(index) == Z)
                ? decode(root.getLeft(), index, sb)
                : decode(root.getRight(), index, sb);
        return index;
    }

    // a priority queue to store live nodes of Huffman tree
    Node makeHaffmanTree(String text) {
        // count frequency of appearance of each character and store it in a map
        Map<Character, Integer> freq = new HashMap<>();
        for (byte aByte : text.getBytes(StandardCharsets.UTF_8)) {
            char c = (char) aByte;
            if (!freq.containsKey(c)) freq.put(c, 0);
            freq.put(c, freq.get(c) + 1);
        }
        // Notice that highest priority item has lowest frequency
        // Create a leaf node for each character and add it to the priority queue.
        return optimizePriorityQueue(freq);
    }

    Node optimizePriorityQueue(Map<Character, Integer> freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>(comparingInt(Node::getFreq));
        freq.forEach((key, value) -> pq.add(new Node(key, value)));
        // do till there is more than one node in the queue
        while (pq.size() > 1) {
            // Remove the two nodes of highest priority (lowest frequency) from the queue
            Node left = pq.poll();
            Node right = pq.poll();
            // Create a new internal node with these two nodes as children
            // and with frequency equal to the sum of the two nodes frequencies.
            // Add the new node to the priority queue.
            pq.add(new Node( left, right, ZC, left.getFreq() + right.getFreq()));
        }
        return pq.peek();
    }

    // Builds Huffman Tree and huffmanCode and decode given input text
    public void buildHuffmanTree(String text) {

        out.printf("\nOriginal string was %d:\n%s%n",text.length()*8, text);
        // root stores pointer to root of Huffman Tree
        Node root = makeHaffmanTree(text);

        // traverse the Huffman tree and store the Huffman codes in a map
        Map<Character, String> huffmanCode = encode(root, "", new HashMap<>());
        out.println("\nHuffman Codes are:"); // print the Huffman codes
        huffmanCode.forEach((key, value) -> out.println(key + " ".repeat(value.length()) + value));

        // print encoded string
        StringBuilder sb = new StringBuilder();
        for (byte aByte : text.getBytes()) sb.append(huffmanCode.get((char) aByte));
        out.printf("\nEncoded string is %f:\n%s%n",(float)sb.length(),
            sb.toString().replaceAll("(\\d{64})", "$1\n")
                    .replaceAll("(\\d{8})","$1 ")
        );

        // traverse the Huffman Tree again and this time decode the encoded string
        out.printf("\nDecoded string is:%f%n", text.length()*8/(float)sb.length());
        for (int index = -1; index < sb.length() - 2; ) index = decode(root, index, sb);
    }
}
