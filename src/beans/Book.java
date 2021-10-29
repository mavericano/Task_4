package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements Serializable {
    static final long serialVersionUID = 2L;
    static final String PARAMETER_DELIMITER = "|";
    static final String PARAMETER_DELIMITER_REGEX = "\\|";

    private String name;
    private String author;
    private int releaseYear;
    private String genre;

    public Book() {
    }

    public Book(String name, String author, int releaseYear, String genre) {
        this.name = name;
        this.author = author;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public Book(String fileLine){
        Pattern pattern = Pattern.compile(PARAMETER_DELIMITER_REGEX + "\\w+" + PARAMETER_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(fileLine);

        List<String> params = new ArrayList<>();
        while (matcher.find()) {
            params.add(matcher.group(0).substring(1, matcher.group(0).length() - 1));
        }
        name = params.get(0);
        author = params.get(1);
        releaseYear = Integer.parseInt(params.get(2));
        genre = params.get(3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();

        sb.append(PARAMETER_DELIMITER).append(name).append(PARAMETER_DELIMITER);
        sb.append(PARAMETER_DELIMITER).append(author).append(PARAMETER_DELIMITER);
        sb.append(PARAMETER_DELIMITER).append(releaseYear).append(PARAMETER_DELIMITER);
        sb.append(PARAMETER_DELIMITER).append(genre).append(PARAMETER_DELIMITER);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return releaseYear == book.releaseYear && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, releaseYear, genre);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                '}';
    }
}
