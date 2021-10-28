package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;

public class AddBook implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String name = (String) request.model.get("name");
        String author = (String) request.model.get("author");
        int year = (int) request.model.get("year");
        String genre = (String) request.model.get("genre");

        Service.addBook(new Book(name, author, year, genre));
        return new RRContainer("success", new HashMap<>());
    }
}