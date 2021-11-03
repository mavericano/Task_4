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

    public static void removeBook(String name, String author, int year, String genre) throws ServiceException{
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

    public static void editBook(String name, String author, int year, String genre, String newName, String newAuthor, int newYear, String newGenre) throws ServiceException {
        if (!isValidBookName(name) || !isValidBookName(newName)) {
            throw new ServiceException("Некорректное название книги");
        }
        if (!isValidAuthorName(author) || !isValidAuthorName(newAuthor)) {
            throw new ServiceException("Некорректное имя автора");
        }
        if (!isValidReleaseYear(year) || !isValidReleaseYear(newYear)) {
            throw new ServiceException("Некорректный год публикации книги");
        }
        if (!isValidGenre(genre) || !isValidGenre(newGenre)) {
            throw new ServiceException("Некорректный жанр книги");
        }

        Book book = new Book(name, author, year, genre);
        Book newBook = new Book(newName, newAuthor, newYear, newGenre);

        try {
            List<Book> books = FactoryDAO.getInstance().getBookDAO().readBooks();
            while (books.remove(book)) {
                FactoryDAO.getInstance().getBookDAO().removeBook(book);
                FactoryDAO.getInstance().getBookDAO().addBook(newBook);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static void register(String username, String password) throws ServiceException {
        if (!isValidUsername(username)) {
            throw new ServiceException("Некорректное имя пользователя");
        }
        if (!isValidPassword(password)) {
            throw new ServiceException("Некорректный пароль");
        }
        User user = new User(username, password);
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

    public static boolean checkRights(String username, String password) throws ServiceException {
        if (!isValidUsername(username)) {
            throw new ServiceException("Некорректное имя пользователя");
        }
        if (!isValidPassword(password)) {
            throw new ServiceException("Некорректный пароль");
        }
        boolean result = false;
        boolean found = false;
        User user = new User(username, password);
        try {
            List<User> users = FactoryDAO.getInstance().getUserDAO().readUsers();
            for (User currentUser : users) {
                if (currentUser.getUsername().equals(user.getUsername()) && currentUser.getPassword().equals(user.getPassword())) {
                    result = currentUser.isAdmin();
                    found = true;
                }
            }
            if (!found) {
                throw new ServiceException("Данного пользователя не существует");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return result;
    }

    public static User signIn(String username, String password) throws ServiceException {
        if (!isValidUsername(username)) {
            throw new ServiceException("Некорректное имя пользователя");
        }
        if (!isValidPassword(password)) {
            throw new ServiceException("Некорректный пароль");
        }
        User user = new User(username, password);
        try {
            List<User> users = FactoryDAO.getInstance().getUserDAO().readUsers();
            for (User curr : users) {
                if (user.compareWithoutRights(curr)) {
                    currentUser = user;
                    return currentUser;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return null;
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
