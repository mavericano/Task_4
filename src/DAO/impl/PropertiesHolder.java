package DAO.impl;

import java.util.HashMap;
import java.util.Map;

public class PropertiesHolder {
    private static final Map<String, String> properties = new HashMap<>();

    static {
        properties.put("USERS_FILE_PATH", "resources/Users.txt");
        properties.put("BOOKS_FILE_PATH", "resources/Books.txt");
    }

    public static String getProperty(String key) {
        return properties.get(key);
    }

    public static void addProperty(String key, String value) {
        PropertiesHolder.properties.put(key, value);
    }
}
