package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBook implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String name = (String) request.model.get("name");
        String author = (String) request.model.get("author");
        int year = request.model.get("year") == null ? 0 : (int) request.model.get("year");
        String genre = (String) request.model.get("genre");

        List<Book> books;
        Map<String, Object> model = new HashMap<>();
        try {
            books = Service.findBooks(name, author, year, genre);
        } catch (ServiceException e) {
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        model.put("books", books);
        return new RRContainer("success", model);
    }
}
