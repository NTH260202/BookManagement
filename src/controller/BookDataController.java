package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.Book;

public class BookDataController implements FileConnection<Book>{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    List<Book> books = new ArrayList<>();

    @Override
    public void open(boolean readMode) {
        if (readMode) {
            try {
                File file = new File("BOOK.DAT");
    
                if(!file.exists()) {
                    file.createNewFile();
                }
                scanner = new Scanner(Paths.get("BOOK.DAT"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileWriter = new FileWriter("BOOK.DAT", true);
                bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void write(Book book) {
        boolean READMODE = false;
        open(READMODE);

        printWriter.println(book.getBookId() + "|" + book.getBookName() + "|" +
                            book.getAuthor() + "|" + book.getSpecialization() + "|" + 
                            book.getPublishYear() + "|" + book.getQuantity());

        close(READMODE);
    }

    @Override
    public ArrayList<Book> read() {
        boolean READMODE = true;
        open(READMODE);

        ArrayList<Book> books = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Book book = create(data);
            books.add(book);
        }

        close(READMODE);
        return books;
    }

    @Override
    public Book create(String data) {
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
    public void update(List<Book> t) {
        
    }

    @Override
    public void close(boolean readMode) {
        if (readMode) {
            try {
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Optional<Book> get(int bookId) {
        this.books = read();
        return books.stream().filter(u -> u.getBookId() == bookId).findFirst();
    }
    
}