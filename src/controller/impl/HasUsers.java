package controller.impl;

import controller.Command;
import controller.connection.RRContainer;
import service.Service;
import service.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class HasUsers implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        Map<String, Object> model = new HashMap<>();
        boolean result;
        try {
            result = Service.hasUsers();
        } catch (ServiceException e) {
            model.put("message", e.getMessage());
            return new RRContainer("fail", model);
        }
        model.put("hasUsers", result);
        return new RRContainer("success", model);
    }
}
