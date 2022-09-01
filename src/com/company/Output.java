package com.company;

import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Output {
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) & 1;
    }

    public static void main(String[] args) {
        for (List<Integer> i : findSubLists(new int[]{10, 2, 5, 6, 8, 77, 9, 4, 12}, 23))
            System.out.println(i);
    }


    public static List<List<Integer>> findSubLists(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        findSubLists(nums, ans, new LinkedList<>(), 0, 0, target);

        return ans;
    }
 public static void findSubLists(int[] nums, List<List<Integer>> ans, LinkedList<Integer> currList, int index, int currSum, int target) {
        if (index >= nums.length) {
            return;
        }
        currSum += nums[index];
        currList.add(nums[index]);
        if (currSum == target) {
            ans.add(currList);
        }
        LinkedList<Integer> tempList = new LinkedList<>(currList);
        findSubLists(nums, ans, new LinkedList<>(tempList), index + 1, currSum, target);
        tempList.removeLast();
        findSubLists(nums, ans, new LinkedList<>(tempList), index + 1, currSum - nums[index], target);

    }

    public ArrayList<Integer> countFreq(int[] a, int n) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> h = new HashMap<>();
        for (int i : a) {
            h.put(i, h.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> i : h.entrySet()) {
            list.add(i.getValue());
        }
        return list;
    }


}