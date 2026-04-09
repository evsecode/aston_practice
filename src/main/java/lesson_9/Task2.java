package lesson_9;

import java.util.List;

public class Task2 {

  public static void main(String[] args) {
    List<String> collection = List.of("Highload", "High", "Load", "Highload");

    // Сколько раз встречается "High"
    long highCount = collection.stream().filter("High"::equals).count();
    System.out.println("2.1 Количество 'High': " + highCount);

    // Первый элемент (0 если пусто)
    Object first = collection.stream().findFirst().map(s -> (Object) s).orElse(0);
    System.out.println("2.2 Первый элемент: " + first);

    // Последний элемент (0 если пусто)
    Object last = collection.get(collection.size() - 1);
    System.out.println("2.3 Последний элемент: " + last);
  }
}