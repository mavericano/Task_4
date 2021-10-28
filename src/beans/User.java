package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {
    static final long serialVersionUID = 1L;
    static final String PARAMETER_DELIMITER = "|";
    static final String PARAMETER_DELIMITER_REGEX = "\\|";

    private String username;
    private String password;
    private boolean isAdmin;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isAdmin = false;
    }

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String fileLine) {
        Pattern pattern = Pattern.compile(PARAMETER_DELIMITER_REGEX + "\\w+" + PARAMETER_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(fileLine);

        List<String> params = new ArrayList<>();
        while (matcher.find()) {
            params.add(matcher.group(0).substring(1, matcher.group(0).length() - 1));
        }
        username = params.get(0);
        password = params.get(1);
        isAdmin = Boolean.parseBoolean(params.get(2));
    }

    public String toFileString() {
        StringBuffer sb = new StringBuffer();

        sb.append(PARAMETER_DELIMITER).append(username).append(PARAMETER_DELIMITER);
        sb.append(PARAMETER_DELIMITER).append(password).append(PARAMETER_DELIMITER);
        sb.append(PARAMETER_DELIMITER).append(isAdmin).append(PARAMETER_DELIMITER);

        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
