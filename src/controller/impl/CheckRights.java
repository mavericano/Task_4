package controller.impl;

import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class CheckRights implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        String username = (String) request.model.get("username");
        String password = (String) request.model.get("password");
        boolean result;
        Map<String, Object> model = new HashMap<>();

        try {
            result = Service.checkRights(username, password);
        } catch (ServiceException e) {
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }

        model.put("rights", result);
        return new RRContainer("success", model);
    }
}
