package lesson_2;

import java.util.Arrays;

public class task_2 {
//1
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }
//2
    public static void checkSumSign() {
        int a = 6, b = -2;
        System.out.println((a + b >= 0) ? "Сумма положительная" : "Сумма отрицательная");
    }
//3
    public static void printColor() {
        int value = 50;
        System.out.println(value <= 0 ? "Красный" : value <= 100 ? "Желтый" : "Зеленый");
    }
//4
    public static void compareNumbers() {
        int a = 3, b = 7;
        System.out.println(a >= b ? "a >= b" : "a < b");
    }
//5
    public static boolean isSumWithinRange(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }
//6
    public static void printNumberSign(int number) {
        System.out.println(number >= 0 ? "Число положительное" : "Число отрицательное");
    }
//7
    public static boolean isNegative(int number) {
        return number < 0;
    }
//8
    public static void printStringNTimes(String str, int times) {
        System.out.println(str.repeat(times));
    }
//9
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
//10
    public static void flipArray() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 - array[i];
        }
        System.out.println(Arrays.toString(array));
    }
//11
    public static void fillArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
    }
//12
    public static void modifyArray() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) array[i] *= 2;
        }
        System.out.println(Arrays.toString(array));
    }
//13
    public static void fillDiagonals(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
//14
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }

    public static void execute() {
        String red = "\033[31m";
        String reset = "\033[0m";

        System.out.println(red + "1." + reset);
        printThreeWords();

        System.out.println(red + "2." + reset);
        checkSumSign();

        System.out.println(red + "3." + reset);
        printColor();

        System.out.println(red + "4." + reset);
        compareNumbers();

        System.out.println(red + "5." + reset);
        System.out.println(isSumWithinRange(10, 5));

        System.out.println(red + "6." + reset);
        printNumberSign(0);

        System.out.println(red + "7." + reset);
        System.out.println(isNegative(-1));

        System.out.println(red + "8." + reset);
        printStringNTimes("Hello, Java!", 3);

        System.out.println(red + "9." + reset);
        System.out.println(isLeapYear(2024));

        System.out.println(red + "10." + reset);
        flipArray();

        System.out.println(red + "11." + reset);
        fillArray();

        System.out.println(red + "12." + reset);
        modifyArray();

        System.out.println(red + "13." + reset);
        fillDiagonals(5);

        System.out.println(red + "14." + reset);
        int[] array = createArray(10, 5);
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
