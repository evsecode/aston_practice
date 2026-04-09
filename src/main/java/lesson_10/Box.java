package lesson_10;

import java.util.ArrayList;
import java.util.List;

class Box<T extends Fruit> {

  private final List<T> fruits = new ArrayList<>();

  // Добавление фрукта в коробку
  public void addFruit(T fruit) {
    fruits.add(fruit);
  }

  // Вес коробки = вес одного фрукта * количество
  public float getWeight() {
    if (fruits.isEmpty()) {
      return 0f;
    }
    return fruits.get(0).getWeight() * fruits.size();
  }

  // Сравнение коробок по весу
  public boolean compare(Box<?> other) {
    return Float.compare(this.getWeight(), other.getWeight()) == 0;
  }

  // Пересыпать фрукты из текущей коробки в другую
  public void transferTo(Box<T> target) {
    target.fruits.addAll(this.fruits);
    this.fruits.clear();
  }

  public int getFruitCount() {
    return fruits.size();
  }

  @Override
  public String toString() {
    return "Box{fruits=" + fruits + ", weight=" + getWeight() + "}";
  }
}