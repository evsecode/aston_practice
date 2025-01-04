package lesson_5;

import lesson_5.animals.*;
import lesson_5.shapes.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TASK 1 - Animals ===");

        // Create a bowl with food
        Bowl bowl = new Bowl(30); // Initial amount of food: 30
        System.out.println("\nInitial Bowl State: " + bowl);

        // Create an array of cats
        Cat[] cats = {
                new Cat("Tom", 5), // Small appetite for testing
                new Cat("Mars", 10),
                new Cat("Garfield", 20)
        };

        // Cats try to eat
        System.out.println("\n--- Cats are trying to eat ---");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " is trying to eat...");
            cat.eat(bowl);
            System.out.println("Is " + cat.getName() + " full? " + (cat.isFull() ? "Yes" : "No"));
            System.out.println(bowl); // Current state of the bowl
        }

        // Add food to the bowl
        System.out.println("\n--- Adding more food to the bowl ---");
        bowl.addFood(20);
        System.out.println("Food added. Current Bowl State: " + bowl);

        // Cats try to eat again
        System.out.println("\n--- Cats are trying to eat again ---");
        for (Cat cat : cats) {
            if (!cat.isFull()) {
                System.out.println(cat.getName() + " is trying to eat again...");
                cat.eat(bowl);
                System.out.println("Is " + cat.getName() + " full now? " + (cat.isFull() ? "Yes" : "No"));
                System.out.println(bowl); // Current state of the bowl
            } else {
                System.out.println(cat.getName() + " is already full!");
            }
        }

        // Create dogs and test running and swimming
        System.out.println("\n--- Dogs ---");
        Dog[] dogs = {
                new Dog("Rex"),
                new Dog("Buddy")
        };

        for (Dog dog : dogs) {
            System.out.println("\n" + dog.getName() + "'s actions:");
            dog.run(400);  // Test running within limits
            dog.run(600);  // Test running beyond limits
            dog.swim(5);   // Test swimming within limits
            dog.swim(15);  // Test swimming beyond limits
        }

        // Display total count of animals, cats, and dogs
        System.out.println("\n--- Animal Count ---");
        System.out.println("Total Animals: " + Animal.getTotalAnimals());
        System.out.println("Total Cats: " + Cat.getTotalCats());
        System.out.println("Total Dogs: " + Dog.getTotalDogs());

        System.out.println("\n=== TASK 2 - Shapes ===");

        // Create shapes
        Shape circle = new Circle(5, "Red", "Blue");
        Shape rectangle = new Rectangle(10, 5, "Green", "Yellow");
        Shape triangle = new Triangle(3, 4, 5, "White", "Black");

        // Print shape characteristics
        System.out.println("\n--- Circle Characteristics ---");
        circle.printCharacteristics();

        System.out.println("\n--- Rectangle Characteristics ---");
        rectangle.printCharacteristics();

        System.out.println("\n--- Triangle Characteristics ---");
        triangle.printCharacteristics();
    }
}