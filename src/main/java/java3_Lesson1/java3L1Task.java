package java3_Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class java3L1Task {
    public static void main(String[] args) {

    }

    class Task1 {
        <T> void swapElements(T[] arr, int firstEl, int secondEl) {
            T tempValue = arr[firstEl];
            arr[firstEl] = arr[secondEl];
            arr[secondEl] = tempValue;
        }
    }

    static class Task2 {
        static<F> List<F> massiveToArrayList(F[] arr) {
            return new ArrayList<>(Arrays.asList(arr));
        }
    }

    class Task3 {

    }

}
