package com.company;

public class MyDeque<T extends Comparable<T>> {
    private Node<T> front, rear;
    private int size;

    public MyDeque() {
        size = 0;
        front = rear = null;
    }

    void insertFront(T val) {
        Node<T> newNode = new Node<>(val);

        if (front == null) {
            front = rear = newNode;
        } else {
            newNode.setNext(front);
            front.setPrev(newNode);
            front = newNode;
        }
        ++size;
    }

    void insertLast(T val) {
        Node<T> newNode = new Node<>(val);
        if (front == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        }
        ++size;
    }

    T deleteFront() {
        if (isEmpty()) throw new NullPointerException();
        T ret = front.getVal();
        if (size == 1) {
            rear = front = null;
        } else {
            front = front.getNext();
            front.setPrev(null);
        }
        --size;
        if (size == 0) {
            front = rear = null;
        }
        return ret;
    }

    T deleteLast() {
        if (isEmpty()) throw new NullPointerException();
        T ret = rear.getVal();
        if (size == 1) {
            rear = front = null;

        } else {
            rear = rear.getPrev();
            rear.setNext(null);
        }
        --size;
        if (size == 0) {
            front = rear = null;
        }
        return ret;
    }

    T getFront() {
        return front.getVal();
    }

    T getRear() {
        return rear.getVal();
    }

    int size() {
        return size;
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

    private boolean isEmpty() {
        return front == null;
    }
}