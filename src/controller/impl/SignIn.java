package controller.impl;

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

        boolean result;
        try {
            result = Service.signIn(username, password);
        } catch (ServiceException e) {
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        model.put("signedIn", result);
        return new RRContainer("success", model);
    }
}
