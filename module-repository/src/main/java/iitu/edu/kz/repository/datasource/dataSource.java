package iitu.edu.kz.repository.datasource;

import iitu.edu.kz.repository.model.authorModel;
import iitu.edu.kz.repository.model.NewsModel;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dataSource {
    private List<authorModel> authorModelList;
    private List<NewsModel> newsModelList;
    private Long authorId;
    private Long newsId;

    private dataSource() {
        authorId = 1L;
        newsId = 1L;
        readAuthorListFromDataSource();
        readNewsListFromDataSource();
    }

    private static final class InstanceHolder {
        private static final dataSource instance = new dataSource();
    }

    public static dataSource getInstance() {
        return InstanceHolder.instance;
    }

    public List<authorModel> getAuthorList() {
        return authorModelList;
    }

    public List<NewsModel> getNewsList() {
        return newsModelList;
    }

    private void readAuthorListFromDataSource() {
        authorModelList = new ArrayList<>();
        String AUTHOR_PATH = "module-repository/src/main/resources/authors.txt";
        List<String> authorNames = readFromDataSource(AUTHOR_PATH);
        for (String _ : authorNames) {
            authorModel authorModel = new authorModel(authorId);
            authorModelList.add(authorModel);
            authorId++;
        }
    }

    private void readNewsListFromDataSource() {
        newsModelList = new ArrayList<>();
        String CONTENT_PATH = "module-repository/src/main/resources/content.txt";
        List<String> contentString = readFromDataSource(CONTENT_PATH);
        String NEWS_PATH = "module-repository/src/main/resources/news.txt";
        List<String> newsString = readFromDataSource(NEWS_PATH);

        List<String> newsTitles = new ArrayList<>();
        List<String> newsCreateDates = new ArrayList<>();
        List<String> newsLastUpdatedDates = new ArrayList<>();
        List<String> newsAuthorIds = new ArrayList<>();

        for (int i = 0; i < newsString.size(); i += 4) {
            if (i + 3 < newsString.size()) {
                newsTitles.add(newsString.get(i));
                newsCreateDates.add(newsString.get(i + 1));
                newsLastUpdatedDates.add(newsString.get(i + 2));
                newsAuthorIds.add(newsString.get(i + 3));
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        for (int i = 0; i < 20 && i < newsTitles.size(); i++) {
            try {
                LocalDateTime createDate = LocalDateTime.parse(newsCreateDates.get(i), formatter);
                LocalDateTime lastUpdatedDate = LocalDateTime.parse(newsLastUpdatedDates.get(i), formatter);
                NewsModel newsModel = new NewsModel(newsId, newsTitles.get(i), contentString.size() > i ? contentString.get(i) : "No content available",
                        createDate, lastUpdatedDate, Long.parseLong(newsAuthorIds.get(i)));
                newsModelList.add(newsModel);
                newsId++;
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date for news with ID: " + newsId + ", error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error: insufficient data to create news with ID: " + newsId);
            }
        }
    }

    private List<String> readFromDataSource(String filepath) {
        List<String> lines = new ArrayList<>();
        try {
            File f = new File(filepath);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + filepath);
        }
        return lines;
    }
}
