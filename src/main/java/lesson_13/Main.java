package lesson_13;

public class Main {
    public static void main(String[] args) {
        FactorialCalculator calculator = new FactorialCalculator();
        int number = 4;
        System.out.println("Factorial of " + number + " is " + calculator.calculate(number));
    }
}
