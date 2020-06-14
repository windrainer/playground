package com.sen.playground.algorithm;

/**
 * Created by gaosen1 on 2017/11/21.
 * O(nlogn)
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array={2,8,7,1,3,5,6,4};
        quicksort(array,0,array.length-1);

        for(int i=0; i<array.length; i++) {
            System.out.print("\t"+array[i]);
        }

//        System.out.println();
//        String fullpath = QuickSort.class.getResource("").getPath();
//        System.out.println("fullpath"+fullpath);
//
//        String classloaderpath = QuickSort.class.getClassLoader().getResource("").getPath();
//        System.out.println("classloaderpath:"+ classloaderpath);
//
//        String rootpath = Paths.get(".").toUri().normalize().getPath();
//        System.out.println("rootpath:" + rootpath);

    }

//    public static int devide(int[] array, int left, int right) {
//
//        int pivot = array[left];
//        while(left<right) {
//            while (left < right && array[right] >= pivot) {
//                right--;
//            }
//
//            swap(array,right,left);
//
//            while (left < right && array[left] <= pivot) {
//                left++;
//            }
//
//            swap(array, left, right);
//        }
//        return left;
//    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void quicksort(int[] array, int left, int right) {
        if(left < right) {
            int pivot = partition(array, left, right);
            System.out.format("base = %d:\t", array[pivot]);
            printArray(array,left,right);

            quicksort(array,left,pivot-1);

            quicksort(array,pivot+1,right);
        }
    }

    public static void printArray(int[] array, int left, int right) {
        System.out.println("\tleft:"+left+"\tright:"+right);
        for(int i=left ; i<= right; i++ ) {
            System.out.print("\t"+array[i]);
        }

        System.out.println("");
    }

    public static int partition(int[] array, int left, int right) {
        int x = array[right]; //pivot
        int i = left-1; //slow pointer starts from left-1
        for(int j=left;j<right; j++) {
            if(array[j] <= x) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i+1, right);
        return i+1;
    }
}
