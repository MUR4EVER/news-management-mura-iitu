package iitu.edu.kz.service.mapping;

import iitu.edu.kz.repository.model.NewsModel;
import iitu.edu.kz.service.dto.NewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO newsToNewsDTO(NewsModel newsModel);

    NewsModel newsDTOToNews(NewsDTO newsDTO);
}