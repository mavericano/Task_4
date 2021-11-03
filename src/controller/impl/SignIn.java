package controller.impl;

import beans.User;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class SignIn implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String username = (String) request.model.get("username");
        String password = (String) request.model.get("password");

        Map<String, Object> model = new HashMap<>();

        User currentUser;
        try {
            currentUser = Service.signIn(username, password);
        } catch (ServiceException e) {
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        model.put("user", currentUser);
        return new RRContainer("success", model);
    }
}
