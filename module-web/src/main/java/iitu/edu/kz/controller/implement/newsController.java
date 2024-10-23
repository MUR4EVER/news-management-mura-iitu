package iitu.edu.kz.controller.implement;

import iitu.edu.kz.controller.generalController;
import iitu.edu.kz.service.dto.NewsDTO;
import iitu.edu.kz.service.exceptions.newsException;

import java.util.List;

public class newsController implements generalController<NewsDTO> {
    private final iitu.edu.kz.service.implement.newsService newsService;

    public newsController() {
        newsService = iitu.edu.kz.service.implement.newsService.getInstance();
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) throws newsException {
        return newsService.create(newsDTO);
    }

    @Override
    public List<NewsDTO> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDTO readById(Long id) throws newsException {
        return newsService.readById(id);
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) throws newsException {
        return newsService.update(newsDTO);
    }

    @Override
    public Boolean delete(Long id) throws newsException {
        return newsService.delete(id);
    }
}