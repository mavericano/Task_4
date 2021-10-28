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
        try {
            books = Service.getAllBooks();
        } catch (ServiceException e) {
            return new RRContainer("fail", new HashMap<>());
        }
        Map<String, Object> model = new HashMap<>();
        model.put("books", books);
        return new RRContainer("success", model);
    }
}
