package com.company;

import java.util.*;

public class MyQueue<T> {
    Node<T> front, rear;
    int length;

    public MyQueue() {
        front = rear = null;
        length = 0;
    }

    void add(T val) {
        Node<T> newNode = new Node<>(val);
        if (rear == null) {
            rear = front = newNode;
        } else {
            rear.setNext(newNode);
            rear = rear.getNext();
        }
        ++length;
    }

    T remove() {
        T ret = null;
        if (front == null) throw new NoSuchElementException();
        if (length == 1) {
            ret = front.getVal();
            rear = front = null;
        } else {
            ret = front.getVal();
            front = front.getNext();
        }
        --length;
        if (length == 0) {
            front=rear=null;
        }
        return ret;
    }

    boolean isEmpty() {
        return rear == null;
    }

    void clear() {
        length = 0;
        front = rear = null;
    }

    int size() {
        return length;
    }

    T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return front.getVal();
    }

    void addAll(Collection<? extends T> c) {
        Object[] arr = c.toArray();
        for (Object i : arr) {
            add((T) i);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[ ]";
        StringBuilder str = new StringBuilder("[");
        Node<T> curr = front;
        while (curr != null) {
            str.append(curr.getVal()).append(", ");
            curr = curr.getNext();
        }
        return str.substring(0, str.length() - 2) + "]";
    }
}
