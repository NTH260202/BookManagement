package model;

public class Book extends Person{
    private String author;
    private int specialization;
    private int publishYear;
    private int quantity;

    public Book(String name, int id, String author, int specialization, 
                int publishYear, int quantity) {
        super(name, id);
        this.author = author;
        this.specialization = specialization;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setSpecialization(int specialization) {
        this.specialization = specialization;
    }

    public int getSpecialization() {
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
}