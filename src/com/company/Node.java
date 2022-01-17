package com.company;

// A Tree node
public class Node {
    Node left = null;
    Node right = null;
    char ch;
    int freq;

    Node(Node left, Node right, char ch, int freq) {
        this.left = left;
        this.right = right;
        this.ch = ch;
        this.freq = freq;
    }

    public char getCh() {
        return ch;
    }

    public int getFreq() {
        return freq;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}
