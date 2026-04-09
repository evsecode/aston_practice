package lesson_9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task5 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> logins = new ArrayList<>();

    System.out.println("Введите логины (пустая строка — завершение ввода):");

    while (true) {
      String input = scanner.nextLine();
      if (input.isEmpty()) {
        break;
      }
      logins.add(input);
    }

    List<String> fLogins = logins.stream()
        .filter(login -> login.startsWith("f"))
        .collect(Collectors.toList());

    System.out.println("Логины, начинающиеся на 'f':");
    fLogins.forEach(System.out::println);
  }
}