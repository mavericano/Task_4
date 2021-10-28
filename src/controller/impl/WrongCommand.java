package controller.impl;

import controller.Command;
import controller.connection.RRContainer;

public class WrongCommand implements Command {
    @Override
    public RRContainer execute(RRContainer request) {
        return null;
                //"Данный запрос невыполним!";
    }
}
