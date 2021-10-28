package DAO;

import beans.Book;
import java.util.List;

public interface BookDAO {
    List<Book> readBooks() throws DAOException;
    void addBook(Book book) throws DAOException;
    void removeBook(Book book) throws DAOException;
}
