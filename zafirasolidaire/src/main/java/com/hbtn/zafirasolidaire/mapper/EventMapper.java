package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.dto.RequestEventDto;
import com.hbtn.zafirasolidaire.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "createDate", target = "createDate"),
        @Mapping(source = "updateDate", target = "updateDate"),
        @Mapping(source = "category.name", target = "category"),
        @Mapping(source = "date", target = "date"),
        @Mapping(source = "capacity", target = "capacity"),
        @Mapping(source = "photo.url", target = "photoUrl")
    })
    EventDto eventToDto(Event event);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createDate", ignore = true),
        @Mapping(target = "updateDate", ignore = true),
        @Mapping(source = "category", target = "category.name"),
        @Mapping(source = "date", target = "date"),
        @Mapping(source = "capacity", target = "capacity"),
        @Mapping(source = "photoUrl", target = "photo.url")
    })
    Event requestDtoToEvent(RequestEventDto dto);
}
