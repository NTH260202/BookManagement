package model;

public class Management {
    private Book books;
    private Reader readers;
    // private int bookId;
    // private int readerId;
    private int numOfBorrowed;
    private String state;
    private int numOfTotalBorrowed;

    public Management() {
        
    }

    public Management(Book books, Reader readers, int numOfBorrowed, 
                    String state, int numOfTotalBorrowed) {
        this.books = books;
        this.readers = readers;
        this.numOfBorrowed = numOfBorrowed;
        this.state = state;
        this.numOfTotalBorrowed = numOfTotalBorrowed;
    }
    // public Management(int bookId, int readerId, int numOfBorrowed, 
    //                 String state, int numOfTotalBorrowed) {
    //     this.bookId = bookId;
    //     this.readerId = readerId;
    //     this.numOfBorrowed = numOfBorrowed;
    //     this.state = state;
    //     this.numOfTotalBorrowed = numOfTotalBorrowed;
    // }

    public void setReaders(Reader readers) {
        this.readers = readers;
    }

    // public void setReaderId(int readerId) {
    //     this.readerId = readerId;
    // }

    public Reader getReaders() {
        return readers;
    }

    // public int getReaderId() {
    //     return readerId;
    // }

    public void setBooks(Book books) {
        this.books = books;
    }

    // public void setBookId(int bookId) {
    //     this.bookId = bookId;
    // }

    public Book getBooks() {
        return books;
    }

    // public int getBookId() {
    //     return bookId;
    // }

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
                " reader ID: " + getReaders().getReaderId() +
                " readerName: " + getReaders().getFullName() + 
                " book ID: " + getBooks().getBookId() +
                " bookName: " + getBooks().getBookName() +
                " number of borrowed times: " + numOfBorrowed +
                " state: " + state + 
                " total of borrowed times: " + numOfTotalBorrowed + 
                "}";
    }
}
