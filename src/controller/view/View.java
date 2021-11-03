package controller.view;

import beans.Book;
import beans.User;
import controller.connection.RRContainer;
import controller.impl.CommandProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class View {

    /*
    TODO:
        Totally refactor sign in
        View:
            +REMOVE HARDCODED loggedIn
            REMOVE SignOut command and service
            REMOVE CheckRights command and service
            Register
            CheckRights
            +SignIn
            SignOut
            +AddBook
            +ViewAllBooks
            +FindBook
            +EditBook
            +DeleteBook
     */
    private final static String BOOKS_MENU = """
            1. Просмотреть все книги
            2. Добавить книгу
            3. Отредактировать имеющуюся книгу
            4. Найти книгу
            5. Удалить книгу
            6. Выйти из учетной записи
            7. Добавить учетную запись
            8. Завершить работу""";
    private final static String ADMIN_BOOKS_MENU = """
            1. Просмотреть все книги
            2. Добавить книгу
            3. Отредактировать имеющуюся книгу
            4. Найти книгу
            5. Удалить книгу
            6. Выйти из учетной записи
            7. Завершить работу""";
    private final static String LOG_IN_MENU = """
            Для использования приложения необходима учетная запись.""";
    private final static String SEARCH_MENU = """
            1. Поиск по названию
            2. Поиск по автору
            3. Поиск по году выпуска
            4. Поиск по жанру""";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean toStay = true;
        User currentUser = null;
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = new HashMap<>();
        while (toStay) {
            if (currentUser != null) {
                if (currentUser.isAdmin()) {
                    System.out.println(ADMIN_BOOKS_MENU);
                    input = scanner.nextLine();
                    if (input.matches("^[1-8]$")) {
                        switch (input) {
                            case "1":
                                viewAllBooks();
                                break;
                            case "2":
                                addBook();
                                break;
                            case "3":
                                editBook();
                                break;
                            case "4":
                                findBook();
                                break;
                            case "5":
                                removeBook();
                                break;
                            case "6":
                                currentUser = null;
                                break;
                            case "7":
                                System.out.println("add");
                                break;
                            case "8":
                                toStay = false;
                        }
                    } else {
                        System.out.println("Некорректная комманда");
                    }
                } else {
                    System.out.println(BOOKS_MENU);
                    input = scanner.nextLine();
                    if (input.matches("^[1-8]$")) {
                        switch (input) {
                            case "1":
                                viewAllBooks();
                                break;
                            case "2":
                                addBook();
                                break;
                            case "3":
                                editBook();
                                break;
                            case "4":
                                findBook();
                                break;
                            case "5":
                                removeBook();
                                break;
                            case "6":
                                currentUser = null;
                                break;
                            case "7":
                                toStay = false;
                        }
                    } else {
                        System.out.println("Некорректная комманда");
                    }
                }
            } else {
                System.out.println(LOG_IN_MENU);
                currentUser = signIn();
                if (currentUser == null) {
                    System.out.println("Данный пользователь не найден");
                } else {
                    System.out.println("Добро пожаловать!");
                }
            }
        }
        System.out.println("Спасибо за использование!");
    }

    public static User signIn() {
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя пользователя");
        model.put("username", scanner.nextLine());
        System.out.println("Введите пароль");
        model.put("password", scanner.nextLine());

        request = new RRContainer("SIGN_IN", model);
        response = CommandProvider.getInstance().createCommand(request).execute(request);
        if ("fail".equals(response.header)) {
            System.out.println(response.model.get("message"));
        }

        return (User) response.model.get("user");
    }

    private static Map<String, Object> readBookProperties() {
        Map<String, Object> model = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название книги");
        model.put("name", scanner.nextLine());
        System.out.println("Введите автора книги");
        model.put("author", scanner.nextLine());
        System.out.println("Введите год выхода книги");
        model.put("year", Integer.parseInt(scanner.nextLine()));
        System.out.println("Введите жанр книги");
        model.put("genre", scanner.nextLine());
        return model;
    }

    private static void printBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append("Название: ").append(book.getName()).append("\n");
            sb.append("Автор: ").append(book.getAuthor()).append("\n");
            sb.append("Год выхода: ").append(book.getReleaseYear()).append("\n");
            sb.append("Жанр: ").append(book.getGenre()).append("\n");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @SuppressWarnings("uncheked")
    private static void viewAllBooks() {
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = new HashMap<>();
        request = new RRContainer("VIEW_BOOKS", model);
        response = CommandProvider.getInstance().createCommand(request).execute(request);
        if ("success".equals(response.header)) {
            printBooks((List<Book>) response.model.get("books"));
        } else {
            System.out.println(response.model.get("message"));
        }
    }

    private static void addBook() {
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = readBookProperties();

        request = new RRContainer("ADD_BOOK", model);
        response = CommandProvider.getInstance().createCommand(request).execute(request);
        if ("fail".equals(response.header)) {
            System.out.println(response.model.get("message"));
        }
    }

    private static void editBook() {
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = readBookProperties();
        System.out.println("Введите новые данные книги");
        model.putAll(readBookProperties());

        request = new RRContainer("EDIT_BOOK", model);
        response = CommandProvider.getInstance().createCommand(request).execute(request);
        if ("fail".equals(response.header)) {
            System.out.println(response.model.get("message"));
        }
    }

    @SuppressWarnings("uncheked")
    private static void findBook() {
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println(SEARCH_MENU);
        String input = scanner.nextLine();
        if (input.matches("^[1-4]$")) {
            switch (input) {
                case "1":
                    System.out.println("Введите название книги");
                    model.put("name", scanner.nextLine());
                    break;
                case "2":
                    System.out.println("Введите автора книги");
                    model.put("author", scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Введите год выхода книги");
                    model.put("year", Integer.parseInt(scanner.nextLine()));
                    break;
                case "4":
                    System.out.println("Введите жанр книги");
                    model.put("genre", scanner.nextLine());
                    break;
            }
            request = new RRContainer("FIND_BOOK", model);
            response = CommandProvider.getInstance().createCommand(request).execute(request);
            if ("success".equals(response.header)) {
                printBooks((List<Book>) response.model.get("books"));
            } else {
                System.out.println(response.model.get("message"));
            }
        } else {
            System.out.println("Некорректная комманда");
        }
    }

    private static void removeBook() {
        RRContainer request;
        RRContainer response;
        Map<String, Object> model = readBookProperties();

        request = new RRContainer("DELETE_BOOK", model);
        response = CommandProvider.getInstance().createCommand(request).execute(request);
        if ("fail".equals(response.header)) {
            System.out.println(response.model.get("message"));
        }
    }
}
