package algorithm;

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

}
