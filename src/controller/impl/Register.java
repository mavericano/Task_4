package controller.impl;

import beans.User;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

public class Register implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String username = (String) request.model.get("username");
        String password = (String) request.model.get("password");
        User user = new User(username, password);

        Service.register(user);
        return null;
    }
}
