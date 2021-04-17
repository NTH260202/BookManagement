package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ControllerProcess;
import controller.FileInteracting;

import model.Book;
import model.BookReaderManagement;
import model.Reader;

public class Information {
    public static void main(String[] args) {

        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        var booksFileName = "BOOK.DAT";
        var readersFileName = "READER.DAT";
        var brmsFileName = "BRM.DAT";

        var controller = new FileInteracting();
        var process = new ControllerProcess();
        var books = new ArrayList<Book>();
        var readers = new ArrayList<Reader>();
        var brms = new ArrayList<BookReaderManagement>();

        var isReaderChecked = false;
        var isBookChecked = false;
        do {
            System.out.println("___________MENU___________");
            System.out.println("1. Adding a new book to the file.");
            System.out.println("2. Showing the book list in the file.");
            System.out.println("3. Adding a new reader to the file.");
            System.out.println("4. Showing the reader list in the file.");
            System.out.println("5. Updating the borrowing information.");
            System.out.println("6. Sorting");
            System.out.println("7. Searching information of borrowing managament by readers' names.");
            System.out.println("0. Exit");
            System.out.println("Your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Reading without users choice line

            switch(choice) {
                case 0:
                    System.out.println("__________________________________________");
                    System.out.println("Thanks for using our appplication!");
                    break;
                case 1:
                    if(!isBookChecked) {
                    checkBookID(controller, booksFileName);
                    isBookChecked = true;
                    };
                    String[] specs = {"Science", "Art", "Economic", "IT"};
                    String bookName, author, spec;
                    int year, quan, kindOfBook;

                    System.out.println("Enter the book's name: ");
                    bookName = scanner.nextLine();

                    System.out.println("Enter the book's author: ");
                    author = scanner.nextLine();

                    do {
                        System.out.println("Enter the book's specialization: ");
                        System.out.println("1. Science\n2. Art\n3. Economic\n4. IT");
                        System.out.println("Enter your choice: ");
                        kindOfBook = scanner.nextInt();
                    }while (kindOfBook < 1 || kindOfBook > 4);
                    spec = specs[kindOfBook-1];

                    System.out.println("Enter the book's published year: ");
                    year = scanner.nextInt();

                    System.out.println("Enter the book's quantity: ");
                    quan = scanner.nextInt();
                    
                    Book book = new Book(0, bookName, author, spec, year, quan);
                    controller.writeBookToFile(book, booksFileName);
                    break;

                case 2:
                    books = controller.readBookFromFile(booksFileName);
                    showBookInfo(books);
                    break;

                case 3:
                    if(!isReaderChecked) {
                    checkReaderID(controller, readersFileName);
                    isReaderChecked = true;
                    };
                    String readerName, address;
                    int phone;

                    System.out.println("Enter reader's name: ");
                    readerName = scanner.nextLine();

                    System.out.println("Enter the reader's address: ");
                    address = scanner.nextLine();

                    System.out.println("Enter the reader's phone number: ");
                    phone = scanner.nextInt();
                    
                    Reader reader = new Reader(0, readerName, address, phone);
                    controller.writeReaderToFile(reader, readersFileName);
                    break;

                case 4:
                    readers = controller.readReaderFromFile(readersFileName);
                    showReaderInfo(readers);
                    break;
                case 5:
                    readers = controller.readReaderFromFile(readersFileName);
                    books = controller.readBookFromFile(booksFileName);
                    brms = controller.readBRMFromFile(brmsFileName);

                    int readerID, bookID;
                    boolean isBorrowed = false;
                    boolean isFull = false;

                    do {
                        showReaderInfo(readers);
                        System.out.println("__________________________________");
                        System.out.println("Enter reader's ID (Enter 0 to exit): ");
                        readerID = scanner.nextInt();
                        if(readerID == 0) {
                            break; // All readers are borrowed enough books.
                        }
                        isBorrowed = checkBorrowed(brms, readerID);
                        if(isBorrowed) {
                            break;
                        }else {
                            System.out.println("You have borrowed enough books!.");
                        }
                    }while(true);

                    do {
                        showBookInfo(books);
                        System.out.println("___________________________________");
                        System.out.println("Enter book's ID (Enter 0 to exit): ");
                        bookID = scanner.nextInt();
                        if(bookID == 0) {
                            break;
                        }
                        isFull = checkFull(brms, readerID, bookID); // true if readers have borrowed 3 books
                        if(isFull) {
                            System.out.println("Please borrow another book");
                        }else {
                            break;
                        }
                    }while(true);

                    int total = getToTal(brms, readerID, bookID);
                    do {
                        System.out.println("Enter the number of books that you have borrowed (the max is 3 books) (borrowed: " + total + "): ");
                        int temp = scanner.nextInt();
                        if(temp + total >= 1 && temp + total <= 3) {
                            total = total + temp;
                            break;
                        }else {
                            System.out.println("Out of time is permitted. Please enter again!.");
                        }
                    }while(true);
                    scanner.nextLine(); // ignore the number line;
                    
                    System.out.println("Enter the book state: ");
                    String status = "";
                    status = scanner.nextLine();

                    Book currentBook = getBook(books, bookID);
                    Reader currentReader = getReader(readers, readerID);
                    BookReaderManagement b = new BookReaderManagement(currentReader, currentBook, total, status, 0);

                    brms = process.updateBRMInfo(brms, b);// update list BRM
                    controller.updateBRMFile(brms, brmsFileName); // update file BRM.DAT

                    showBRMInfo(brms);
                    break;
                case 6:
                    brms = controller.readBRMFromFile(brmsFileName);
                    brms = process.updateTotalBorrowed(brms);
                    System.out.println("_________________________________________________");
                    System.out.println("_________________Method to sort______________");
                    int x = 0;
                    do {
                        System.out.println("1. Sorting as readers' names.");
                        System.out.println("2. Sorting as numbers of borrowed times;");
                        System.out.println("0. Back to main menu.");
                        System.out.println("Your choice:");
                        x = scanner.nextInt();                    
                        if(x == 0) {
                            break;
                        }
                        switch(x) {
                            case 1:
                                brms = process.sortByReaderName(brms);
                                showBRMInfo(brms);
                                break;
                            case 2:
                                brms = process.sortByNumOfBorrowed(brms);
                                showBRMInfo(brms);
                                break;
                        }
                    }while(true);
                    break;
                case 7:
                    brms = controller.readBRMFromFile(brmsFileName);
                    System.out.println("Enter the key words of readers' name: ");
                    String key = scanner.nextLine();
                    var result = process.searchByReaderName(brms, key);
                    if(result.size() == 0) {
                        System.out.println("Not found!");
                    }else {
                        showBRMInfo(result);
                    }
                    break;
            }
        }while (choice !=0);
        scanner.close();
    }

    private static void checkBookID(FileInteracting controller, String fileName) {
        var listBooks = controller.readBookFromFile(fileName);
        if(listBooks.size() == 0) {
            //do nothing
        }else {
            Book.setID(listBooks.get(listBooks.size()-1).getBookID()+1);
        }     
    }

    private static void checkReaderID(FileInteracting controller, String fileName) {
        var listReaders = controller.readReaderFromFile(fileName);
        if(listReaders.size() == 0) {
            //do nothing
        }else {
            Reader.setID(listReaders.get(listReaders.size()-1).getReaderID()+1);
        }
    }


    private static void showBookInfo(ArrayList<Book> books) {
        System.out.println("______________Book Information in File______________________");
        for(var b: books) {
            System.out.println(b);
        }
    }

    private static void showReaderInfo(ArrayList<Reader> readers) {
        System.out.println("______________Reader Information in File______________________");
        for(var r: readers) {
            System.out.println(r);
        }
    }

    private static void showBRMInfo(ArrayList<BookReaderManagement> brms) {
        for(var b: brms) {
            System.out.println(b);
        }
    }

    private static boolean checkBorrowed(ArrayList<BookReaderManagement> brms, int readerID) {
        int count = 0;
        for(var r: brms) {
            if(r.getReaders().getReaderID() == readerID) {
                count = count + r.getNumOfBorrowed();
            }
        }
        if(count == 15) {
            return false; // can't borrow
        } 
        return true;       
    }

    private static boolean checkFull(ArrayList<BookReaderManagement> brms, int readerID, int bookID) {
        for(var r: brms) {
            if(r.getReaders().getReaderID() == readerID && r.getBooks().getBookID() == bookID && r.getNumOfBorrowed() ==3) {
                return true;// this book can't be borrowed
            }
        }
        return false;       
    }

    private static int getToTal(ArrayList<BookReaderManagement> brms, int readerID, int bookID) {
        for(var r: brms) {
            if(r.getReaders().getReaderID() == readerID && r.getBooks().getBookID() == bookID) {
                return r.getNumOfBorrowed();
            }
        }
        return 0;
    }

    private static Book getBook(ArrayList<Book> books, int bookID) {
        for(int i = 0; i < books.size();  i ++) {
            if(books.get(i).getBookID() == bookID) {
                return books.get(i);
            }
        }
        return null;
    }
   

    private static Reader getReader(ArrayList<Reader> readers, int readerID) {
        for(int i = 0; i < readers.size(); i ++) {
            if(readers.get(i).getReaderID() == readerID) {
                return readers.get(i);
            }
        }
        return null;
    }
    
}
