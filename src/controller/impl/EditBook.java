package controller.impl;

import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.Map;

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

        try {
            Service.editBook(name, author, year, genre, newName, newAuthor, newYear, newGenre);
        } catch (ServiceException e) {
            Map<String, Object> model = new HashMap<>();
            model.put("message", e.getMessage());
            new RRContainer("fail", model);
        }

        return new RRContainer("success", new HashMap<>());
    }
}
