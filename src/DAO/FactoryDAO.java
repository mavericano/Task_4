package DAO;

import DAO.impl.FileBookDAO;
import DAO.impl.FileUserDAO;

public class FactoryDAO {
    private static FactoryDAO instance;
    private UserDAO userDAO;
    private BookDAO bookDAO;

    private FactoryDAO() {
        userDAO = new FileUserDAO();
        bookDAO = new FileBookDAO();
    }

    public static FactoryDAO getInstance() {
        if (instance == null) {
            instance = new FactoryDAO();
        }

        return instance;
    }

    public static void setInstance(FactoryDAO instance) {
        FactoryDAO.instance = instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
