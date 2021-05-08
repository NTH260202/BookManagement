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

public class ManagerDataController implements FileConnection<Management> {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    BookDataController bookController = new BookDataController();
    ReaderDataController readerController = new ReaderDataController();

    @Override
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

    @Override
    public void openFileToWrite() {
        try {
            fileWriter = new FileWriter("MANAGEMENT.DAT", true);
            bufferedWriter = new BufferedWriter((fileWriter));
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Management management) {
        openFileToWrite();

        printWriter.println(management.getReaders().getReaderId() + "|" + management.getBooks().getBookId() + "|" + 
                            management.getNumOfBorrowed() + "|" + management.getState());
    
        closeFileAfterWrite();    
    }

    @Override
    public ArrayList<Management> read() {
        ArrayList<Book> books = bookController.read();
        ArrayList<Reader> readers = readerController.read();

        openFileToRead();

        ArrayList<Management> managements = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Management management = createManagement(data, books, readers);
            managements.add(management);
        }

        closeFileAfterRead();
        return managements;
    }

    @Override
    public Book createBook(String data) {
        return null;
    }

    @Override
    public Reader createReader(String data) {
        return null;
    }

    @Override
    public Management createManagement(String data, ArrayList<Book> books, ArrayList<Reader> readers) {
        String[] datas = data.split("\\|");
        Management management = new Management();

        management.setReaders(getReader(readers, Integer.parseInt(datas[0])));
        management.setBooks(getBook(books, Integer.parseInt(datas[1])));
        management.setNumOfBorrowed(Integer.parseInt(datas[2]));
        management.setState(datas[3]);
        management.setNumOfTotalBorrowed(0);
        
        return management;
    }

    @Override
    public void update(ArrayList<Management> managements) {
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

    @Override
    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeFileAfterWrite() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Book getBook(ArrayList<Book> books, int bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == bookId) {
                return books.get(i);
            }
        }

        return null;
    }

    public static Reader getReader(ArrayList<Reader> readers, int readerId) {
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i).getReaderId() == readerId) {
                return readers.get(i);
            }
        }

        return null;
    }
}
