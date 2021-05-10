package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.Reader;

public class ReaderDataController implements FileConnection<Reader> {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    List<Reader> readers = new ArrayList<>();

    @Override
    public void open(boolean readMode) {
        if (readMode) {
            try {
                File file = new File("READER.DAT");
    
                if(!file.exists()) {
                    file.createNewFile();
                }
                scanner = new Scanner(Paths.get("READER.DAT"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileWriter = new FileWriter("READER.DAT", true);
                bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void write(Reader reader) {
        boolean READMODE = false;

        open(READMODE);

        printWriter.println(reader.getReaderId() + "|" + reader.getFullName() + "|" +
                            reader.getAddress() + "|" + reader.getPhone());
        
        close(READMODE);
    }

    @Override
    public ArrayList<Reader> read() {
        boolean READMODE = true;
       
        open(READMODE);

        ArrayList<Reader> readers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Reader reader = create(data);
            readers.add(reader);
        }

        close(READMODE);
        return readers;
    }

    @Override
    public Reader create(String data) {
        String[] datas = data.split("\\|");
        Reader reader = new Reader();

        reader.setReaderId(Integer.parseInt(datas[0]));
        reader.setFullName(datas[1]);
        reader.setAddress(datas[2]);
        reader.setPhone(Integer.parseInt(datas[3]));
        
        return reader;
    }

    @Override
    public void update(List<Reader> readers) {

    }

    @Override
    public void close(boolean readMode) {
        if (readMode) {
            try {
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public List<Reader> getAll() {
        return readers;
    }

    @Override
    public Optional<Reader> get(int readerId) {
        this.readers = read();
        return readers.stream().filter(u -> u.getReaderId() == readerId).findFirst();
    }
}

