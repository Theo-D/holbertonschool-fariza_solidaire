package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.model.BlogPost;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {

    @Mapping(source = "photo.url", target = "photoUrl")
    @Mapping(source = "id", target = "blogPostId")
    BlogPostDto blogPostToDto(BlogPost blogPost);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "photoUrl", target = "photo.url")
    BlogPost dtoToBlogPost(BlogPostDto eventDto);
}
