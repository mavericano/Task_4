package service.validation;

import java.time.Year;

public class Validator {

    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]+$");
    }

    public static boolean isValidPassword(String password) {
        return password.length() > 7 && password.matches("[a-z]+") && password.matches("[A-Z]+") &&
                password.matches("[0-9]+");
    }

    public static boolean isValidBookName(String name) {
        return name.matches("^[a-zA-Z0-9 ]+$");
    }

    public static boolean isValidAuthorName(String name) {
        return name.matches("^[a-zA-Z]+");
    }

    public static boolean isValidReleaseYear(int releaseYear) {
        return releaseYear > 0 && releaseYear <= Year.now().getValue();
    }

    public static boolean isValidGenre(String genre) {
        return genre.matches("^[a-zA-Z0-9 ]+$");
    }
}
