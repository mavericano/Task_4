package controller;

import controller.connection.RRContainer;

public interface Command {
    RRContainer execute(RRContainer request);
    String DELIMITER = " ";
}
