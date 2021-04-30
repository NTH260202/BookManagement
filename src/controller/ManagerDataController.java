package controller;

import model.Book;
import model.Reader;

public class ManagerDataController {
    private Book books;
    private Reader readers;
    private int numOfBorrowed;
    private String state;
    private int totalOfBorrowed;

    public ManagerDataController() {

    }

    public ManagerDataController(Reader readers, Book books, 
                                    int numOfBorrowed, String state,
                                    int totalOfBorrowed) {
    this.readers = readers;
    this.books = books;
    this.numOfBorrowed = numOfBorrowed;
    this.state = state;
    this.totalOfBorrowed = totalOfBorrowed;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public Book getBooks() {
        return books;
    }

    public void setReaders(Reader readers) {
        this.readers = readers;
    }

    public Reader getReaders() {
        return readers;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setNumOfBorrowed(int numOfBorrowed) {
        this.numOfBorrowed = numOfBorrowed;
    }

    public int getNumOfBorrowed() {
        return numOfBorrowed;
    }
    
    public void setTotalOfBorrowed(int totalOfBorrowed) {
        this.totalOfBorrowed = totalOfBorrowed;
    }

    public int getTotalOfBorrowed() {
        return totalOfBorrowed;
    }

    @Override
    public String toString() {
        return "BookReaderManagement{" + 
                " reader ID: " + readers.getReaderId() +
                " readerName: " + readers.getFullName() + 
                " book ID: " + books.getBookId() +
                " bookName: " + books.getBookName() +
                " number of borrowed times: " + numOfBorrowed +
                " state: " + state + 
                " total of borrowed times: " + totalOfBorrowed + 
                "}";
    }
}
