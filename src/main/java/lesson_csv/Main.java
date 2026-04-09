package lesson_csv;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CsvService csvService = new CsvService();

        String[] header = {"id", "age", "score"};
        int[][] data = {
            {1, 25, 90},
            {2, 30, 85},
            {3, 22, 95}
        };
        AppData appData = new AppData(header, data);

        csvService.save("output.csv", appData);
        System.out.println("Файл сохранён.");

        AppData loaded = csvService.load("output.csv");
        System.out.println("Заголовок: " + String.join(", ", loaded.getHeader()));
        for (int[] row : loaded.getData()) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }
}