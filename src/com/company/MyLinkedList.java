package com.company;

import java.util.*;

public class MyLinkedList<T extends Comparable<T>> {

    Node<T> head, tail;
    private int length;

    public MyLinkedList() {
        length = 0;
        head = tail = null;
    }

    boolean isEmpty() {
        return length == 0;
    }

    public void addFirst(T val) {
        Node<T> newNode = new Node<T>(val);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        ++length;
    }

    public void addLast(T val) {
        Node<T> newNode = new Node<T>(val);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            //   newNode.setNext(null);
            tail = newNode;

        }
        ++length;
    }

    public void addPos(int ind, T val) {
        if (ind < 0 || ind > length) throw new IndexOutOfBoundsException("out of range");
        else if (ind == 0) addFirst(val);
        else if (ind == length) addLast(val);
        else {
            Node<T> curr = head;
            Node<T> newNode = new Node<T>(val);
            for (int i = 0; i < ind - 1; i++) {
                curr = curr.getNext();
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            //tail.setNext(newNode);
            ++length;
        }
    }

    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        T ret = tail.getVal();
        Node<T> curr = head;
        while (curr.getNext().getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(null);
        tail = curr;
        --length;
        return ret;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        T ret = head.getVal();
        if (size() == 0) {
            head = tail = null;
            return ret;
        }
        head = head.getNext();
        --length;
        return ret;
    }

    public T removePos(int ind) {
        if (ind < 0 || ind >= size()) {
            throw new IndexOutOfBoundsException("out of range");
        } else if (ind == 0) return removeFirst();
        else if (size() - 1 == ind) return removeLast();
        else {
            Node<T> A = head, B = head.getNext().getNext();
            for (int i = 0; i < ind - 1; i++) {
                A = A.getNext();
                B = B.getNext();
            }
            T ret = A.getNext().getVal();

            A.setNext(B);
            --length;
            return ret;
        }
    }

    public T remove(int ind) {
        if (ind < 0 || ind >= size()) {
            throw new IndexOutOfBoundsException("out of range");
        }
        Node<T> temp = null, tempX = null, curr = head;
        T ret = null;
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (i == ind) {
                ret = curr.getVal();
                curr = curr.getNext();
                continue;
            }
            if (count == 0) temp = tempX = curr;
            else {
                tempX.setNext(curr);
                tempX = curr;
            }
            curr = curr.getNext();
            ++count;

        }
        --length;
        head = temp;
        tempX.setNext(null);
        tail = tempX;
        return ret;
    }

    public T removeAtPos(int ind) {
        if (ind < 0 || ind >= size()) {
            throw new IndexOutOfBoundsException("out of range");
        }
        Node<T> temp = head, curr = null;
        T ret = null;
        if (ind == 0) {
            ret = temp.getVal();
            head = temp.getNext();
        } else {
            for (int i = 0; i <= ind; i++) {
                if (i == ind) {
                    ret = temp.getVal();
                    curr.setNext(temp.getNext());
                } else {
                    curr = temp;
                    temp = temp.getNext();
                }
            }
        }
        --length;
        return ret;
    }

    public int size() {
        return length;
    }

    void revers() {
        Node<T> prev = null, cur = head, next = cur.getNext();
        tail = head;

        while (next != null) {
            next = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    void clear() {
        length = 0;
        tail = head = null;
    }


    public void set(int ind, T val) {
        if (ind < 0 || ind >= size()) throw new IndexOutOfBoundsException("out of range");
        Node<T> curr = head;
        for (int i = 0; i <= ind; i++) {
            if (i == ind) {
                curr.setVal(val);
            }
            curr = curr.getNext();
        }

    }

    public T get(int ind) {
        if (ind < 0 || ind >= size()) throw new IndexOutOfBoundsException("out of range");
        Node<T> curr = head;
        for (int i = 0; i < ind; i++) {
            curr = curr.getNext();
        }
        return curr.getVal();
    }

    public T getFirst() {
        Node<T> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return f.getVal();
    }

    public T getLast() {
        Node<T> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return l.getVal();
    }

    public int indexOf(Object o) {
        int ret = 0;
        Node<T> curr = head;
        while (curr != null) {
            if (curr.getVal().equals(o)) {
                return ret;
            }
            ret++;
            curr = curr.getNext();
        }
        return -1;
    }

    public int indexOfLast(Object o) {
        int ret = -1;
        Node<T> curr = head;
        for (int i = 0; i < size(); i++) {
            if (curr.getVal().equals(o)) {
                ret = i;
            }
            curr = curr.getNext();
        }
        return ret;
    }

    public int Cer() {
        Node<T> curr = head;
        Node<T> temp = null;
        for (int i = 0; curr.getNext() != null; i++, curr = curr.getNext()) {
            temp = head;
            for (int j = 0; j < i; j++, temp = temp.getNext()) {
                if (temp == curr) return j;
            }
        }
        return -1;
    }

    void rotateLeft(int k) {
        Node<T> prev = head;
        tail.setNext(head);
        while (k-- > 1) {
            prev = prev.getNext();
        }
        tail = prev;
        head = tail.getNext();
        tail.setNext(null);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[ ]";
        StringBuilder str = new StringBuilder("[ ");
        Node<T> curr = head;
        while (curr != null) {
            str.append(curr.getVal()).append(", ");
            curr = curr.getNext();
        }
        return str.substring(0, str.length() - 2) + " ]";
    }

    public MyLinkedList<T> Merge(MyLinkedList<T> s, MyLinkedList<T> s1) {
        MyLinkedList<T> temp = new MyLinkedList<T>();
        Node<T> current1 = s.head;
        Node<T> current2 = s1.head;
        while (current1 != null && current2 != null) {
            if (current1.getVal().compareTo(current2.getVal()) > 0) {
                temp.addLast(current2.getVal());
                current2 = current2.getNext();
            } else {
                temp.addLast(current1.getVal());
                current1 = current1.getNext();
            }
        }
        while (current1 != null) {
            temp.addLast(current1.getVal());
            current1 = current1.getNext();
        }
        while (current2 != null) {
            temp.addLast(current2.getVal());
            current2 = current2.getNext();
        }
        return temp;
    }


    public void Q5(T val) {
        // Node<T> curr=head;
        int count = 0;
        while (count < size()) {
            if (get(count) == val) {
                removePos(count);
            } else
                ++count;
        }

    }

    public void Q6(Integer val) {
        removePos(indexOf(val) + 1);
    }

    public void Q7(Integer val) {
        removeAtPos(indexOfLast(val));
    }

    public void Q1(T v1, T v2) {
        Node<T> curr = head;
        int count = 1;
        while (curr != null) {
            if (curr.getVal().equals(v1)) {
                addPos(count, v2);
            }
            curr = curr.getNext();
            ++count;
        }
    }

    public MyLinkedList<Integer> Q2(T val) {
        MyLinkedList<Integer> ans = new MyLinkedList<Integer>();
        Node<T> curr = head;
        int count = 0;
        while (curr != null) {
            if (curr.getVal().equals(val)) {
                ans.addLast(count);

            }
            ++count;
            curr = curr.getNext();
        }
        return ans;
    }

    public MyLinkedList<T> Q3(MyLinkedList<T> l1, MyLinkedList<T> l2) {
        int count = 0;
        MyLinkedList<T> ans = new MyLinkedList<T>();
        for (Node<T> i = l1.head; i != null; i = i.getNext()) {
            if (l2.indexOf(i.getVal()) > -1 && ans.indexOf(i.getVal()) == -1) {
                ans.addLast(i.getVal());
            }
        }
        return ans;
    }

    public T Q4(int ind) {
        return get(size() - ind - 1);
    }

    public MyLinkedList<T> Q8(MyLinkedList<T> l1, MyLinkedList<T> l2) {
        l1 = sortHabal(l1);
        l2 = sortHabal(l2);
        return Merge(l1, l2);
    }

    public MyLinkedList<T> Q9(MyLinkedList<T> l1, MyLinkedList<T> l2) {
        l1 = sortHabal(l1);
        l2 = sortHabal(l2);
        MyLinkedList<T> ans = new MyLinkedList<T>();
        for (Node<T> i = l1.head; i != null; i = i.getNext()) {
            for (Node<T> j = l2.head; j != null; j = j.getNext()) {
                if (j.getVal().equals(i.getVal()) && ans.indexOf(i.getVal()) == -1)
                    ans.addLast(i.getVal());
            }
        }
        return ans;
    }

    public MyLinkedList sortHabal(MyLinkedList<T> temp) {
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                if (temp.get(j).compareTo(temp.get(i)) < 0) {
                    T GG = temp.get(i);
                    temp.set(i, temp.get(j));
                    temp.set(j, GG);
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) {

        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 6; ++i) list.addLast(i);
        System.out.println(list);
        list.swapNodes(1, 4);
        System.out.println(list);
        list.fun(7);

    }

    public static Node fun(Node head, ArrayList arr) {
        Node temp = head;
        Node ans = new Node(-1), tail = ans;

        while (temp != null) {
            boolean flag = false;
            for (Object i : arr) {
                if (Objects.equals(i, temp.getVal())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Node newNode = new Node(temp.getVal());
                tail.setNext(newNode);
                tail = newNode;
            }
            temp = temp.getNext();
        }
        return ans.getNext();
    }


    public void swapNodes(Object x, Object y) {
        if (Objects.equals(y, x))
            return;
        Node<T>[] arr = new Node[1];
        Node<T> currX = helper(head, x, arr);
        Node<T> prevX = arr[0];
        Node<T> currY = helper(head, y, arr);
        Node<T> prevY = arr[0];
        if (currX == null || currY == null)
            return;
        if (prevX != null)
            prevX.next = currY;
        else
            head = currY;
        if (prevY != null)
            prevY.next = currX;
        else
            head = currX;
        Node<T> temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }
    Node<T> helper(Node<T> head, Object val, Node<T>[] arr) {
        if (head == null) return null;
        if (Objects.equals(head.val, val)) return head;
        arr[0] = head;
        return helper(head.next, val, arr);
    }


    public void addSorted(Stack<Integer> stk, int val) {
        if (stk.isEmpty() || stk.peek() <= val)
            stk.push(val);
        else {
            int popped = stk.pop();
            addSorted(stk, val);
            stk.push(popped);
        }
    }

    void fun(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(1);
        for (int i = 0; i < n; i++) {
            int a = q.poll();
            int b = q.poll();
            q.add(b);
            q.add(a + b);
            System.out.print(a + " ");
        }
    }

    public static void addSorted(Queue<Integer> q, int val) {
        int i = 0;
        while (q.peek() < val) {
            q.add(q.remove());
            ++i;
            if (i == q.size())
                break;
        }
        i = q.size() - i;
        q.add(val);
        while (i-- != 0)
            q.add(q.remove());
    }
}