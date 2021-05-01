package model;

public class Management {
    private Book books;
    private Reader readers;
    private int numOfBorrowed;
    private String state;
    private int numOfTotalBorrowed;

    public Management() {
        
    }

    public Management(Book books, Reader readers, int numOfBorrowed, String state, int numOfTotalBorrowed) {
        this.books = books;
        this.readers = readers;
        this.numOfBorrowed = numOfBorrowed;
        this.state = state;
        this.numOfTotalBorrowed = numOfTotalBorrowed;
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

    public void setNumOfBorrowed(int numOfBorrowed) {
        this.numOfBorrowed = numOfBorrowed;
    }

    public int getNumOfBorrowed() {
        return numOfBorrowed;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public void setNumOfTotalBorrowed(int numOfTotalBorrowed) {
        this.numOfTotalBorrowed = numOfTotalBorrowed;
    }

    public int getNumOfTotalBorrowed() {
        return numOfTotalBorrowed;
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
                " total of borrowed times: " + numOfTotalBorrowed + 
                "}";
    }
}
