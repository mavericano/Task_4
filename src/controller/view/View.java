package controller.view;

import beans.Book;
import beans.User;
import controller.connection.RRContainer;
import controller.impl.CommandProvider;

import java.util.HashMap;
import java.util.Map;

public class View {

    /*
    TODO:
        +DeleteBook
        +FindBook
        +EditBook
        Register
        SignIn
        SignOut
        validation
        Exceptions
     */

    public static void main(String[] args) {
        Book book = new Book("a", "b", 2006, "c");
        User user = new User("vladik", "lox", true);

        try {
            CommandProvider provider = CommandProvider.getInstance();
            Map<String, Object> map = new HashMap<>();
            map.put("book", book);
            RRContainer requestTwo = new RRContainer("add_book", map);
            RRContainer responseTwo = provider.createCommand(requestTwo).execute(requestTwo);
            RRContainer request = new RRContainer("view_books", new HashMap<>());
            RRContainer response = provider.createCommand(request).execute(request);
            System.out.println(response.model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
