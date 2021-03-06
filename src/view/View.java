package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.BookDataController;
import controller.ManagerDataController;
import controller.ReaderDataController;
import controller.Utility;
import model.Book;
import model.Management;
import model.Reader;

public class View {
    public static void view() {
        Scanner scanner = new Scanner(System.in);
        BookDataController bookController = new BookDataController();
        ReaderDataController readerController = new ReaderDataController();
        ManagerDataController managementController = new ManagerDataController();
        Utility utility = new Utility();
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Reader> readers = new ArrayList<Reader>();
        ArrayList<Management> managements = new ArrayList<Management>();
        int choice = 0;
        boolean isBookChecked = false;
        boolean isReaderChecked = false;
       
        do {
            System.out.println("________________MENU_________________");
            System.out.println("1. Adding a new book to the Book list.");
            System.out.println("2. Showing the Book list.");
            System.out.println("3. Adding a new book to the Reader list.");
            System.out.println("4. Showing the Reader list.");
            System.out.println("5. Adding the borrowing information to the Management list");
            System.out.println("6. Sorting in the Management list");
            System.out.println("7. Searching information in the Management list by readers' names.");
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
                    String[] specs = {"Science", "Art", "Economic", "IT"};
                    String bookName;
                    String author;
                    String spec;
                    int year;
                    int quan;
                    int kindOfBook;

                    if (!isBookChecked) {
                        checkBookId(bookController);
                        isBookChecked = true;
                    };

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
                    bookController.write(book);
                    break;
                case 2:
                    books = bookController.read();
                    showBookInfo(books);
                    break;
                case 3:
                    String readerName;
                    String address;
                    int phone;

                    if (!isReaderChecked) {
                        checkReaderId(readerController);
                        isReaderChecked = true;
                    }

                    System.out.println("Enter the reader's name: ");
                    readerName = scanner.nextLine();

                    System.out.println("Enter the reader's address");
                    address = scanner.nextLine();

                    System.out.println("Enter the reader's phone");
                    phone = scanner.nextInt();

                    Reader reader = new Reader(0, readerName, address, phone);
                    readerController.write(reader);
                    break;
                case 4:
                    readers = readerController.read();
                    showReaderInfo(readers);
                    break;
                case 5:
                    int readerId;
                    int bookId;
                    boolean isBorrowed = false;
                    boolean isFull = false;

                    readers = readerController.read();
                    books = bookController.read();
                    managements = managementController.read();

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
                            System.out.println("Out of borrowing books is permitted. Please enter again!.");
                        }
                    } while (true);
                    scanner.nextLine();

                    System.out.println("Enter the book state: ");
                    String status = "";
                    status = scanner.nextLine();

                    Book currentBook = bookController.get(bookId).get();
                    Reader currentReader = readerController.get(readerId).get();
                    Management management = new Management(currentBook, currentReader, total, status, 0);
                    managements = utility.updateManagementInfo(managements, management);// update list Management
                    managementController.update(managements);// update file MANAGEMENT.DAT
                    showManagementInfo(managements);
                    break;
                case 6:
                    int choice_1 = 0;

                    managements = managementController.read();
                    managements = utility.updateTotalBorrowed(managements);

                    System.out.println("___________________________________________");
                    System.out.println("______________Method to Sorting____________");
                    do {
                        System.out.println("1. Sorting as readers' names.");
                        System.out.println("2. Sorting as numbers of borrowed times;");
                        System.out.println("0. Back to main menu.");
                        System.out.println("Your choice:");
                        choice_1 = scanner.nextInt();
                        if (choice_1 == 0) {
                            break;
                        }
                        switch (choice_1) {
                            case 1:
                                managements = utility.sortByReaderName(managements);
                                showManagementInfo(managements);
                                break;
                            case 2:
                                managements = utility.sortByReaderName(managements);
                                showManagementInfo(managements);
                                break;
                        }
                    } while (true);
                    break;
                case 7:
                    managements = managementController.read();

                    System.out.println("Enter the key words of readers' name:");
                    String keywords = scanner.nextLine();
                    ArrayList<Management> result = utility.searchByReaderName(managements, keywords);
                    if (result.size() == 0) {
                        System.out.println("Not found!");
                    } else {
                        showManagementInfo(result);
                    }
                    break;
            } 
        } while (choice != 0);
          scanner.close();
    }

    private static void checkBookId(BookDataController bookController) {
        ArrayList<Book> listBooks = bookController.read();
        if (listBooks.size() == 0) {
            //do nothing
        } else {
            Book.setBaseId(listBooks.get(listBooks.size() - 1).getBookId() + 1);
        }
    }

    private static void checkReaderId(ReaderDataController readerController) {
        ArrayList<Reader> listReaders = readerController.read();
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
        System.out.println("_____________Reader Information in List____________");
        for (Reader reader: readers) {
            System.out.println(reader);
        }
    }

    private static void showManagementInfo(ArrayList<Management> managements) {
        System.out.println("_____________Management Information in List____________");
        for(Management management: managements) {
            System.out.println(management);
        }
    }

    private static boolean checkBorrowed(ArrayList<Management> managements, int readerId) {
        int count = 0;
        for (Management management: managements) {
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
        for (Management management: managements) {
            if (management.getReaders().getReaderId() == readerId && management.getBooks().getBookId() == bookId && management.getNumOfBorrowed() == 3) {
                return true; // this book can't be borrowed
            }
        }

        return false;
    }

    private static int getTotal(ArrayList<Management> managements, int readerId, int bookId) {
        for (Management management: managements) {
            if (management.getReaders().getReaderId() == readerId && management.getBooks().getBookId() == bookId) {
                return management.getNumOfBorrowed();
            }
        }

        return 0;
    }

}
