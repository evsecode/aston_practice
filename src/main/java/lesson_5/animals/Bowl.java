package lesson_5.animals;

public class Bowl {
    private int food;

    public Bowl(int initialFood) {
        this.food = initialFood;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            this.food += amount;
        }
    }

    public boolean consumeFood(int amount) {
        if (food >= amount) {
            food -= amount;
            return true;
        }
        return false;
    }

    public int getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Bowl contains " + food + " units of food.";
    }
}
