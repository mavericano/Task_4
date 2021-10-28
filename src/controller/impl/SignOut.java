package controller.impl;

import controller.Command;
import controller.connection.RRContainer;
import service.Service;

import java.util.HashMap;

public class SignOut implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        Service.signOut();
        return new RRContainer("success", new HashMap<>());
    }
}
