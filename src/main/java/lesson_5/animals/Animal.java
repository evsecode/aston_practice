package lesson_5.animals;

public abstract class Animal {
    private static int totalAnimals = 0;
    protected String name;
    protected int runLimit;
    protected int swimLimit;

    public Animal(String name, int runLimit, int swimLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        totalAnimals++;
    }

    public String getName() {
        return name;
    }

    public static int getTotalAnimals() {
        return totalAnimals;
    }

    public void run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " ran " + distance + " m.");
        } else {
            System.out.println(name + " couldn't run " + distance + " m.");
        }
    }

    public void swim(int distance) {
        if (swimLimit == 0) {
            System.out.println(name + " can't swim.");
        } else if (distance <= swimLimit) {
            System.out.println(name + " swam " + distance + " m.");
        } else {
            System.out.println(name + " couldn't swim " + distance + " m.");
        }
    }
}
