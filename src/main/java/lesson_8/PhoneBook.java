package lesson_8;

import java.util.*;

public class PhoneBook {
    private final Map<String, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        phoneBook.putIfAbsent(lastName, new ArrayList<>());
        phoneBook.get(lastName).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, Collections.emptyList());
    }
}
