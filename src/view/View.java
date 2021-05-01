package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.BookDataController;
import controller.ManagerDataController;
import controller.ReaderDataController;
import model.Book;
import model.Management;
import model.Reader;

public class View {
    public static void view() {
        BookDataController bookController = new BookDataController();
        ReaderDataController readerController = new ReaderDataController();
        ManagerDataController managementController = new ManagerDataController();
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        boolean isBookChecked = false;
        boolean isReaderChecked = false;
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Reader> readers = new ArrayList<Reader>();
        ArrayList<Management> managements = new ArrayList<Management>();
        do {
            System.out.println("________________MENU_________________");
            System.out.println("1. Adding a new book to the Book list.");
            System.out.println("2. Showing the Book list.");
            System.out.println("3. Adding a new book to the Reader list.");
            System.out.println("4. Showing the Reader list.");
            System.out.println("5. Adding the borrowing information to the Management list");
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
            case 5:
                readers = readerController.readReaderFromFile();
                books = bookController.readBookFromFile();
                managements = managementController.readManagementFromFile();

                int readerId;
                int bookId;
                boolean isBorrowed = false;
                boolean isFull = false;

                do {
                    showReaderInfo(readers);
                    System.out.println("__________________________________");
                    System.out.println("Enter reader's ID (Enter 0 to exit): ");
                    readerId = scanner.nextInt();
                    if (readerId == 0) {
                        break;
                    }
                    isBorrowed = checkBorrowed(managements, readerId);
                    if (isBorrowed) {
                        break;
                    } else {
                        System.out.println("You have borrowed enough books!.");
                    }
                } while (true);

                do{
                    showBookInfo(books);
                    System.out.println("___________________________________");
                    System.out.println("Enter book's ID (Enter 0 to exit): ");
                    bookId = scanner.nextInt();
                    if(bookId == 0) {
                        break;
                    }
                    isFull = checkFull(managements, readerId, bookId);
                    if (isFull) {
                        System.out.println("Please borrow another book!.");
                    } else {
                        break;
                    }
                } while (true);

                int total = getTotal(managements, readerId, bookId);
                do {
                    System.out.println("Enter the number of books that you have borrowed (the max is 3 books) (borrowed: " + total + "): ");
                    int temp = scanner.nextInt();
                    if(temp + total >= 1 && temp + total <= 3) {
                        total = total + temp;
                        break;
                    } else {
                        System.out.println("Out of borrowing time is permitted. Please enter again!.");
                    }
                } while (true);
                scanner.nextLine();

                System.out.println("Enter the book state: ");
                String status = "";
                status = scanner.nextLine();

                Book currentBook = getBook(books, bookId);
                Reader currentReader = getReader(readers, readerId);
                Management management = new Management(currentBook, currentReader, total, status, 0);
                managements = utility.updateBRMInfo(managements, management);// update list Management
                managementController.updateBRMFile(managements);// update file MANAGEMENT.DAT

                showManagementInfo(managements);
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

    private static void showManagementInfo(ArrayList<Management> managements) {
        for(Management management: managements) {
            System.out.println(management);
        }
    }

    private static boolean checkBorrowed(ArrayList<Management> managements, int readerId) {
        int count = 0;
        for (ArrayList<Management> management: managements) {
            if(management.getReaders().getReaderId() == readerId) {
                count += management.getNumOfBorrowed();
            }
        }
        if (count == 15) {
            return false; // can't borrow
        }
        return true;
    }

    private static boolean checkFull(ArrayList<Management> managements, int readerId, int bookId) {
        int count = 0;
        for (ArrayList<Management> management: managements) {
            if (management.getReaders().getReaderId() == readerId && management.getBooks().getBookId == bookId && management.getNumOfBorrowed() == 3) {
                return true; // this book can't be borrowed
            }
        }
        return false;
    }

    private static int getTotal(ArrayList<Management> managements, int readerId, int bookId) {
        for (ArrayList<Management> management: managements) {
            if (management.getReaders().getReaderId() == readerId && management.getBooks().getBookId == bookId) {
                return management.getNumOfBorrowed();
            }
        }
        return 0;
    }

    private static Book getBook(ArrayList<Book> books, int bookId) {
        for(int i = 0; i < books.size();  i ++) {
            if(books.get(i).getBookId() == bookId) {
                return books.get(i);
            }
        }
        return null;
    }
   

    private static Reader getReader(ArrayList<Reader> readers, int readerId) {
        for(int i = 0; i < readers.size(); i ++) {
            if(readers.get(i).getReaderId() == readerId) {
                return readers.get(i);
            }
        }
        return null;
    }
}
