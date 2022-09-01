package com.company;

public class  Node <T> {
     T val;
     Node<T> next;
    Node<T> prev;

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node(T val) {
        this.val = val;
    }

    Node(T val, Node<T> next) {
        this.val = val;
        this.next = next;
    }

    public T getVal() {
        return val;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Node<T> getNext() {
        return next;
    }

}
