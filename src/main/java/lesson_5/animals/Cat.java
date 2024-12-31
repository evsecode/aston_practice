package lesson_5.animals;

public class Cat extends Animal {
    private static int totalCats = 0;
    private boolean isFull;

    public Cat(String name, int appetite) {
        super(name, 200, 0); // Cats can't swim
        this.appetite = appetite;
        this.isFull = false;
        totalCats++;
    }

    private int appetite; // Amount of food the cat eats

    public boolean isFull() {
        return isFull;
    }

    public void eat(Bowl bowl) {
        if (isFull) {
            System.out.println(name + " is already full.");
        } else if (bowl.getFood() >= appetite) {
            bowl.consumeFood(appetite);
            isFull = true;
            System.out.println(name + " ate " + appetite + " units of food and is now full.");
        } else {
            System.out.println("Not enough food in the bowl for " + name + ".");
        }
    }

    public static int getTotalCats() {
        return totalCats;
    }
}
