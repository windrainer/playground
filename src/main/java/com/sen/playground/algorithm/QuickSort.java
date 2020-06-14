package com.sen.playground.algorithm;


import static com.sen.playground.utils.Utils.printArray;
import static com.sen.playground.utils.Utils.swap;

/**
 * Created by gaosen1 on 2017/11/21.
 * O(nlogn)
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array={2,8,7,1,3,5,6,4,1078,2065,-2};
        quicksort(array,0,array.length-1);

        printArray(array);
    }

    public static void quicksort(int[] array, int left, int right) {
        if(left < right) {
            int pivot = partition(array, left, right);

            quicksort(array,left,pivot-1);

            quicksort(array,pivot+1,right);
        }
    }

    public static int partition(int[] array, int left, int right) {
        int x = array[right]; //pivot
        int i = left-1; //init a <= area
        for(int j=left;j<right; j++) {
            if(array[j] <= x) {
                i++;
                swap(array, i, j); // <= area expands to the right by 1 element, and swap with [j]
            }
        }
        i++;
        swap(array, i, right);
        return i;
    }
}
