package com.company;

public class MyQueueArray<T extends Comparable<T>> {
    int rear, front, length;
    Object[] arr;

    public MyQueueArray(int size) {
        if (size <= 0)
            arr = new Object[100];
        else
            arr = new Object[size];
        front = 0;
        rear = arr.length - 1;
        length = 0;
    }

    boolean isEmpty() {
        return length == 0;
    }

    boolean isFull() {
        return length == arr.length;
    }

    void add(T val) {
        if (isFull()) {
            System.out.println("Queue Full Can't Enqueue ...!");
        } else {
            rear = (rear + 1) % arr.length;

            arr[rear] = val;
            length++;
        }
    }

    void remove() {
        if (isEmpty()) {
            System.out.println("Empty Queue Can't Dequeue ...!");
        } else {
            front = (front + 1) % arr.length;
            --length;
        }
    }

    T frontQueue() {
        assert (!isEmpty());
        return (T) arr[front];
    }

    T rearQueue() {
        assert (!isEmpty());
        return (T) arr[rear];
    }

    void sort() {
        int size = length;
        for (int i = 0; i < size; i++) {// 5 4 6 12 1 0
            T minVal = this.frontQueue();
            int ind = -1;
            T temp = this.frontQueue();
            for (int j = 0; j < size; j++) {
                if (minVal.compareTo(this.frontQueue()) > -1 && j < size - i) {
                    minVal = this.frontQueue();
                    ind = j;
                }
                this.add(this.frontQueue());
                this.remove();
            }// n log

            for (int j = 0; j < size; j++) {//5 4 2 3 1
                if (j == ind) {
                    temp = this.frontQueue();
                } else {
                    this.add(this.frontQueue());
                    this.remove();
                }
            }//
            // System.out.println(temp);
            this.add(temp);
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[ ]";
        StringBuilder str = new StringBuilder("[");
        int i;
        for (i = front; i != rear; i = (i + 1) % arr.length) {
            str.append(arr[i]).append(", ");
        }
        str.append(arr[i]);

        return str + " ]";
    }
}
