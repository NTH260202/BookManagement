package controller;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll();
    Optional<T> get(int id);
    void write(T t);
    List<T> read();
    void update(List<T> t);
   
    

}
