package controller.impl;

import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> model = new HashMap<>();
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        return new RRContainer("success", new HashMap<>());
    }
}