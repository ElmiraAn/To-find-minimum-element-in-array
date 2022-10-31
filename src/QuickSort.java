import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        test1();

    }
    public static void quickSort(int[] arr, int from, int to){
        if (from<to){
            int divideIndex = part(arr, from, to);//номер разделения
            quickSort(arr, from, divideIndex-1); // сортировка для подмассивов
            quickSort(arr, divideIndex, to);
        }
    }

    private static int part(int[] arr, int from, int to) {
        int right_index = to;
        int left_index = from;
        int middle = arr[from+(to-from)/2]; // опорный элемент
        while (left_index<=right_index){
            while (arr[left_index]<middle){
                left_index++;
            }//находим элемент который больше опорного
            while (arr[right_index]>middle){
                right_index--;
            }//находим элемент который меньше опорного
            if (left_index<=right_index){ // меняем найденные элементы местами в методе swap
                swap(arr, right_index, left_index);
                left_index++;
                right_index--;
            }
        }
        return left_index;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2]=temp;
    }
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
    private static void printSortStep(int[] arr, int from, int to, int partitionIndex) {
        System.out.print(arrayToString(arr));
        System.out.print("\npartition at index: " + partitionIndex);
        System.out.print(", left: " + arrayToString(Arrays.copyOfRange(arr, from, partitionIndex)));
        System.out.println(", right: " + arrayToString(Arrays.copyOfRange(arr, partitionIndex, to + 1)) + "\n");
    }

    private static void bubbleSort(int[] arr) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    sorted = false;
                }
            }
        }
    }

    public static int[] mergeSort(int[] array) {
        int[] tmp;
        int[] currentSrc = array;
        int[] currentDest = new int[array.length];

        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                merge(currentSrc, i, currentSrc, i + size, currentDest, i, size);
            }

            tmp = currentSrc;
            currentSrc = currentDest;
            currentDest = tmp;

            size = size * 2;
        }
        return currentSrc;
    }

    private static void merge(int[] src1, int src1Start, int[] src2, int src2Start, int[] dest, int destStart, int size) {
        int index1 = src1Start;
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.length);
        int src2End = Math.min(src2Start + size, src2.length);

        int iterationCount = src1End - src1Start + src2End - src2Start;

        for (int i = destStart; i < destStart + iterationCount; i++) {
            if (index1 < src1End && (index2 >= src2End || src1[index1] < src2[index2])) {
                dest[i] = src1[index1];
                index1++;
            } else {
                dest[i] = src2[index2];
                index2++;
            }
        }
    }

    private static void test1() {
        int testLen = 100000;

        int[] arr1 = new int[testLen];
        int[] arr2 = new int[testLen];

        System.out.println("\n-----Случайный массив------");

        for (int i = 0; i < testLen; i++) {
            arr2[i] = arr1[i] = (int)Math.round(Math.random() * 100);
        }

        System.out.println("Быстрая сортировка:");
        measureTime(() -> quickSort(arr1, 0, testLen - 1));

        System.out.println("Сортировка пузырьком:");
        measureTime(() -> bubbleSort(arr2));
    }

    private static void test2() {
        int testLen = 100000000;

        int[] arr1 = new int[testLen];
        int[] arr2 = new int[testLen];

        System.out.println("\n-----Отсортированный массив------");

        for (int i = 0; i < testLen; i++) {
            arr2[i] = arr1[i] = i;
        }

        System.out.println("Быстрая сортировка:");
        measureTime(() -> quickSort(arr1, 0, testLen - 1));

        System.out.println("Сортировка пузырьком:");
        measureTime(() -> bubbleSort(arr2));

    }

    private static void measureTime(Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Затраченное время: " + elapsed + " ms");
    }

}
