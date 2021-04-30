package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import model.Reader;

public class ReaderDataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToWrite() {
        try {
            fileWriter = new FileWriter("READER.DAT", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFileToRead() {
        try {
            File file = new File("READER.DAT");
            if(!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get("READER.DAT"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeReaderToFile(Reader reader) {
        openFileToWrite();

        printWriter.println(reader.getReaderId() + "|" + reader.getFullName() + "|" +
                            reader.getAddress() + "|" + reader.getPhone());
        
        closeFileAfterWrite();
    }

    public ArrayList<Reader> readReaderFromFile() {
        openFileToRead();

        ArrayList<Reader> readers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Reader reader = createReaderFromData(data);
            readers.add(reader);
        }

        closeFileAfterRead();
        return readers;
    }

    public Reader createReaderFromData(String data) {
        String[] datas = data.split("\\|");
        Reader reader = new Reader();

        reader.setReaderId(Integer.parseInt(datas[0]));
        reader.setFullName(datas[1]);
        reader.setAddress(datas[2]);
        reader.setPhone(Integer.parseInt(datas[3]));
        return reader;
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

    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


