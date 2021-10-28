package DAO.impl;

import DAO.DAOException;
import DAO.UserDAO;
import beans.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUserDAO implements UserDAO {

    @Override
    public void addUser(User user) throws DAOException {
        try (FileWriter fos = new FileWriter("resources/Users.txt", true)) {
            fos.append(user.toFileString()).append("\n");
        } catch (Exception e) {
            throw new DAOException("Файл пользователей не найден или повреждён");
        }
    }

    @Override
    public List<User> readUsers() throws DAOException{
        List<User> result = new ArrayList<>();
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/Users.txt"))) {
            while ((current = reader.readLine()) != null) {
                result.add(new User(current));
            }
        } catch (Exception e) {
            throw new DAOException("Файл пользователей не найден или повреждён");
        }
        return result;
    }

}
