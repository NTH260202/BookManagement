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

public class BookDataController{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToWrite() {
        try {
            fileWriter = new FileWriter("BOOK.DAT", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFileToRead() {
        try {
            File file = new File("BOOK.DAT");
            if(!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get("BOOK.DAT"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeBookToFile(Book book) {
        openFileToWrite();

        printWriter.println(book.getBookId() + "|" + book.getBookName() + "|" +
                            book.getAuthor() + "|" + book.getSpecialization() + "|" + 
                            book.getPublishYear() + "|" + book.getQuantity());

        closeFileAfterWrite();
    }

    public ArrayList<Book> readBookFromFile() {
        openFileToRead();

        ArrayList<Book> books = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            books.add(book);
        }

        closeFileAfterRead();
        return books;
    }

    public Book createBookFromData(String data) {
        String[] datas = data.split("\\|");
        Book book = new Book();
        
        book.setBookId(Integer.parseInt(datas[0]));
        book.setBookName(datas[1]);
        book.setAuthor(datas[2]);
        book.setSpecialization(datas[3]);
        book.setPublishYear(Integer.parseInt(datas[4]));
        book.setQuantity(Integer.parseInt(datas[5]));
        return book;
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

    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}