package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.hbtn.zafirasolidaire.dto.PhotoDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.model.Photo;



@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "createDate", target = "createDate"),
        @Mapping(source = "updateDate", target = "updateDate"),
        @Mapping(source = "user.id", target = "userId"),
        @Mapping(source = "event.id", target = "eventId"),
        @Mapping(source = "blogPost.id", target = "blogPostId"),
        @Mapping(source = "url", target = "url")
    })
    PhotoDto photoToDto(Photo photo);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createDate", ignore = true),
        @Mapping(target = "updateDate", ignore = true),
        @Mapping(target = "user", ignore = true),
        @Mapping(target = "event", ignore = true),
        @Mapping(target = "blogPost", ignore = true),
        @Mapping(source = "url", target = "url")
    })
    Photo requestDtoToPhoto(RequestPhotoDto dto);
}
