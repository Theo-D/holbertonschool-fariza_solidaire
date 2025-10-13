package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hbtn.zafirasolidaire.dto.PhotoDto;
import com.hbtn.zafirasolidaire.model.Photo;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "blogPost.id", target = "blogPostId")
    @Mapping(source = "user.id", target = "userId")
    PhotoDto photoToDto(Photo photo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "blogPost", ignore = true)
    @Mapping(target = "user", ignore = true)
    Photo dtoToPhoto(PhotoDto photoDto);
}
