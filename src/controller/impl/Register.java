package controller.impl;

import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class Register implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String username = (String) request.model.get("username");
        String password = (String) request.model.get("password");
        String isAdmin = (String) request.model.get("isAdmin");

        try {
            Service.register(username, password, isAdmin);
        } catch (ServiceException e) {
            Map<String, Object> model = new HashMap<>();
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        return new RRContainer("success", new HashMap<>());
    }
}
