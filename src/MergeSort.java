//Сортировка слиянием
//массив делится по одному элементу, затем сливается 1 в 2, 2 в 4, 4 в 8 и т.д.
public class MergeSort {

    public static void main(String[] params) {
        int[] array = new int[] {15,78,89,9,12,12546,8,879,16,54,1};
        System.out.println(arrayToString(array));
        array = mergeSort(array);
        System.out.println(arrayToString(array));
    }

    public static int[] mergeSort(int[] array) {
        //метод для замены исходных части массива на отсортированные
        int[] tmp; //вспомогательный массив для замены
        int[] currentSrc = array; //массив исходник
        int[] currentDest = new int[array.length];//массив приемник

        int size = 1; //в начале рассматриваем участки размером 1 элемент
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) { //увеличиваем размер участка 1,2,4,8,16,32 и т.д.
                merge(currentSrc, i, currentSrc, i + size, currentDest, i, size);//вызываем метод для участка
                //передаем исходный участок и участок-приемник, размер подмассива
            }

            tmp = currentSrc;
            currentSrc = currentDest;
            currentDest = tmp;
            //заменили исходник и приемник с отсортированной частью

            size = size * 2; // увеличиваем размер подмассива 1,2,4,8,16,32 и т.д.

            System.out.println(arrayToString(currentSrc));
        }
        return currentSrc; //передаем итоговый
    }

    private static void merge(int[] src1, int src1Start, int[] src2, int src2Start, int[] dest,
                              int destStart, int size) {
        int index1 = src1Start;//индекс первого подмассива
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.length);
        int src2End = Math.min(src2Start + size, src2.length);

        if (src1Start + size > src1.length) {
            for (int i = src1Start; i < src1End; i++) {
                dest[i] = src1[i];
            }
            return;
        }

        int iterationCount = src1End - src1Start + src2End - src2Start; //вычисляем размер итераций

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
}