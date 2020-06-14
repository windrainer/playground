package com.sen.playground.datastructure.tree;

public class Node {
    int value;
    Node leftChild;
    Node rightChild;

    Node(int value) {
        this.value = value;
    }

    public void display(){
        System.out.println(this.value + "\t");
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
