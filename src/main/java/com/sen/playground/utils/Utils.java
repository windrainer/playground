package com.sen.playground.utils;

public class Utils {

  public static void swap(int[] array, int left, int right) {
    int temp = array[left];
    array[left] = array[right];
    array[right] = temp;
  }

  public static void printArray(int[] array) {
    for(int i=0 ; i< array.length; i++ ) {
      System.out.print("\t"+array[i]);
    }

    System.out.println("");
  }
}
