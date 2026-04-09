package lesson_10;

public class Main {

  public static void main(String[] args) {
    // Создаём коробки
    Box<Apple> appleBox1 = new Box<>();
    Box<Apple> appleBox2 = new Box<>();
    Box<Orange> orangeBox = new Box<>();

    // Наполняем коробки
    appleBox1.addFruit(new Apple());
    appleBox1.addFruit(new Apple());
    appleBox1.addFruit(new Apple()); // 3 яблока = 3.0

    appleBox2.addFruit(new Apple());
    appleBox2.addFruit(new Apple());
    appleBox2.addFruit(new Apple()); // 3 яблока = 3.0

    orangeBox.addFruit(new Orange());
    orangeBox.addFruit(new Orange()); // 2 апельсина = 3.0

    System.out.println("Начальное состояние");
    System.out.println("appleBox1:  " + appleBox1);
    System.out.println("appleBox2:  " + appleBox2);
    System.out.println("orangeBox:  " + orangeBox);

    // Сравнение коробок
    System.out.println("\nСравнение весов");
    System.out.println(
        "appleBox1 == appleBox2: " + appleBox1.compare(appleBox2)); // true (3.0 == 3.0)
    System.out.println(
        "appleBox1 == orangeBox: " + appleBox1.compare(orangeBox)); // true (3.0 == 3.0)
    orangeBox.addFruit(new Orange()); // добавим ещё один апельсин
    System.out.println("appleBox1 == orangeBox (после добавления): " + appleBox1.compare(
        orangeBox)); // false (3.0 != 4.5)

    //  Пересыпание фруктов
    System.out.println("\nПересыпание appleBox1 -> appleBox2");
    System.out.println("До: appleBox1=" + appleBox1.getFruitCount() + " шт., appleBox2="
        + appleBox2.getFruitCount() + " шт.");
    appleBox1.transferTo(appleBox2);
    System.out.println("После: appleBox1=" + appleBox1.getFruitCount() + " шт., appleBox2="
        + appleBox2.getFruitCount() + " шт.");
    System.out.println("appleBox1: " + appleBox1);
    System.out.println("appleBox2: " + appleBox2);
  }
}