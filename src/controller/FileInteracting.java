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
import model.BookReaderManagement;
import model.Reader;

public class FileInteracting {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToRead(String fileName) {
        try {
            File file = new File(fileName);
            if(!file.exists()) { 
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reader> readReaderFromFile(String fileName) {
        openFileToRead(fileName);
        ArrayList<Reader> readers = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Reader reader = createReaderFromData(data);
            readers.add(reader);
        }

        closeFileAfterRead(fileName);
        return readers;
    }

    public Reader createReaderFromData(String data) {
        String[] datas = data.split("\\|");
        //printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
        //Reader(int readerID, String fullName, String address, int phoneNumber)
        Reader reader = new Reader();
        reader.setReaderID(Integer.parseInt(datas[0]));
        reader.setFullName(datas[1]);
        reader.setAddress(datas[2]);
        reader.setPhoneNumber(Integer.parseInt(datas[3]));
        return reader;
    }

    public ArrayList<Book> readBookFromFile(String fileName) {
        openFileToRead(fileName);
        ArrayList<Book> books = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            books.add(book);
        }

        closeFileAfterRead(fileName);
        return books;
    }

    public ArrayList<BookReaderManagement> readBRMFromFile(String fileName) {
        var books = readBookFromFile("BOOK.DAT");
        var readers = readReaderFromFile("READER.DAT");

        openFileToRead(fileName);
        ArrayList<BookReaderManagement> brms = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            BookReaderManagement brm = createBRMFromData(data, readers, books);
            brms.add(brm);
        }

        closeFileAfterRead(fileName);
        return brms;
    }

    public BookReaderManagement createBRMFromData(String data, ArrayList<Reader> readers, ArrayList<Book> books) {
        String[] datas = data.split("\\|");
        //printWriter.println(brm.getReaders().getReaderID() + "|" + brm.getBooks().getBookID() + "|" + brm.getNumOfBorrowed() + "|" + brm.getState());
        //public BookReaderManagement(Reader readers, Book books, int numOfBorrowed, String state, int totalOfBorrewed) {
        BookReaderManagement brm = new BookReaderManagement();
        brm.setBooks(getBook(books, Integer.parseInt(datas[1])));
        brm.setReaders(getReader(readers, Integer.parseInt(datas[0])));
        brm.setNumOfBorrowed(Integer.parseInt(datas[3]));
        brm.setState(datas[4]);
        brm.setTotalOfBorrowed(0);
        return brm;
    }

    public Book createBookFromData(String data) {
        String[] datas = data.split("\\|");
        //println(book.getBookID() + "|" + book.getBookName() + "|"
        //+ book.getAuthor() + "|" + book.getSpecialization() + "|" + book.getPublishYear() + "|" + book.getQuantity())
        //Book(int bookID, String bookName, String author, String specialization, int publishYear, int quantity)
        Book book = new Book();
        book.setBookID(Integer.parseInt(datas[0]));
        book.setBookName(datas[1]);
        book.setAuthor(datas[2]);
        book.setSpecialization(datas[3]);
        book.setPublishYear(Integer.parseInt(datas[4]));
        book.setQuantity(Integer.parseInt(datas[5]));
        return book;
    }

    public void closeFileAfterRead(String fileName) {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter((fileWriter));
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBookToFile(Book book, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(book.getBookID() + "|" + book.getBookName() + "|"
                            + book.getAuthor() + "|" + book.getSpecialization() + "|" + book.getPublishYear() + "|" + book.getQuantity());
        closeFileAfterWrite(fileName);
    }

    public void writeReaderToFile(Reader reader, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
        closeFileAfterWrite(fileName);    
    }

    public void writeBRMToFile(BookReaderManagement brm, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(brm.getReaders().getReaderID() + "|" + brm.getBooks().getBookID() + "|" + brm.getNumOfBorrowed() + "|" + brm.getState());
        closeFileAfterWrite(fileName);    
    }

    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBRMFile(ArrayList<BookReaderManagement> list, String fileName) {
        File file = new File(fileName);
        if(file.exists()) {
            file.delete();
        }

        openFileToWrite((fileName));
        for(var v: list) {
            printWriter.println(v.getReaders().getReaderID() + "|" + v.getBooks().getBookID() + "|" + v.getNumOfBorrowed() + "|" + v.getState());
        closeFileAfterWrite(fileName);
        }
    }

    private static Book getBook(ArrayList<Book> books, int bookID) {
        for(int i = 0; i < books.size();  i ++) {
            if(books.get(i).getBookID() == bookID) {
                return books.get(i);
            }
        }
        return null;
    }
   

    private static Reader getReader(ArrayList<Reader> readers, int readerID) {
        for(int i = 0; i < readers.size(); i ++) {
            if(readers.get(i).getReaderID() == readerID) {
                return readers.get(i);
            }
        }
        return null;
    }
}
