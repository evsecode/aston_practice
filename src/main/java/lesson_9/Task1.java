package lesson_9;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Task1 {

  public static void main(String[] args) {
    Random random = new Random();
    List<Integer> numbers = random.ints(20, -100, 101)
        .boxed()
        .collect(Collectors.toList());

    System.out.println("Числа: " + numbers);

    long evenCount = numbers.stream()
        .filter(n -> n % 2 == 0)
        .count();

    System.out.println("Количество чётных чисел: " + evenCount);
  }
}