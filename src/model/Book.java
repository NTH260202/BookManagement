package model;

public class Book {

    public static int id = 100000;
    private int bookID;
    private String bookName;
    private String author;
    private String specialization;
    private int publishYear;
    private int quantity;

    public Book(int bookID) {
        this.bookID = bookID;
    }

    public Book() {

    }

    public Book(int bookID, String bookName, String author, String specialization, int publishYear, int quantity) {
        setBookID();
        this.bookName = bookName;
        this.author = author;
        this.specialization = specialization;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    public static int getID() {
        return id;
    }

    public static void setID(int id) {
        Book.id = id;
    }

    public void setBookID() {
        this.bookID = id++;
    }

    public void setBookID(int id) {
        bookID = id;
    }

    public int getBookID() {
        return bookID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
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
                " bookID: " + bookID +
                " bookName: " + bookName +
                " author: " + author +
                " specialization: " + specialization + 
                " publishYear: " + publishYear + 
                " quantity: " + quantity +
                "}";
    }
}