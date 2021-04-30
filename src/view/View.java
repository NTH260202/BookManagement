package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.BookDataController;
import model.Book;

public class View {
    public static void view() {
        BookDataController bookController = new BookDataController();
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        ArrayList<Book> books = new ArrayList<Book>();
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

                Book book = new Book(bookName, 100000, author, spec, year, quan);
                bookController.writeBookToFile(book);
                break;
            case 2:
                books = bookController.readBookFromFile();
                showBookInfo(books);
                break;
        } 
        } while (choice != 0);
        scanner.close();
    }

    private static void showBookInfo(ArrayList<Book> books) {
        System.out.println("_____________Book Information in List____________");
        for (Book book: books)
        {
            System.out.println(book);
        }
    }
}
