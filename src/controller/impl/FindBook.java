package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBook implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String name = (String) request.model.get("name");
        String author = (String) request.model.get("author");
        int year = (int) request.model.get("year");
        String genre = (String) request.model.get("genre");

        List<Book> books = Service.findBooks(name, author, year, genre);
        Map<String, Object> model = new HashMap<>();
        model.put("books", books);
        return new RRContainer("success", model);
    }
}
