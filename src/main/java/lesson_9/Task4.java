package lesson_9;

import java.util.List;
import java.util.stream.Collectors;

public class Task4 {

  public static void main(String[] args) {
    List<Student> students = List.of(
        new Student("Алексей", 20, Gender.MALE),
        new Student("Мария", 22, Gender.FEMALE),
        new Student("Дмитрий", 25, Gender.MALE),
        new Student("Анна", 19, Gender.FEMALE),
        new Student("Иван", 17, Gender.MALE),
        new Student("Сергей", 28, Gender.MALE),
        new Student("Елена", 23, Gender.FEMALE)
    );

    // Средний возраст студентов мужского пола
    double avgMaleAge = students.stream()
        .filter(s -> s.getGender() == Gender.MALE)
        .mapToInt(Student::getAge)
        .average()
        .orElse(0);
    System.out.printf("4.1 Средний возраст мужчин: %.2f%n", avgMaleAge);

    // Кому грозит повестка (мужчины от 18 до 27 включительно)
    List<Student> conscripts = students.stream()
        .filter(s -> s.getGender() == Gender.MALE)
        .filter(s -> s.getAge() >= 18 && s.getAge() <= 27)
        .collect(Collectors.toList());
    System.out.println("4.2 Кому грозит повестка:");
    conscripts.forEach(s -> System.out.println("   " + s));
  }

  enum Gender {MALE, FEMALE}

  static class Student {

    private final String name;
    private final int age;
    private final Gender gender;

    public Student(String name, int age, Gender gender) {
      this.name = name;
      this.age = age;
      this.gender = gender;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

    public Gender getGender() {
      return gender;
    }

    @Override
    public String toString() {
      return name + " (возраст: " + age + ", " + gender + ")";
    }
  }
}