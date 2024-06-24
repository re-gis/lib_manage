package classes;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;

    public LibraryMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() < 5 && book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
        } else {
            System.out.println("Cannot borrow more books or book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
        } else {
            System.out.println("This book is not borrowed by the member.");
        }
    }

    public void listBorrowedBooks() {
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }
}