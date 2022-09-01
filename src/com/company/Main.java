package com.company;

import javafx.scene.layout.Priority;

import java.util.*;
import java.io.*;

class pair {
    public pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    Integer first;
    Integer second;
}

public class Main {


    int[][] adj = new int[100][100];
    boolean[] vis = new boolean[100];

    void BFS(int src) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        vis[src] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (!vis[v]) {
                    q.add(v);
                    vis[v] = true;
                }
            }
        }
    }


    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                int m = len / i;
                String str = s.substring(0, i);
                String sb = "";
                for (int j = 0; j < m; j++) {
                    sb += str;
                }
                if (sb.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        //Student(String id, String name, Course[] course)
        //Course(String name, int mark)

        //   Student st = new Student("15", "ABD", new Course[]{new Course("s1", 100), new Course("s2", 95)});

    }


    public static void fun(int time) {
        if (time > 0) {
            fun(time - 1);
            int x = 10 + time;
            System.out.println(x);
        }
    }

    public int deepestLeavesSum(TreeNode root) {
        return helper(root, depth(root));
    }

    public int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    int helper(TreeNode root, int maxDepth) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int ans = 0;
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode curr = q.poll();
            for (int i = 0; i < size; i++) {
                if (curr.left != null) {
                    q.add(curr.left);
                    if (maxDepth == depth) {
                        ans += curr.val;
                    }
                }
                if (curr.right != null) {
                    q.add(curr.right);
                    if (maxDepth == depth) {
                        ans += curr.val;
                    }
                }

            }
        }
        return ans;
    }

    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        HashMap<Integer, Integer> h = new HashMap<>();
        for (Integer i : nums) {
            h.put(i, h.getOrDefault(i, 0) + 1);
        }
        q.addAll(h.entrySet());
        int[] ans = new int[k];
        int i = 0;
        while (i < k) {
            ans[i++] = q.poll().getKey();
        }
        return ans;
    }


    public static boolean EngLetters(String str) {
        HashSet<Character> set = new HashSet<>();
        for (Character i : str.toCharArray()) {
            set.add(i);
        }
        return set.size() == 26;

    }

    static void QFun(Queue<Integer> q) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int Minvalue = q.peek(), ind = 0;
            for (int j = 0; j < size; j++) {
                if (Minvalue > q.peek() && j < size - i) {
                    Minvalue = q.peek();
                    ind = j;
                }
                q.add(q.remove());
            }
            for (int k = 0; k < size; k++) {
                if (k == ind) Minvalue = q.poll();
                else q.add(q.poll());
            }
            q.add(Minvalue);
        }
    }


    static void fun(Stack<Integer> st) {

        if (st.isEmpty()) return;
        int x = st.pop();
        fun(st);
        push(st, x);
    }

    static void push(Stack<Integer> st, int x) {
        if (st.isEmpty()) st.push(x);
        else {
            int y = st.pop();
            push(st, x);
            st.push(y);
        }
    }


    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i : nums) {
            arr.add(i);
        }
        Collections.sort(arr);
        for (int i = 0; i < nums.length; i++) {
            ans[i] = arr.indexOf(nums[i]);
        }
        return ans;

    }

    public static <E extends Comparable<E>> Queue<E> sort(Queue<E> q) {
        int size = q.size();
        for (int i = 0; i < size; i++) {// 5 4 6 12 1 0
            E minVal = q.peek();
            int ind = -1;
            E temp = q.peek();
            for (int j = 0; j < size; j++) {
                if (minVal.compareTo(q.peek()) > -1 && j < size - i) {
                    minVal = q.peek();
                    ind = j;
                }
                q.add(q.poll());
            }// n log

            for (int j = 0; j < size; j++) {//5 4 2 3 1
                if (j == ind) {
                    temp = q.poll();
                } else q.add(q.poll());
            }//
            System.out.println(temp);
            q.add(temp);
        }

        return q;

    }

    public static int missNumberArray(int[] arr) {
        int n = arr.length;
        int sum = (n * (n + 1)) >> 1;
        int newSum = 0;
        for (int j : arr) {
            newSum += j;
        }
        return sum - newSum;
    }

    public static <E> E removeBottom(Stack<E> st) {
        if (st.isEmpty()) return null;
        E temp = st.pop();
        if (st.isEmpty()) return temp;
        E ans = removeBottom(st);
        st.push(temp);
        return ans;
    }


}


/*
[-10,-3,0,5,9]
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;

        if (head == tail) return null;

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }



        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head, slow);
        thead.right = toBST(slow.next, tail);

        return thead;
    }

}

 */
class Course {
    private String name;
    private int mark;

    public Course(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }


}

class Student {
    private String id;
    private String name;
    private Course[] cousres = new Course[3];

    public Student(String id, String name, Course[] course) {
        this.id = id;
        this.name = name;
        this.cousres = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
