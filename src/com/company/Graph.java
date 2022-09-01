package com.company;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.*;

public class Graph {
    HashMap<Integer, HashSet<Integer>> graph;
    static int v;

    public Graph() {
        graph = new HashMap<>();
        for (int i = 0; i < v; i++) {

            graph.put(i, new HashSet<>());
        }
    }

    public void addEdge(int src, int dest) {
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }

    public void searchEdge(int src, int dest) {
        Iterator<Integer> set = graph.get(src).iterator();

        if (graph.get(src).contains(dest))
            System.out.println("Edge from " + src + " to " + dest + " found");
        else
            System.out.println("Edge from " + src + " to " + dest + " not found");

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        char[] ch = {'ح', 'ة', 'ي', 'ك', 'ت', 'أ', 'ز', 'و', 'ف', 'ر', 'ن', 'ل', 'ض'};


    }

    static int[] ans;
    static int max = 0;

    public static void maxOrdaring(int[] arr) {
        int mid = arr.length / 2;
        if (mid == 0) return;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        int x = maxSort(arr);
        System.out.println(x);
        if (x > max) {
            max = x;
            ans = new int[x];
            if (x >= 0) System.arraycopy(arr, 0, ans, 0, x);
        }
        maxOrdaring(left);
        maxOrdaring(right);

    }

    private static int maxSort(int[] left) {

        int i;
        for (i = 0; i < left.length - 1; i++) {
            if (left[i] > left[i + 1]) {

                return i + 1;
            }
        }

        return i + 1;
    }

}



/*
class Solution {
    public int maxSubArray(int[] nums) {
        int rst = nums[0];
        int preSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preSum = Math.max(nums[i], nums[i] + preSum);
            rst = Math.max(rst, preSum);
        }

        return rst;
    }
}*/

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner input = new Scanner(System.in);
        System.out.println(64 & 63);
        for (int i = 1; i < 100; i++) {
            System.out.println(i + "\t" + ((i & (i - 1))));

        }
    }
}


