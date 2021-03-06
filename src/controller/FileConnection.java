package controller;

public interface FileConnection<T> extends DAO<T> {
    void open(boolean readMode);
    T create(String data);
    void close(boolean readMode);
}
