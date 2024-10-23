package iitu.edu.kz.service.validation;

import iitu.edu.kz.service.exceptions.errors;
import iitu.edu.kz.service.exceptions.newsException;

public class newsValidator {
    private static final int NEWS_TITLE_MIN = 5;
    private static final int NEWS_TITLE_MAX = 30;
    private static final int NEWS_CONTENT_MIN = 5;
    private static final int NEWS_CONTENT_MAX = 255;

    private newsValidator() {
    }

    private static final class InstanceHolder {
        private static final newsValidator instance = new newsValidator();
    }

    public static newsValidator getInstance() {
        return InstanceHolder.instance;
    }

    public void checkNewsId(String id) throws newsException {
        if (isNumeric(id)) {
            throw new newsException(errors.ERROR_NEWS_ID_FORMAT.getErrorData());
        }
        if (Long.parseLong(id)<1) {
            throw new newsException(errors.ERROR_NEWS_ID_VALUE.getErrorData(id));
        }
    }

    public void checkNewsTitle(String title) throws newsException {
        if (title.length()<NEWS_TITLE_MIN || title.length()>NEWS_TITLE_MAX) {
            throw new newsException(errors.ERROR_NEWS_TITLE_LENGTH.getErrorData(title));
        }
    }

    public void checkNewsContent(String content) throws newsException {
        if (content.length()<NEWS_CONTENT_MIN || content.length()>NEWS_CONTENT_MAX) {
            throw new newsException(errors.ERROR_NEWS_CONTENT_LENGTH.getErrorData(content));
        }
    }

    public void checkNewsAuthorId(String authorId) throws newsException {
        if (isNumeric(authorId)) {
            throw new newsException(errors.ERROR_NEWS_AUTHOR_ID_FORMAT.getErrorData());
        }
        if (Long.parseLong(authorId)<1) {
            throw new newsException(errors.ERROR_NEWS_AUTHOR_ID_VALUE.getErrorData(authorId));
        }
    }

    private boolean isNumeric(String str) {
        char[] chars = str.toCharArray();
        int counter=0;
        if (chars[0]=='-') {
            counter++;
        }
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                counter++;
            }
        }
        return counter != chars.length;
    }
}