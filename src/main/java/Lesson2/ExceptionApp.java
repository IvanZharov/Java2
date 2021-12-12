package Lesson2;

public class ExceptionApp {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        String[][] array1 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"}
        };
        String[][] array2 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"1", "2", "3"},
                {"5", "6", "7", "8"}
        };
        String[][] array3 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"1", "2", "АШИПКА!!", "4"},
                {"5", "6", "7", "8"}
        };
        summStringArray(array1);
    }

    static int summStringArray(String array[][]) throws MyArraySizeException, MyArrayDataException {
        // можно проверку кол-ва столбцов вынести сюда, чтобы проверял 1 раз, а не 4
        int summ = 0;
        if (array.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException();
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int cell = Integer.parseInt(array[i][j]);
                    summ += cell;
                } catch (NumberFormatException ae) {
                    throw new MyArrayDataException(i + 1, j + 1);
                }
            }
        }
        System.out.println(summ);
        return summ;
    }
}
