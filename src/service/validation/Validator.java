package service.validation;

import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class Validator {

    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]+$");
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(password);
        boolean isValid = password.length() > 7;
        isValid &= matcher.find();
        pattern = Pattern.compile("[A-Z]");
        matcher = pattern.matcher(password);
        isValid &= matcher.find();
        pattern = Pattern.compile("[0-9]");
        matcher = pattern.matcher(password);
        isValid &= matcher.find();

        return isValid;
    }

    public static boolean isValidIsAdmin(String isAdmin) {
        return isAdmin.matches("true|false");
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
