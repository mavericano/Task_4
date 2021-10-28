package service;

import DAO.FactoryDAO;
import beans.Book;
import beans.User;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public static List<Book> getAllBooks() {
        List<Book> books = null;
        try {
            books = FactoryDAO.getInstance().getBookDAO().readBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static void addBook(Book book) {
        try {
            FactoryDAO.getInstance().getBookDAO().addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBook(Book book) {
        try {
            FactoryDAO.getInstance().getBookDAO().removeBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Book> findBooks(Book book) {
        if (book.getName() != null) {
            return findByName(book.getName());
        }
        if (book.getAuthor() != null) {
            return findByAuthor(book.getAuthor());
        }
        if (book.getReleaseYear() != 0) {
            return findByYear(book.getReleaseYear());
        }
        if (book.getGenre() != null) {
            return findByGenre(book.getGenre());
        }
        return null;
    }

    private static List<Book> findByName(String name) {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getName().equals(name)) result.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getAuthor().equals(author)) result.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<Book> findByYear(int year) {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getReleaseYear() == year) result.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<Book> findByGenre(String genre) {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getGenre().equals(genre)) result.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    private static List<Book> findEqual(Book book) {
//        List<Book> result = new ArrayList<>();
//        try {
//            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
//            for (Book currentBook : books) {
//                if (currentBook.equals(book)) result.add(currentBook);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public static void editBook(Book book, Book newBook) {
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            while (books.remove(book)) {
                FactoryDAO.getInstance().getBookDAO().addBook(newBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void register(User user) {
        try {
            List<User> users = FactoryDAO.getInstance().getUserDAO().readUsers();
            if (!users.contains(user)) {
                FactoryDAO.getInstance().getUserDAO().addUser(user);
            } else {
                //TODO throw exception here
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
