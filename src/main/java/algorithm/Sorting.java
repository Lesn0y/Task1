package algorithm;

import java.util.Arrays;

public class Sorting {

    public static <T extends Comparable<T>> void bubble(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void selection(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMax = array.length - i - 1;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[iMax]) > 0) {
                    iMax = j;
                }
            }
            if (iMax != array.length - i - 1) {
                T temp = array[iMax];
                array[iMax] = array[array.length - i - 1];
                array[array.length - i - 1] = temp;
            }
        }
    }

    public static <T extends Comparable<T>> void insertion(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int iSorted = i - 1;
            while (iSorted > -1 && (array[iSorted].compareTo(array[iSorted + 1]) > 0)) {
                T temp = array[iSorted];
                array[iSorted] = array[iSorted + 1];
                array[iSorted + 1] = temp;
                iSorted--;
            }
        }
    }

    public static int[] merge(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] right = Arrays.copyOfRange(array, left.length, array.length);
        return mergeLaR(merge(left), merge(right));
    }

    private static int[] mergeLaR(int[] left, int[] right) {
        int resInd = 0, leftInd = 0, rightInd = 0;
        int[] result = new int[left.length + right.length];

        while (leftInd < left.length && rightInd < right.length) {
            if (left[leftInd] <= right[rightInd]) {
                result[resInd++] = left[leftInd++];
            } else {
                result[resInd++] = right[rightInd++];
            }
        }

        while (resInd < result.length) {
            if (leftInd < left.length) {
                result[resInd++] = left[leftInd++];
            } else {
                result[resInd++] = right[rightInd++];
            }
        }
        return result;
    }
}
