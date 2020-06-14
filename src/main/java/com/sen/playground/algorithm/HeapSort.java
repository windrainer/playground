package com.sen.playground.algorithm;
/*
a Big Root Heap is:
- a complete binary tree
- parent node is greater than or equals to left child node and right child (big heap)
- given index i, left child index is 2i+1, right child index is 2i+2, parent index (i-1)/2
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array ={1, 3, 4, 5, 20, 6, 9, 7, 8, 0};
        HeapSort heapSort=new HeapSort();
        heapSort.sort(array);
        System.out.print("排序后:\t");
        heapSort.printPart(array, 0, array.length-1);
    }

    public void sort(int[] array) {
        //build maxHeap，start from the first non-leaf node(array.length/2-1)
        for(int i=array.length/2-1; i>=0; i--) {
            maxHeap(array, array.length-1, i);
        }

        //sort
        int j = array.length-1;
        while(j>0) {
            swap(array, j, 0);
            j--;
            //To rebuild the heap, we must exclude the last element and make the
            //rest of the nodes a maxheap again.
            maxHeap(array, j,0);
        }
    }

    public void maxHeap(int[] array, int size, int index) {
        if(index<0)
            return;
        int left = getLeft(index);
        int right = getRight(index);
        int index_large = index;
        if(left <= size && array[left] > array[index]) {
            index_large = left;
        }

        if(right <= size && array[right] > array[index_large]) {
            index_large = right;
        }
        //Size value is fixed because we are recursively adjustng based on current heap structure.
        if(index_large != index) {
            swap(array, index_large, index);
            maxHeap(array, size, index_large);
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private int getLeft(int i) {
        return 2*i+1;
    }

    private int getRight(int i) {
        return 2*i+2;
    }

    private void printHeap(int[] array) {
        System.out.println("\nHeap:\t");
        for (int i=0; i<array.length; i++) {
           System.out.print("\t" + array[i]);
        }
    }

    // 打印序列
    public void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }

}
