package Lesson2;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int a, int b) {
        super("Ошибка преобразования строки в число в ячейке: " + a + " " + b);
    }
}
