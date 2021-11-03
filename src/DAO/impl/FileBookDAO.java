package DAO.impl;

import DAO.BookDAO;
import DAO.DAOException;
import beans.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBookDAO implements BookDAO {

    private final String PATH = PropertiesHolder.getProperty("BOOKS_FILE_PATH");

    @Override
    public void removeBook(Book book) throws DAOException {
        List<Book> books = readBooks();

        books.remove(book);
        try (FileWriter writer = new FileWriter(PATH)) {
            writer.write("");
        } catch (Exception e) {
            throw new DAOException("Файл книг не найден или повреждён");
        }

        for (Book currentBook : books) {
            addBook(currentBook);
        }
    }

    @Override
    public List<Book> readBooks() throws DAOException{
        List<Book> result = new ArrayList<>();
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            while ((current = reader.readLine()) != null) {
                result.add(new Book(current));
            }
        } catch (Exception e) {
            throw new DAOException("Файл книг не найден или повреждён");
        }
        return result;
    }

    @Override
    public void addBook(Book book) throws DAOException{
        try (FileWriter fos = new FileWriter(PATH, true)) {
            fos.append(book.toFileString()).append("\n");
        } catch (Exception e) {
            throw new DAOException("Файл книг не найден или повреждён", e);
        }
    }
}
