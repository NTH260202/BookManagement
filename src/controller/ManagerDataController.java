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

import model.Management;

public class ManagerDataController implements FileConnection<Management> {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    List<Management> managements = new ArrayList<>();

    @Override
    public void open(boolean readMode) {
        if (readMode) {
            try {
                File file = new File("MANAGEMENT.DAT");
    
                if(!file.exists()) {
                    file.createNewFile();
                }
                scanner = new Scanner(Paths.get("MANAGEMENT.DAT"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileWriter = new FileWriter("MANAGEMENT.DAT", true);
                bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void write(Management management) {
        boolean READMODE = false;

        open(READMODE);

        printWriter.println(management.getReaders().getReaderId() + "|" + management.getBooks().getBookId() + "|" + 
                            management.getNumOfBorrowed() + "|" + management.getState());
    
        close(READMODE);    
    }

    @Override
    public ArrayList<Management> read() {
        boolean READMODE = true;

        open(READMODE);

        ArrayList<Management> managements = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Management management = create(data);
            managements.add(management);
        }

        close(READMODE);
        return managements;
    }

    @Override
    public Management create(String data) {
        String[] datas = data.split("\\|");
        BookDataController bookDataController = new BookDataController();
        ReaderDataController readerDataController = new ReaderDataController();
        Management management = new Management();
        System.out.println(Integer.parseInt(datas[0]));

        management.setReaders(readerDataController.get(Integer.parseInt(datas[0])).get());
        management.setBooks(bookDataController.get(Integer.parseInt(datas[1])).get());
        management.setNumOfBorrowed(Integer.parseInt(datas[2]));
        management.setState(datas[3]);
        management.setNumOfTotalBorrowed(0);
        
        return management;
    }

    @Override
    public void update(List<Management> managements) {
        boolean READMODE = false;
        File file = new File("MANAGEMENT.DAT");
        if (file.exists()) {
            file.delete();
        }

        open(READMODE);

        for (Management management: managements) {
        printWriter.println(management.getReaders().getReaderId() + "|" + management.getBooks().getBookId() + "|" + 
                            management.getNumOfBorrowed() + "|" + management.getState());
        }

        close(READMODE);
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
    public List<Management> getAll() {
        return managements;
    }

    @Override
    public Optional<Management> get(int readerId) {
        return managements.stream().filter(u -> u.getReaders().getReaderId() == readerId).findFirst();
    }

    // //public static Book getBook(ArrayList<Book> books, int bookId) {
    //     for (int i = 0; i < books.size(); i++) {
    //         if (books.get(i).getBookId() == bookId) {
    //             return books.get(i);
    //         }
    //     }

    //     return null;
    // }

    // public static Reader getReader(ArrayList<Reader> readers, int readerId) {
    //     for (int i = 0; i < readers.size(); i++) {
    //         if(readers.get(i).getReaderId() == readerId) {
    //             return readers.get(i);
    //         }
    //     }

    //     return null;
    // }
}
