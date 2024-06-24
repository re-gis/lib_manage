import java.util.Scanner;

import classes.Book;
import classes.Library;
import classes.LibraryMember;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("123", "Testing0", "D Regis");
        Book book2 = new Book("654", "Testing", "Merci");
        library.addBook(book1);
        library.addBook(book2);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("..........Welcome to D Regis Library Management System...........\n");
            System.out.println("Choose 1 to enter as a library manager\n");
            System.out.println("Choose 2 to enter as a library member\n");
            System.out.println("Choose 0 to exit!\n");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline

            if (choice == 0) {
                System.out.println("\t...Done with 💖 by D Regis...");
                break;
            }

            switch (choice) {
                case 1:
                    handleLibraryManager(sc, library);
                    break;
                case 2:
                    handleLibraryMember(sc, library);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    private static void handleLibraryManager(Scanner sc, Library library) {
        System.out.println(">.....Enter the password to continue");
        while (!sc.next().equals("admin")) {
            System.out.println("Password is incorrect. Retry again or enter 0 to exit.");
            if (sc.next().equals("0")) {
                return;
            }
        }

        while (true) {
            System.out.println("Choose an action: \n");
            System.out.println("1. Add a book \n");
            System.out.println("2. Add a library member \n");
            System.out.println("3. Get all available books \n");
            System.out.println("4. Find book by ISBN \n");
            System.out.println("5. Find member by id \n");
            System.out.println("0. Go back\n");

            int action = sc.nextInt();
            sc.nextLine();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    System.out.println("Provide the ISBN, title, and author for the book:");
                    String isbn = sc.nextLine();
                    String title = sc.nextLine();
                    String author = sc.nextLine();
                    Book book = new Book(isbn, title, author);
                    library.addBook(book);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.println("Provide the member id and the name:");
                    String id = sc.nextLine();
                    String name = sc.nextLine();
                    LibraryMember member = new LibraryMember(id, name);
                    library.addMember(member);
                    System.out.println("Library member added successfully.");
                    break;
                case 3:
                    System.out.println(".....Available books....");
                    library.listAvailableBooks();
                    System.out.println("......................................");
                    break;
                case 4:
                    System.out.println("Provide the book's ISBN:");
                    isbn = sc.nextLine();
                    Book foundBook = library.findBookByIsbn(isbn);
                    System.out.println(foundBook != null ? foundBook : "Book not found.");
                    break;
                case 5:
                    System.out.println("Provide the member's id:");
                    id = sc.nextLine();
                    LibraryMember foundMember = library.findMemberById(id);
                    System.out.println(foundMember != null ? foundMember : "Member not found.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleLibraryMember(Scanner sc, Library library) {
        System.out.println("Provide your id and name:");
        String id = sc.nextLine();
        String name = sc.nextLine();

        LibraryMember member = library.findMemberById(id);
        if (member == null) {
            member = new LibraryMember(id, name);
            library.addMember(member);
            System.out.println("Thanks for registering.");
        }

        while (true) {
            System.out.println("Choose an action: \n");
            System.out.println("0. Get available books\n");
            System.out.println("1. Borrow book\n");
            System.out.println("2. Return a book\n");
            System.out.println("3. List borrowed books\n");
            System.out.println("4. Go back\n");

            int action = sc.nextInt();
            sc.nextLine();

            if (action == 4) {
                break;
            }

            switch (action) {
                case 0:
                    System.out.println("Available books:");
                    library.listAvailableBooks();
                    break;
                case 1:
                    System.out.println("Provide the ISBN of the book to borrow:");
                    String isbn = sc.nextLine();
                    Book book = library.findBookByIsbn(isbn);
                    if (book != null) {
                        member.borrowBook(book);
                        System.out.println(String.format("%s borrowed '%s'.", name, book.getTitle()));
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 2:
                    System.out.println("Provide the ISBN of the book to return:");
                    isbn = sc.nextLine();
                    book = library.findBookByIsbn(isbn);
                    if (book != null) {
                        member.returnBook(book);
                        System.out.println(String.format("%s returned '%s'.", name, book.getTitle()));
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.println(String.format("Books borrowed by %s:", name));
                    member.listBorrowedBooks();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
