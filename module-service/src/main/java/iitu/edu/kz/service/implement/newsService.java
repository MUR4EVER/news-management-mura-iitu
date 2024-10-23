package iitu.edu.kz.service.implement;

import iitu.edu.kz.repository.implement.newsRepository;
import iitu.edu.kz.repository.model.NewsModel;
import iitu.edu.kz.service.dto.NewsDTO;
import iitu.edu.kz.service.generalService;
import iitu.edu.kz.service.exceptions.errors;
import iitu.edu.kz.service.exceptions.newsException;
import iitu.edu.kz.service.mapping.NewsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class newsService implements generalService<NewsDTO> {
    private final iitu.edu.kz.repository.implement.newsRepository newsRepository;
    private final iitu.edu.kz.service.validation.newsValidator newsValidator;
    private final List<NewsDTO> newsDTOList;

    private newsService() {
        newsRepository = new newsRepository();
        newsValidator = iitu.edu.kz.service.validation.newsValidator.getInstance();
        newsDTOList = new ArrayList<>();
    }

    private static final class InstanceHolder {
        private static final newsService instance = new newsService();
    }

    public static newsService getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) throws newsException {
        newsValidator.checkNewsTitle(newsDTO.getTitle());
        newsValidator.checkNewsContent(newsDTO.getContent());
        newsValidator.checkNewsAuthorId(String.valueOf(newsDTO.getAuthorId()));
        if (doesAuthorIdExist(newsDTO.getAuthorId())) {
            throw new newsException(errors.ERROR_NEWS_AUTHOR_ID_NOT_EXIST.getErrorData(newsDTO.getAuthorId(),false));
        }
        NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsDTOToNews(newsDTO));
        return NewsMapper.INSTANCE.newsToNewsDTO(newsModel);
    }

    @Override
    public List<NewsDTO> readAll() {
        newsDTOList.clear();
        for (NewsModel newsModel : newsRepository.readAll()) {
            newsDTOList.add(NewsMapper.INSTANCE.newsToNewsDTO(newsModel));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO readById(Long id) throws newsException {
        NewsDTO newsDTO = NewsMapper.INSTANCE.newsToNewsDTO(newsRepository.readById(id));
        if (newsDTO!=null) {
            return newsDTO;
        } else {
            throw new newsException(errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(id,true));
        }
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) throws newsException {
        if (readById(newsDTO.getId())==null) {
            throw new newsException(errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(newsDTO.getId(),true));
        }
        newsValidator.checkNewsTitle(newsDTO.getTitle());
        newsValidator.checkNewsContent(newsDTO.getContent());
        newsValidator.checkNewsAuthorId(String.valueOf(newsDTO.getAuthorId()));
        if (doesAuthorIdExist(newsDTO.getAuthorId())) {
            throw new newsException(errors.ERROR_NEWS_AUTHOR_ID_NOT_EXIST.getErrorData(newsDTO.getAuthorId(),false));
        }
        NewsModel newsModel = newsRepository.update(NewsMapper.INSTANCE.newsDTOToNews(newsDTO));
        return NewsMapper.INSTANCE.newsToNewsDTO(newsModel);
    }

    @Override
    public Boolean delete(Long id) throws newsException {
        if (readById(id)==null) {
            throw new newsException(errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(id,true));
        }
        return newsRepository.delete(id);
    }

    private boolean doesAuthorIdExist(Long authorId) {
        for (iitu.edu.kz.repository.model.authorModel authorModel : newsRepository.getAuthorList()) {
            if (Objects.equals(authorId, authorModel.getId())) {
                return false;
            }
        }
        return true;
    }
}