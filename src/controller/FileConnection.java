package controller;

public interface FileConnection<T> extends DAO<T>{
    void openFileToRead();
    void openFileToWrite();
    void closeFileAfterRead();
    void closeFileAfterWrite();
}
