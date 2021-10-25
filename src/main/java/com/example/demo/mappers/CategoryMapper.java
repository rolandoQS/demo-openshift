package com.example.demo.mappers;

import com.example.demo.Request.CreateCategoryRequest;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.models.Category;
import com.example.demo.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Mapper(uses = {CommonMapper.class})
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryEntityToCategory(CategoryEntity categoryEntity);
    CategoryEntity categoryToCategoryEntity(Category category);
    List<Category> listCategoryEntityToListCategory(List<CategoryEntity> categoryEntityList);
    CategoryResponse categoryToCategoryResponse(Category category);
    List<CategoryResponse> categoryListToCategoryResponse(List<Category> category);

    @Mapping(target = "length", source = "length", qualifiedByName = "formatLength")
    Category categoryRequestToCategory(CreateCategoryRequest createCategoryRequest);


    @Named("formatLength")
    default String formatLength (Long seconds){
       if (seconds==null){
           return "";
       }

        String length ="";

        long hours = TimeUnit.SECONDS.toHours(seconds);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

        if (hours > 0L){
            length = hours+":"+minute+":"+second;
        }else if(hours == 0L && minute >0L){
            length = minute+":"+second;
        }else if(hours == 0L && minute == 0L){
            length = ""+second;
        }

        return length;
    }

}
