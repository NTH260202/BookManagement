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

public class BookDataController implements FileConnection<Book>{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    @Override
    public void openFileToWrite() {
        try {
            fileWriter = new FileWriter("BOOK.DAT", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public void write(Book book) {
        openFileToWrite();

        printWriter.println(book.getBookId() + "|" + book.getBookName() + "|" +
                            book.getAuthor() + "|" + book.getSpecialization() + "|" + 
                            book.getPublishYear() + "|" + book.getQuantity());

        closeFileAfterWrite();
    }

    @Override
    public ArrayList<Book> read() {
        openFileToRead();

        ArrayList<Book> books = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Book book = createBook(data);
            books.add(book);
        }

        closeFileAfterRead();
        return books;
    }

    @Override
    public Reader createReader(String data) {
        return null;
    }

    @Override
    public Management createManagement(String data, ArrayList<Book> books, ArrayList<Reader> readers) {
        return null;
    }

    @Override
    public Book createBook(String data) {
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

    @Override
    public void update(ArrayList<Book> t) {
        
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

    @Override
    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}