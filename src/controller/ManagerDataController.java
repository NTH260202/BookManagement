package controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import controller.BookDataController;
import controller.ReaderDataController;
import model.Book;
import model.Management;
import model.Reader;

public class ManagerDataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    BookDataController bookController = new BookDataController();
    ReaderDataController readerController = new ReaderDataController();

    public void openFileToRead() {
        try {
            File file = new File("MANAGEMENT.DAT");
            if(!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get("MANAGEMENT.DAT"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Management> readManagementFromFile() {
        ArrayList<Book> books = bookController.readBookFromFile();
        ArrayList<Reader> readers = readerController.readReaderFromFile();

        openFileToRead();

        ArrayList<Management> managements = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Management management = createManagementFromData(data, readers, books);
            managements.add(management);
        }

        closeFileAfterRead();
        return managements;
    }

    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
