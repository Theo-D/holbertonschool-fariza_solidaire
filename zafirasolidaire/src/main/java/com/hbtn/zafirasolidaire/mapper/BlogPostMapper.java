package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.dto.RequestBlogPostDto;
import com.hbtn.zafirasolidaire.model.BlogPost;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "photo.url", target = "photoUrl"),
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "textBody", target = "textBody"),
        @Mapping(source = "author", target = "author"),
        @Mapping(source = "createDate", target = "createDate"),
        @Mapping(source = "updateDate", target = "updateDate")
    })
    BlogPostDto blogPostToDto(BlogPost blogPost);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "photoUrl", target = "photo.url"),
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "textBody", target = "textBody"),
        @Mapping(source = "author", target = "author"),
        @Mapping(target = "createDate", ignore = true),
        @Mapping(target = "updateDate", ignore = true)
    })
    BlogPost requestDtoToBlogPost(RequestBlogPostDto dto);
}
