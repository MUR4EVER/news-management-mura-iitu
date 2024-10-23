package iitu.edu.kz.controller.menu;

import iitu.edu.kz.controller.implement.newsController;
import iitu.edu.kz.service.dto.NewsDTO;
import iitu.edu.kz.service.exceptions.errors;
import iitu.edu.kz.service.exceptions.newsException;

import java.util.Scanner;

public class controllerMenu {
    private final Scanner scanner;
    private final iitu.edu.kz.controller.implement.newsController newsController;
    private final iitu.edu.kz.service.validation.newsValidator newsValidator;

    public controllerMenu() {
        scanner = new Scanner(System.in);
        newsController = new newsController();
        newsValidator = iitu.edu.kz.service.validation.newsValidator.getInstance();
    }

    public void init() {
        while (true) {
            System.out.println("Enter the number of operation: ");
            for (menuOptions options: menuOptions.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }
            switch (scanner.nextLine()) {
                case "1":
                    getAllNewsOption();
                    break;
                case "2":
                    getNewsByIdOption(scanner);
                    break;
                case "3":
                    createNewsOption(scanner);
                    break;
                case "4":
                    updateNewsOption(scanner);
                    break;
                case "5":
                    removeNewsByIdOption(scanner);
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println(errors.ERROR_COMMAND_NOT_FOUND.getErrorMessage());
            }
        }
    }

    private void getAllNewsOption() {
        System.out.println("Operation: " + menuOptions.GET_ALL_NEWS.getOptionName());
        System.out.println("ID | Title | Content | Create Date | Last Updated Date | Author Id");
        for (NewsDTO dto: newsController.readAll()) {
            System.out.println(dto);
        }
    }

    private void getNewsByIdOption(Scanner scanner) {
        System.out.println("Operation: " + menuOptions.GET_NEWS_BY_ID.getOptionName());
        try {
            System.out.println("Enter news id: ");
            String id = scanner.nextLine();
            newsValidator.checkNewsId(id);
            System.out.println(newsController.readById(Long.parseLong(id)));
        } catch (newsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createNewsOption(Scanner scanner) {
        System.out.println("Operation: " + menuOptions.CREATE_NEWS.getOptionName());
        try {
            System.out.println("Enter news title:");
            String title = scanner.nextLine();
            System.out.println("Enter news content: ");
            String content = scanner.nextLine();
            System.out.println("Enter author id: ");
            String authorId = scanner.nextLine();
            newsValidator.checkNewsAuthorId(authorId);
            NewsDTO newsDTO = new NewsDTO(title, content, Long.parseLong(authorId));
            System.out.println(newsController.create(newsDTO));
        } catch (newsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateNewsOption(Scanner scanner) {
        System.out.println("Operation: " + menuOptions.UPDATE_NEWS.getOptionName());
        try {
            System.out.println("Enter news id: ");
            String id = scanner.nextLine();
            newsValidator.checkNewsId(id);

            System.out.println("Enter news title: ");
            String title = scanner.nextLine();
            System.out.println("Enter news content: ");
            String content = scanner.nextLine();

            System.out.println("Enter author id: ");
            String authorId = scanner.nextLine();
            newsValidator.checkNewsAuthorId(authorId);

            NewsDTO newsDTO = new NewsDTO(title, content, Long.parseLong(authorId));
            newsDTO.setId(Long.parseLong(id));
            System.out.println(newsController.update(newsDTO));
        } catch (newsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeNewsByIdOption(Scanner scanner) {
        System.out.println("Operation: " + menuOptions.REMOVE_NEWS_BY_ID.getOptionName());
        try {
            System.out.println("Enter news id: ");
            String id = scanner.nextLine();
            newsValidator.checkNewsId(id);
            System.out.println(newsController.delete(Long.parseLong(id)));
        } catch (newsException e) {
            System.out.println(e.getMessage());
        }
    }
}