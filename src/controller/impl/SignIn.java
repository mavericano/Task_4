package controller.impl;

import beans.User;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;
import java.util.Map;

public class SignIn implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String username = (String) request.model.get("username");
        String password = (String) request.model.get("password");
        User user = new User(username, password);

        boolean result = Service.signIn(user);
        Map<String, Object> model = new HashMap<>();
        model.put("signedIn", result);
        return new RRContainer("success", model);
    }
}
