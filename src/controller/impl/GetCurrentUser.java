package controller.impl;

import beans.User;
import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;
import java.util.Map;

public class GetCurrentUser implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        User user = Service.getCurrentUser();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return new RRContainer("success", model);
    }
}
