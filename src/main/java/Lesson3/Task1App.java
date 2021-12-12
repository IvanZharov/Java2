package Lesson3;

import java.util.*;

public class Task1App {
    public static void main(String[] args) {
        String[] basicWordsList = {
                "A", "B", "j", "D", "E",
                "F", "A", "C", "D", "E",
                "E", "A", "C", "A", "A"
        };
        findUnique(basicWordsList);

    }

    public static String[] findUnique(String[] basic) {
        String[] workList = new String[basic.length];
        for (int i = 0; i < basic.length; i++) {
            workList[i] = basic[i];
        }
        for (int i = 0; i < basic.length-1; i++) {
            for (int j = i + 1; j < basic.length; j++) {
                if (basic[i].equals(basic[j])) {
                    workList[j] = null;
                }
            }
        }
        System.out.println(Arrays.toString(workList));

        Map<String, Integer> uniqueWordsCounter = new HashMap<>();
        for (int i = 0; i < basic.length; i++) {
            if (workList[i] != null) {
                int counter = 0;
                for (int j = 0; j < basic.length; j++) {
                    if (basic[j].equals(workList[i])) {
                        counter += 1;
                    }
                }
                uniqueWordsCounter.put(workList[i], counter);
            }
        }
        for (Map.Entry<String, Integer> pair : uniqueWordsCounter.entrySet()) {
            String key = pair.getKey();
            int value = pair.getValue();
            System.out.println(key + " повторяется " + value + " раз.");
        }
        return workList;
    }
}
