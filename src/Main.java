public class Main {
    //Найти минимальный элемент в массиве, то есть его значение и индекс
    public static void main(String[] args) {
        int [] array = new int[] {95, 24, 73, 89, 32, 53, 16, 24, 57, 1, 74, 55, 36};

        for (int i = 0; i < array.length; i++) {
            System.out.println(arrayToString(array));//выводим массив
            int index = min(array, i); //находим минимальный элемент в массиве

            int tmp = array[i];
            array[i] = array[index];
            array[index] = tmp;//меняем местами первый элемент с минимальным
        }
    }

    private static int min(int[] array, int start) {
        //метод поиска минимума
        int min_index = start;
        int min = array[start];
        for (int i = start + 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                min_index = i;
            }
        }
        return min_index;
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