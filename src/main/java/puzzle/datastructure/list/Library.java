package puzzle.datastructure.list;

import java.util.*;
import java.util.stream.Collectors;

class Book {
    private int id;
    private String name;
    private String author;
    private int issueDate;
    private int returnDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(int issueDate) {
        this.issueDate = issueDate;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book book = (Book) obj;
        return getId() == book.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

class ListOfBooks {
    private List<Book> books;

    public ListOfBooks() {
        this.books = new LinkedList<>();
    }

    public void add(int id, String name, String author, int issueDate, int returnDate) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setIssueDate(issueDate);
        book.setReturnDate(returnDate);
        books.add(book);
        System.out.println("Successfully added: "+id);
    }

    public void edit(int id, String name, String author, int issueDate, int returnDate) {
        Book bookToSearch = new Book();
        bookToSearch.setId(id);

        if (books.contains(bookToSearch)) {
            Book bookMatch = books.stream().filter(book -> book.getId() == id).collect(Collectors.toList()).get(0);
            bookMatch.setName(name);
            bookMatch.setAuthor(author);
            bookMatch.setIssueDate(issueDate);
            bookMatch.setReturnDate(returnDate);
            System.out.println("Successfully edited: "+id);
        } else {
            System.out.println("No such Book ID exists");
        }
    }

    public void delete(int id) {
        Book bookToDelete = new Book();
        bookToDelete.setId(id);

        if (books.contains(bookToDelete)) {
            books.remove(bookToDelete);
            System.out.println("Successfully deleted: "+id);
        } else {
            System.out.println("No such Book ID exists");
        }
    }

    public void printDatabase() {
        System.out.println("List of books:");
        for (Book book : books) {
            System.out.println("ID: "+book.getId()+", Name: "+book.getName()+", Author: "+book.getAuthor()+", Date of Issue: "+book.getIssueDate()+", Date of Return: "+book.getReturnDate());
        }
    }
}


public class Library {
    public static void main(String[] args) {
        ListOfBooks database = new ListOfBooks();
        database.add(234,"Hamlet","W. Shakespeare",27,29);
        database.add(35,"Relativity","Albert Einstein",15,30);
        database.add(2,"Data Sciences","Shan B",1,4);
        database.edit(2,"Data Sciences","Shan B",1,8);
        database.delete(35);
        database.printDatabase();
        database.add(120,"Crooked House","Agatha Christie",15,28);
        database.edit(235,"Hamlet","W. Shakespeare",28,29);
        database.add(121,"The final problem","Arthur Doyle",13,20);
        database.delete(234);
        database.printDatabase();
    }
}
