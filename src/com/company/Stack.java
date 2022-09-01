package com.company;

public class Stack<T> {
    Node<T> top;
    int size;

    public Stack() {
        top = null;
    }

    boolean isEmpty() {
        return top == null;

    }

    void push(T val) {
        Node<T> newNode = new Node<>(val);
        newNode.setNext(top);
        top = newNode;
        ++size;
    }

    T pop() {
        if (isEmpty()) throw new NullPointerException();
        Node<T> temp = top;
        T ret = top.getVal();
        temp = temp.getNext();
        top = temp;
        --size;
        return ret;
    }

    T peek() {
        return top.getVal();
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[ ]";
        StringBuilder str = new StringBuilder("[ ");
        Node<T> curr = top;
        while (curr != null) {
            str.append(curr.getVal()).append(",");
            curr = curr.getNext();
        }
        return str.substring(0, str.length() - 1) + "]";
    }
}
