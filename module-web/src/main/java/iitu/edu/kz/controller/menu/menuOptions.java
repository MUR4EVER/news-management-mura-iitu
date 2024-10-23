package iitu.edu.kz.controller.menu;

public enum menuOptions {
    GET_ALL_NEWS("1 |", "Get all news."),
    GET_NEWS_BY_ID("2 |", "Get news by id."),
    CREATE_NEWS("3 |", "Create news."),
    UPDATE_NEWS("4 |", "Update news."),
    REMOVE_NEWS_BY_ID("5 |", "Remove news by id."),
    EXIT("0 |", "Exit.");

    private final String optionCode;
    private final String optionName;

    menuOptions(String optionCode, String optionName) {
        this.optionCode = optionCode;
        this.optionName = optionName;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public String getOptionName() {
        return optionName;
    }

}