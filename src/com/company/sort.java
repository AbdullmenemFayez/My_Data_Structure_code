package com.company;

import java.util.*;

public class sort<T extends Comparable<T>> {
    public void Selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int ind = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[ind])
                    ind = j;
            a[i] += a[ind] - (a[ind] = a[i]);
        }


    }

    public void Bubble(int[] a) {
        boolean flag = false;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    a[j] += a[j + 1] - (a[j + 1] = a[j]);
                    flag = true;
                }
            }
            if (!flag) return;
        }
    }

    public void Insertion(int[] a) {
        int key, j;
        for (int i = 1; i < a.length; i++) {
            key = a[i];
            j = i - 1;
            while (j > -1 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    public void Merge(int[] a) {

        int mid = (a.length ) / 2;
        if (mid == 0) return;
        int[] L = Arrays.copyOfRange(a, 0, mid);
        int[] R = Arrays.copyOfRange(a, mid, a.length);
        Merge(L);
        Merge(R);
        merge(a, L, R);
    }

    public void merge(int[] a, int[] L, int[] R) {
        int i = 0, j = i, k = i;
        while (i < L.length && j < R.length) {
            if (L[i] < R[j]) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }
        while (i < L.length) {
            a[k++] = L[i++];
        }
        while (j < R.length) {
            a[k++] = R[j++];
        }
    }

    public void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(int[] a, int l, int h) {

        if (l < h) {
            int piv = helperQuickSort(a, l, h);
            quickSort(a, l, piv - 1);
            quickSort(a, piv + 1, h);
        }
    }

    private int helperQuickSort(int[] a, int low, int high) {
        int piv = low;//بحط مكان البيفوت الي بدي اياه
        int pivot = a[piv];
        a[piv] += a[high] - (a[high] = a[piv]);///swap
        int i = low;
        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                a[i] += a[j] - (a[j] = a[i]);//swap
                i++;
            }
        }
        a[i] += a[high] - (a[high] = a[i]);//swap
        return i;
    }

    private void heapIfy(int[] a, int n, int i) {
        int l = 2 * i + 1, r = l + 1;
        int max = i;
        if (l < n && a[l] > a[max]) max = l;
        if (r < n && a[r] > a[max]) max = r;
        if (max != i) {
            a[max] += a[i] - (a[i] = a[max]);
            heapIfy(a, n, max);
        }
    }

    public void buildHeap(int[] a) {
        for (int i = a.length / 2 - 1; i > -1; --i)
            heapIfy(a, a.length, i);
    }

    void heapSort(int[] a) {
        buildHeap(a);
        for (int i = a.length - 1; i > -1; i--) {
            a[0] += a[i] - (a[i] = a[0]);
            heapIfy(a, i, 0);
        }
    }

    public void bucketSort(float[] arr, int n) {
        if (n <= 0)
            return;

        @SuppressWarnings("unchecked")
        Vector<Float>[] buckets = new Vector[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<>();
        }

        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int) idx].add(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }

    public void binSort(int[] array, int bucketSize) {
        List<Integer>[] buckets = new List[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int num : array) {
            buckets[hash(num, bucketSize)].add(num);
        }
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                array[index++] = num;
            }
        }
    }

    private static int hash(int num, int bucketSize) {
        return num % bucketSize;
    }

}
