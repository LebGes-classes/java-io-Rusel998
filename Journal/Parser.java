package Journal;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Parser {
    private final String filename;
    private Product[] products;
    private int numberLines;

    public Parser(String filename) {
        this.filename = filename;
    }

    private void getNumberLines() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                count++;
            }
            numberLines = count;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputData() {
        getNumberLines();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            String[] data;
            products = new Product[numberLines];
            int count = 0;
            boolean cond = true;
            while ((line = reader.readLine()) != null) {
                if (cond || line.isEmpty()) {
                    cond = false;
                    continue;
                }
                data = line.split(";");
                if (data.length < 7) {
                    // Пропускаем строки с неправильным форматом данных
                    continue;
                }
                Product product = new Product(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[6]);
                products[count] = product;
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Product[] getProducts() {
        return products;
    }
}