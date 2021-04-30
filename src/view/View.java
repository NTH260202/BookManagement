package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.BookDataController;
import controller.ReaderDataController;
import model.Book;
import model.Reader;

public class View {
    public static void view() {
        BookDataController bookController = new BookDataController();
        ReaderDataController readerController = new ReaderDataController();
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        boolean isBookChecked = false;
        boolean isReaderChecked = false;
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Reader> readers = new ArrayList<Reader>();
        do {
            System.out.println("MENU");
            System.out.println("1. Adding a new book to the list.");
            System.out.println("2. Showing the book list.");
            System.out.println("0.Exit");
            System.out.println("Your choice: ");
        
        choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 0:
                System.out.println("___________________________________");
                System.out.println("Thanks for using our application!");
                break;
            case 1:
                if (!isBookChecked) {
                    checkBookId(bookController);
                    isBookChecked = true;
                };

                String[] specs = {"Science", "Art", "Economic", "IT"};
                String bookName;
                String author;
                String spec;
                int year;
                int quan;
                int kindOfBook;

                System.out.println("Enter the book's name: ");
                bookName = scanner.nextLine();

                System.out.println("Enter the book's author: ");
                author = scanner.nextLine();

                do {
                    System.out.println("Enter the book's specialization: ");
                    System.out.println("1. Science\n2. Art\n3. Economic\n4. IT");
                    System.out.println("Enter your choice: ");
                    kindOfBook = scanner.nextInt();
                } while(kindOfBook < 1 || kindOfBook > 4);
                spec = specs[kindOfBook - 1];

                System.out.println("Enter the book's published year: ");
                year = scanner.nextInt();

                System.out.println("Enter the book's quantity: ");
                quan = scanner.nextInt();

                Book book = new Book(bookName, 0, author, spec, year, quan);
                bookController.writeBookToFile(book);
                break;
            case 2:
                books = bookController.readBookFromFile();
                showBookInfo(books);
                break;
            case 3:
                if (!isReaderChecked) {
                    checkReaderId(readerController);
                    isReaderChecked = true;
                }

                String readerName;
                String address;
                int phone;

                System.out.println("Enter the reader's name: ");
                readerName = scanner.nextLine();

                System.out.println("Enter the reader's address");
                address = scanner.nextLine();

                System.out.println("Enter the reader's phone");
                phone = scanner.nextInt();

                Reader reader = new Reader(0, readerName, address, phone);
                readerController.writeReaderToFile(reader);
                break;
            case 4:
                readers = readerController.readReaderFromFile();
                showReaderInfo(readers);
                break;
        } 
        } while (choice != 0);
        scanner.close();
    }

    private static void checkBookId(BookDataController bookController) {
        ArrayList<Book> listBooks = bookController.readBookFromFile();
        if (listBooks.size() == 0) {
            //do nothing
        } else {
            Book.setBaseId(listBooks.get(listBooks.size() - 1).getBookId() + 1);
        }
    }

    private static void checkReaderId(ReaderDataController readerController) {
        ArrayList<Reader> listReaders = readerController.readReaderFromFile();
        if (listReaders.size() == 0) {
            //do nothing
        } else {
            Reader.setBaseId(listReaders.get(listReaders.size() - 1).getReaderId() + 1);
        }
    }

    private static void showBookInfo(ArrayList<Book> books) {
        System.out.println("_____________Book Information in List____________");
        for (Book book: books) {
            System.out.println(book);
        }
    }

    private static void showReaderInfo(ArrayList<Reader> readers) {
        for (Reader reader: readers) {
            System.out.println(reader);
        }
    }
}
