package DAO;

import beans.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user) throws DAOException;
    List<User> readUsers() throws DAOException;
}
