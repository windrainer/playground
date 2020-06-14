package com.sen.playground.strings;

/**
 * Let's compress a string according to how many times a particular letter appears. If the compression doesn't make the string
 * shorter, then we return the original string. For example, aabccccaa , after compression it becomes a2b1c4a2
 */
public class StringCompress {
    public static void main(String[] args) {
        String str = "aabccccaa";
        str = doCompression(str);
        System.out.println(str);
    }

    public static int countCompression(String str) {
        if(null==str || 0==str.length())
            return 0;
        int size=0, count=1; char current=str.charAt(0);

        for(int i=1; i<str.length(); i++) {
            if(current==str.charAt(i)) {
                count++;
            } else {
                size += 1 + String.valueOf(count).length();
                current=str.charAt(i);
                count=1;
            }
        }

        size += 1 + String.valueOf(count).length();
        return size;
    }

    public static String  doCompression(String str) {
        int size = countCompression(str);
        if(size >= str.length()) {
            return str;
        }

        int index = 0;
        char[] array = new char[size];
        int count =1;
        char current=str.charAt(0);

        for(int i=1; i<str.length(); i++) {
            if (current == str.charAt(i)) {
                count++;
            } else {
                array[index++] = current;
                char[] countCharArray = String.valueOf(count).toCharArray();
                for (int j = 0; j < countCharArray.length; j++) {
                    array[index + j] = countCharArray[j];
                }
                index++;
                count = 1;
                current = str.charAt(i);
            }
        }

        array[index++] = current;
        char[] countCharArray = String.valueOf(count).toCharArray();
        for (int j = 0; j < countCharArray.length; j++) {
            array[index + j] = countCharArray[j];
        }

        return String.valueOf(array);

    }


}
