package lesson_5.animals;

public class Dog extends Animal {
    private static int totalDogs = 0;

    public Dog(String name) {
        super(name, 500, 10);
        totalDogs++;
    }

    public static int getTotalDogs() {
        return totalDogs;
    }
}
