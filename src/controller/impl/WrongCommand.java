package controller.impl;

import controller.Command;
import controller.connection.RRContainer;

import java.util.HashMap;
import java.util.Map;

public class WrongCommand implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", "Данный запрос невыполним!");
        return new RRContainer("fail", model);
    }
}
