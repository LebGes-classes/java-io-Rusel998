package Journal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите путь до файла: ");
        Scanner scanner = new Scanner(System.in);
        String filepathToOpen = scanner.nextLine();
        Parser parser = new Parser(filepathToOpen);
        parser.inputData();
        Json json = new Json(parser.getProducts());
        System.out.print("Введите путь для сохранения json: ");
        String filepathToSave = scanner.nextLine();
        json.creatingJson(filepathToSave);
    }
}
