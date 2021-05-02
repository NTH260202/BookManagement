package controller;

import java.util.ArrayList;

import model.Book;
import model.Management;
import model.Reader;

public interface DAO<T> {
    void write(T t);
    ArrayList<T> read();
    void update(ArrayList<T> t);
    Reader createReader(String data);
    Book createBook(String data);
    Management createManagement(String data, ArrayList<Book> books, ArrayList<Reader> readers);
    

}
