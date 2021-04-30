package model;

public class Book {
    public static int baseId = 100000;
    private int bookId;
    private String bookName;
    private String author;
    private String specialization;
    private int publishYear;
    private int quantity;

    public Book() {
        
    }

    public Book(int bookId) {
        this.bookId = bookId;
    }

    public Book(String bookName, int baseId, String author, 
                String specialization, int publishYear, 
                int quantity) {
        setBookId();
        this.bookName = bookName;
        this.author = author;
        this.specialization = specialization;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    public static int getBaseId() {
        return baseId;
    }

    public static void setBaseId(int baseId) {
        Book.baseId = baseId;
    }

    public void setBookId() {
        this.bookId = baseId++;
    }
    
    public void setBookId(int baseId) {
        bookId = baseId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Book{" + 
                " bookId: " + bookId +
                " bookName: " + bookName +
                " author: " + author +
                " specialization: " + specialization + 
                " publishYear: " + publishYear + 
                " quantity: " + quantity +
                "}";
    }
}