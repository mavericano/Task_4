package service;

import DAO.DAOException;
import DAO.FactoryDAO;
import beans.Book;
import beans.User;

import java.util.ArrayList;
import java.util.List;

import static service.validation.Validator.*;

public class Service {

    private static User currentUser;

    public static List<Book> getAllBooks() throws ServiceException {
        List<Book> books;
        try {
            books = FactoryDAO.getInstance().getBookDAO().readBooks();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return books;
    }

    public static void addBook(String name, String author, int year, String genre) throws ServiceException {
        if (!isValidBookName(name)) {
            throw new ServiceException("Некорректное название книги");
        }
        if (!isValidAuthorName(author)) {
            throw new ServiceException("Некорректное имя автора");
        }
        if (!isValidReleaseYear(year)) {
            throw new ServiceException("Некорректный год публикации книги");
        }
        if (!isValidGenre(genre)) {
            throw new ServiceException("Некорректный жанр книги");
        }

        Book book = new Book(name, author, year, genre);
        try {
            FactoryDAO.getInstance().getBookDAO().addBook(book);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static void removeBook(Book book) throws ServiceException{
        try {
            FactoryDAO.getInstance().getBookDAO().removeBook(book);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static List<Book> findBooks(String name, String author, int year, String genre) throws ServiceException {
        if (name != null) {
            if (!isValidBookName(name)) {
                throw new ServiceException("Некорректное название книги");
            }
            return findByName(name);
        }
        if (author != null) {
            if (!isValidAuthorName(author)) {
                throw new ServiceException("Некорректное имя автора");
            }
            return findByAuthor(author);
        }
        if (year != 0) {
            if (!isValidReleaseYear(year)) {
                throw new ServiceException("Некорректный год публикации книги");
            }
            return findByYear(year);
        }
        if (genre != null) {
            if (!isValidGenre(genre)) {
                throw new ServiceException("Некорректный жанр книги");
            }
            return findByGenre(genre);
        }
        return null;
    }

    private static List<Book> findByName(String name) throws ServiceException {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getName().equals(name)) result.add(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return result;
    }

    private static List<Book> findByAuthor(String author) throws ServiceException {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getAuthor().equals(author)) result.add(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return result;
    }

    private static List<Book> findByYear(int year) throws ServiceException {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getReleaseYear() == year) result.add(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return result;
    }

    private static List<Book> findByGenre(String genre) throws ServiceException {
        List<Book> result = new ArrayList<>();
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            for (Book book : books) {
                if (book.getGenre().equals(genre)) result.add(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
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
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public static void editBook(Book book, Book newBook) throws ServiceException {
        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            while (books.remove(book)) {
                FactoryDAO.getInstance().getBookDAO().addBook(newBook);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static void register(User user) throws ServiceException {
        try {
            List<User> users = FactoryDAO.getInstance().getUserDAO().readUsers();
            if (!users.contains(user)) {
                FactoryDAO.getInstance().getUserDAO().addUser(user);
            } else {
                throw new ServiceException("Пользователь с такими данными уже существует");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static boolean checkRights(User user) throws ServiceException {
        boolean result = false;
        try {
            List<User> users = FactoryDAO.getInstance().getUserDAO().readUsers();
            for (User currentUser : users) {
                if (currentUser.getUsername().equals(user.getUsername()) && currentUser.getPassword().equals(user.getPassword())) {
                    result = currentUser.isAdmin();
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return result;
    }

    public static boolean signIn(User user) throws ServiceException {
        boolean result = false;
        try {
            List<User> users = FactoryDAO.getInstance().getUserDAO().readUsers();
            if (users.contains(user)) {
                result = true;
                currentUser = user;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return result;
    }

    public static void signOut() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Service.currentUser = currentUser;
    }
}
