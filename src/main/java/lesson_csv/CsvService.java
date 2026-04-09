package lesson_csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvService {

  private static final String DELIMITER = ";";

  public void save(String filePath, AppData appData) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(filePath, false), StandardCharsets.UTF_8))) {

      writer.write(String.join(DELIMITER, appData.getHeader()));
      writer.newLine();

      for (int[] row : appData.getData()) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
          if (i > 0) {
            sb.append(DELIMITER);
          }
          sb.append(row[i]);
        }
        writer.write(sb.toString());
        writer.newLine();
      }
    }
  }

  public AppData load(String filePath) throws IOException {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

      String headerLine = reader.readLine();
      if (headerLine == null) {
        throw new IOException("Файл пуст или отсутствует строка заголовка.");
      }
      String[] header = headerLine.split(DELIMITER, -1);

      List<int[]> rows = new ArrayList<>();
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank()) {
          continue;
        }
        String[] parts = line.split(DELIMITER, -1);
        int[] row = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
          row[i] = Integer.parseInt(parts[i].trim());
        }
        rows.add(row);
      }

      int[][] data = rows.toArray(new int[0][]);
      return new AppData(header, data);
    }
  }
}