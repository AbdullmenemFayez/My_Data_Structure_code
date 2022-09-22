package hash;

import java.util.*;

public class Hash<K, V> {

    int size, threshold, Max, sum, count;
    Node[] arr;

    public Hash(int size) {
        if (size > 0) {
            arr = new Node[size];
        } else {
            arr = new Node[15];
        }
        threshold = (int) Math.min(arr.length * 0.75f, Integer.MAX_VALUE - 7);
    }

    public Hash() {
        this(15);
    }

    public void put(K key, V val) {
        int hash = getHash(key);
        Node head = arr[hash];
        while (head != null) {
            if (head.key.hashCode() == hash && head.key.equals(key)) {
                head.val = val;
                ++size;
                return;
            }
            head = head.next;
        }
        if (size >= threshold) {
            rehash();
        }
        Node newNode = new Node(key, val);
        head = arr[hash];
        newNode.next = head;
        head = newNode;
        arr[hash] = head;
        ++size;
    }

    public Object get(K key) {
        int hash = getHash(key);

        Node curr = arr[hash];
        while (curr.next != null) {
            if (curr.key.equals(key)) {
                return curr.val;
            }
            curr = curr.next;
        }
        return null;
    }

    int getHash(Object key) {
        return key.hashCode() % arr.length;
    }

    public V remove(Object key) {
        V ret = null;
        int hash = getHash(key);
        Node curr = arr[hash];
        for (Node prev = null; curr != null; prev = curr, curr = curr.next) {
            if (curr.key.hashCode() == hash && curr.key.equals(key)) {
                ret = (V) curr.val;
                if (prev != null) {
                    prev.next = curr.next;
                } else {
                    arr[hash] = curr.next;
                }
                --size;
                break;
            }
        }
        return ret;
    }

    public void rehash() {
        Node[] temp = new Node[arr.length * 2];
        threshold = (int) Math.min(temp.length * 0.75f, Integer.MAX_VALUE - 7);
        for (Node arr1 : arr) {
            if (arr1 != null) {
                Node curr = arr1;
                while (curr != null) {
                    int hash = curr.key.hashCode() % temp.length;
                    Node newNode = new Node(curr.key, curr.val);
                    newNode.next = temp[hash];
                    temp[hash] = newNode;
                    curr = curr.next;

                }
            }
        }
        arr = temp;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        String ret = "[";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                ret += "hash(" + i + "):{";
                Node curr = arr[i];
                while (curr != null) {
                    ret += "key= " + curr.key + " value=" + curr.val + ", ";
                    curr = curr.next;

                }
                ret = ret.substring(0, ret.length() - 2) + "}\n";
            }

        }
        return ret + "]";
    }

    public boolean search(Object key) {
        int hash = getHash(key);
        Node curr = arr[hash];
        int counter = 0;
        while (curr != null) {

            if (curr.key.hashCode() == hash && curr.key.equals(key)) {
                Max = (counter > Max) ? counter : Max;
                this.count += counter;
                return true;
            }
            ++counter;
            curr = curr.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Random r = new Random();
        Hash<String, String> h = new Hash<>();

        for (int i = 0; i < 100; i++) {
            h.put(i + "", i + "");
        }
        for (int i = 0; i < 50; i++) {
            h.search(i + "");
        }
        for (int i = 100; i < 150; i++) {
            h.search(i + "");
        }
        System.out.println(h);
    }

}

class Node<K, V> {

    K key;
    V val;
    Node next;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
