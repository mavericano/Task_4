package controller.impl;

import controller.Command;
import controller.connection.RRContainer;

import java.util.HashMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final HashMap<String, Command> commands;

    private CommandProvider() {
        commands = new HashMap<>();
        commands.put("SIGN_IN", new SignIn());
        commands.put("REGISTER", new Register());
        commands.put("FIND_BOOK", new FindBook());
        commands.put("DELETE_BOOK", new DeleteBook());
        commands.put("ADD_BOOK", new AddBook());
        commands.put("VIEW_BOOKS", new ViewBooks());
        commands.put("EDIT_BOOK", new EditBook());
        commands.put("HAS_USERS", new HasUsers());
    }

    public Command createCommand(RRContainer request) {
        String command = request.header.toUpperCase();
        Command result = commands.get(command);
        return result == null ? new WrongCommand() : result;
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }

        return instance;
    }
}
