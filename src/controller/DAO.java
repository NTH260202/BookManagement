package controller;

import java.util.ArrayList;

public interface DAO<T> {
    void write(T t);
    ArrayList<T> read();
    void update(ArrayList<T> t);
    public interface create<T> {
        T createManagement(T t, ArrayList<T> t_1, ArrayList<T> t_2);
        T createBook(T t);
        T createReader(T t);
    }

}
