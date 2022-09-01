package com.company;

public class HashNode<K,V> {
    K key;
    V val;
    HashNode<K,V> next;

    public HashNode() {

    }

    public HashNode(K key, V val) {
        this.key = key;
        this.val = val;
        next = null;


    }
}
