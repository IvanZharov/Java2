package Lesson3;

import java.sql.Array;
import java.util.*;

public class Task2App {
    static Map<String, String> phoneBook = new HashMap<>();

    public static void main(String[] args) {
        add("123-32-56","Иванов");
        add("123-32-30","Петров");
        add("123-32-40","Иванов");
        add("123-32-70","Сидоров");
        add("123-32-68","Иванов");
        add("123-32-00","Иванов");

        get("Иванов");
    }

    private static void add(String number, String name) {
        phoneBook.put(number, name);
        System.out.println("Added");
    }

    private static void get(String name) {
        List<String> neededNumbers = new ArrayList<>();
        for (Map.Entry<String, String> pair : phoneBook.entrySet()) {
            String value = pair.getValue();
            if (value.equals(name)) {
                neededNumbers.add(pair.getKey());
            }
        }
        System.out.println(name + " имеет следующие телефонные номера: ");
        System.out.println(neededNumbers);
    }
}

