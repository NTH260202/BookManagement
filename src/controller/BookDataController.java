package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Book;

public class BookDataController{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToWrite() {
        try {
            fileWriter = new FileWriter("BOOK.DAT", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBookToFile(Book book) {
        openFileToWrite();
        printWriter.println(book.getId() + "|" + book.getName() + "|" +
                            book.getAuthor() + "|" + book.getSpecialization() + "|" + 
                            book.getPublishYear() + "|" + book.getQuantity());
        closeFileAfterWrite();
    }

    public void closeFileAfterWrite() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}