package com.company;


public class Hash<K, V> {

    int size, threshold;
    HashNode<?, ?>[] arr;

    public Hash(int size) {
        if (size > 0) {
            arr = new HashNode<?, ?>[size];
        } else {
            arr = new HashNode<?, ?>[15];
        }
        threshold = (int) Math.min(arr.length * 2.0f, Integer.MAX_VALUE - 7);
    }

    public Hash() {
        this(15);
    }

    public void put(K key, V val) {
        int hash = getHash(key);
        HashNode head = arr[hash];
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
        HashNode newNode = new HashNode(key, val);
        head = arr[hash];
        newNode.next = head;
        head = newNode;
        arr[hash] = head;
        ++size;
    }

    public Object get(K key) {
        int hash = getHash(key);

        HashNode curr = arr[hash];
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
        HashNode curr = arr[hash];
        for (HashNode prev = null; curr != null; prev = curr, curr = curr.next) {
            if (curr.key.equals(key)) {
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
        HashNode<?, ?>[] temp = new HashNode[arr.length * 2];
        threshold = (int) Math.min(temp.length * 2.0f, Integer.MAX_VALUE - 7);
        for (HashNode<?, ?> arr1 : arr) {
            if (arr1 != null) {
                HashNode<?, ?> curr = arr1;
                while (curr != null) {
                    int hash = curr.key.hashCode() % temp.length;
                    HashNode newNode = new HashNode(curr.key, curr.val);
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
        StringBuilder ret = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                ret.append("hash(").append(i).append("):{");
                HashNode<?, ?> curr = arr[i];
                while (curr != null) {
                    ret.append("key= ").append(curr.key).append(" value=").append(curr.val).append(", ");
                    curr = curr.next;

                }
                ret = new StringBuilder(ret.substring(0, ret.length() - 2) + "}\n");
            }

        }
        return ret + "]";
    }

    public static void main(String[] args) {
        Hash<Integer, Integer> h = new Hash<>();
        for (int i = 0; i < 100; i++) {
            h.put(i, i);
        }
        h.put(30, Integer.SIZE);
        System.out.println(h);
        h.remove(30);
        System.out.println(h);
    }

}


