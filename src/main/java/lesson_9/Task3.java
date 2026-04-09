package lesson_9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task3 {

  public static void main(String[] args) {
    List<String> collection = List.of("f10", "f15", "f2", "f4", "f4");

    String[] sorted = collection.stream()
        .sorted(Comparator
            .comparingInt(s -> Integer.parseInt(s.replaceAll("[^0-9]", "")))
        )
        .toArray(String[]::new);

    System.out.println("Отсортированный массив: " + Arrays.toString(sorted));
  }
}