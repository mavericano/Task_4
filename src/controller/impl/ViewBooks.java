package controller.impl;

import beans.Book;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewBooks implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        List<Book> books;
        Map<String, Object> model = new HashMap<>();
        try {
            books = Service.getAllBooks();
        } catch (ServiceException e) {
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        model.put("books", books);
        return new RRContainer("success", model);
    }
}
