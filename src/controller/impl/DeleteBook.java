package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;

public class DeleteBook implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String name = (String) request.model.get("name");
        String author = (String) request.model.get("author");
        int year = (int) request.model.get("year");
        String genre = (String) request.model.get("genre");
        Book book = new Book(name, author, year, genre);
        Service.removeBook(book);
        return new RRContainer("success", new HashMap<>());
    }
}
