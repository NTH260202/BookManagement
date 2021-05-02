package controller;

import java.util.ArrayList;

import model.Book;
import model.Management;
import model.Reader;

public interface DAO<T> {
    void write(T t);
    ArrayList<T> read();
    void update(ArrayList<T> t);
    Reader createReader(String t);
    Book createBook(String t);
    Management createManagement(String t, ArrayList<Book> t_2, ArrayList<Reader> t_3);
    

}
