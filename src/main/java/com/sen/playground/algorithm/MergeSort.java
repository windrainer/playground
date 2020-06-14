package com.sen.playground.algorithm;

import static com.sen.playground.utils.Utils.printArray;

/**
 * Created by gaosen1 on 2017/11/20.
 */
public class MergeSort {

    public static void main(String[] args) {
        int i;
        int a[] = {80,30,60,40,20,10,50,70, -2, 1098, Integer.MAX_VALUE, Integer.MIN_VALUE};

        System.out.printf("before sort:");
        printArray(a);
        System.out.printf("\n");

        mergeSort(a, 0, a.length - 1);

        System.out.printf("after  sort:");
        printArray(a);
    }

    /**
     * merge two sorted array
     * @param a
     * @param start
     * @param mid
     * @param end
     */
    public static void merge(int[] a, int start, int mid, int end) {
        int[] tmp = new int[end-start+1];    // tmp是汇总2个有序区的临时区域
        int i = start;            // 第1个有序区的索引
        int j = mid + 1;        // 第2个有序区的索引
        int k = 0;                // 临时区域的索引

        while(i <= mid && j <= end) {
            if (a[i] <= a[j])
                tmp[k++] = a[i++];
            else
                tmp[k++] = a[j++];
        }

        while(i <= mid)
            tmp[k++] = a[i++];

        while(j <= end)
            tmp[k++] = a[j++];

        // 将排序后的元素，全部都整合到数组a中。
        for (i = 0; i < k; i++)
            a[start + i] = tmp[i];

        tmp=null;
    }

    public static void mergeSort(int[] array, int start, int end) {
        if(array==null || start >= end)
            return;

        int mid = start + ((end -start) >> 1);
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);

        merge(array,start,mid,end);
    }
}
