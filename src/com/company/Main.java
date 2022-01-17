package com.company;

public class Main {
    public static void main(String[] args) {
        Huffman h1 = new Huffman();
//        h1.buildHuffmanTree("algorithm data compression by Huffman coding is am.".repeat(1));
        h1.buildHuffmanTree("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip " +
        "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
        " sunt in culpa qui officia deserunt mollit anim id est laborum."
        );
    }
}
