package lesson_8;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Task 1: Find and print unique words
        String[] words = {"apple", "banana", "apple", "orange", "banana", "grape", "peach", "pear", "grape", "mango", "kiwi", "kiwi"};

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<String> uniqueWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueWords.add(entry.getKey());
            }
        }

        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Word count for each word: " + wordCount);

        // Task 2: PhoneBook implementation
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Sergeev", "123-456");
        phoneBook.add("Sinitcin", "234-567");
        phoneBook.add("Cook", "789-012");
        phoneBook.add("Lazarev", "345-678");

        System.out.println("Phone number for Sergeev: " + phoneBook.get("Sergeev"));
        System.out.println("Phone number for Sinitcin: " + phoneBook.get("Sinitcin"));
        System.out.println("Phone number for unknown: " + phoneBook.get("Unknown"));
    }
}
