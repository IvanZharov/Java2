package java3_Lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class java3L1Task {
    public static void main(String[] args) {

    }

    class Task1 {
        void swapElements(Object[] arr, int firstEl, int secondEl) {
            Object tempValue = arr[firstEl];
            arr[firstEl] = arr[secondEl];
            arr[secondEl] = tempValue;
        }
    }

    static class Task2 {
        static<F> ArrayList<F> massiveToArrayList(F[] arr) {
            return new ArrayList<>(Arrays.asList(arr));
        }
    }

    class Task3 {

    }


}
