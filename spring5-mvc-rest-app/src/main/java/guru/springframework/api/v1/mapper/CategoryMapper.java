package guru.springframework.api.v1.mapper;

import guru.springframework.domain.Category;
import guru.springframework.api.v1.model.CategoryDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //@Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
