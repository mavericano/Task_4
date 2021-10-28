package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;

public class AddBook implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String name = (String) request.model.get("name");
        String author = (String) request.model.get("author");
        int year = (int) request.model.get("year");
        String genre = (String) request.model.get("genre");

        try {
            Service.addBook(name, author, year, genre);
        } catch (ServiceException e) {
            return new RRContainer("fail", new HashMap<>());
        }
        return new RRContainer("success", new HashMap<>());
    }
}