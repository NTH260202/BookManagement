package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void openFileToWrite() {
        try {
            fileWriter = new FileWriter("MANAGEMENT.DAT", true);
            bufferedWriter = new BufferedWriter((fileWriter));
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeManagementToFile(Management management) {
        openFileToWrite();

        printWriter.println(management.getReaders().getReaderId() + "|" + management.getBooks().getBookId() + "|" + 
                            management.getNumOfBorrowed() + "|" + management.getState());
    
        closeFileAfterWrite();    
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

    public Management createManagementFromData(String data, ArrayList<Reader> readers, ArrayList<Book> books) {
        String[] datas = data.split("\\|");
        Management management = new Management();
        management.setReaders(getReader(readers, Integer.parseInt(datas[0])));
        management.setBooks(getBook(books, Integer.parseInt(datas[1])));
        management.setNumOfBorrowed(Integer.parseInt(datas[2]));
        management.setState(datas[3]);
        management.setNumOfTotalBorrowed(0);
        return management;
    }

    public void updateManagementFile(ArrayList<Management> managements) {
        File file = new File("MANAGEMENT.DAT");
        if (file.exists()) {
            file.delete();
        }

        openFileToWrite();

        for (Management management: managements) {
        printWriter.println(management.getReaders().getReaderId() + "|" + management.getBooks().getBookId() + "|" + 
                            management.getNumOfBorrowed() + "|" + management.getState());
        }

        closeFileAfterWrite();
    }

    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Book getBook(ArrayList<Book> books, int bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == bookId) {
                return books.get(i);
            }
        }
        return null;
    }

    private static Reader getReader(ArrayList<Reader> readers, int readerId) {
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i).getReaderId() == readerId) {
                return readers.get(i);
            }
        }
        return null;
    }
}
