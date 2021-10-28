package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewBooks implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        List<Book> books = Service.getAllBooks();
        Map<String, Object> model = new HashMap<>();
        model.put("books", books);
        return new RRContainer("success", model);
    }
}
