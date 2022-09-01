package com.company;

public class DoubleLinkedList<T> {
    Node<T> head, tail;
    int length;

    public DoubleLinkedList() {
        length = 0;

    }

    public void addFirst(T val) {
        Node<T> newNode = new Node<>(val);
        if (head == null) {
            head = tail = newNode;
        } else {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        ++length;
    }

    public void addLast(T val) {
        Node<T> newNode = new Node<>(val);
        if (head == null) {
            tail = head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        ++length;
    }

    void addAtPos(int ind, T val) {
        if (ind < 0 || ind > length) throw new IndexOutOfBoundsException("Out of Range");
        else if (ind == 0) addFirst(val);
        else if (ind == length) addLast(val);
        else {
            Node<T> curr = head;
            for (int i = 0; i < ind - 1; i++) {
                curr = curr.getNext();

            }
            Node<T> newNode = new Node<>(val);
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            newNode.setPrev(curr);
            newNode.getNext().setPrev(newNode);
            ++length;
        }
    }

    public T removeFirst() {
        if (head == null) throw new NullPointerException();
        T ret = head.getVal();
        if (length == 1) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        --length;
        return ret;
    }

    public T removeLast() {
        if (tail == null) throw new NullPointerException();
        T ret = tail.getVal();
        if (length == 1) head = tail = null;
        else {
            tail = tail.getPrev();
            tail.getNext().setPrev(null);
            tail.setNext(null);
        }
        --length;
        return ret;
    }

    @Override
    public String toString() {
        if (head == null) return "[ ]";
        Node<T> curr = head;
        StringBuilder str = new StringBuilder("[");
        while (curr != null) {
            str.append(curr.getVal()).append(", ");
            curr = curr.getNext();
        }
        return str.substring(0, str.length() - 2) + " ]";
    }

    public String print() {
        if (head == null) return "[ ]";
        Node<T> curr = tail;
        StringBuilder str = new StringBuilder("[");
        while (curr != null) {
            str.append(curr.getVal()).append(", ");
            curr = curr.getPrev();
        }
        return str.substring(0, str.length() - 2) + " ]";
    }


}

