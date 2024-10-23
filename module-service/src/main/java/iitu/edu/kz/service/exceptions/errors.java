package iitu.edu.kz.service.exceptions;

public enum errors {
    ERROR_NEWS_ID_FORMAT("0001", "News Id should be number"),
    ERROR_NEWS_ID_VALUE("0002", "News id can not be null or less than 1. News id is: "),
    ERROR_NEWS_ID_NOT_EXIST("0003", "News with id {id} does not exist."),
    ERROR_NEWS_TITLE_LENGTH("0011", "News title can not be less than 5 and more than 30 symbols. News title is: "),
    ERROR_NEWS_CONTENT_LENGTH("0012", "News content can not be less than 5 and more than 255 symbols. News content is: "),
    ERROR_NEWS_AUTHOR_ID_FORMAT("0021", "Author Id should be number"),
    ERROR_NEWS_AUTHOR_ID_VALUE("0022", "Author id can not be null or less than 1. Author id is: "),
    ERROR_NEWS_AUTHOR_ID_NOT_EXIST("0023", "Author Id does not exist. Author Id is: "),
    ERROR_COMMAND_NOT_FOUND("0031", "Command not found.");

    private final String errorCode;
    private final String errorMessage;

    errors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorData() {
        return "ERROR_CODE: " +
                getErrorCode() +
                " ERROR_MESSAGE: " +
                getErrorMessage();
    }

    public String getErrorData(Long id, boolean replace) {
        if (replace) {
            return "ERROR_CODE: " +
                    getErrorCode() +
                    " ERROR_MESSAGE: " +
                    getErrorMessage().replace("{id}", String.valueOf(id));
        }
        return "ERROR_CODE: " +
                getErrorCode() +
                " ERROR_MESSAGE: " +
                getErrorMessage() + id;
    }

    public String getErrorData(String input) {
        return "ERROR_CODE: " +
                getErrorCode() +
                " ERROR_MESSAGE: " +
                getErrorMessage() + input;
    }
}