package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;

public class EditBook implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String name = (String) request.model.get("name");
        String author = (String) request.model.get("author");
        int year = (int) request.model.get("year");
        String genre = (String) request.model.get("genre");

        String newName = (String) request.model.get("newName");
        String newAuthor = (String) request.model.get("newAuthor");
        int newYear = (int) request.model.get("newYear");
        String newGenre = (String) request.model.get("newGenre");

        Book book = new Book(name, author, year, genre);
        Book newBook = new Book(newName, newAuthor, newYear, newGenre);

        Service.editBook(book, newBook);

        return new RRContainer("success", new HashMap<>());
    }
}
