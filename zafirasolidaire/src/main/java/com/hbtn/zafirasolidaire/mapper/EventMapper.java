package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hbtn.zafirasolidaire.dto.EventDto;
import com.hbtn.zafirasolidaire.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "photo.url", target = "photoUrl")
    @Mapping(source = "id", target = "eventId")
    @Mapping(source = "category.name", target = "category")
    EventDto eventToDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "photoUrl", target = "photo.url")
    @Mapping(source = "category", target = "category.name")
    Event dtoToEvent(EventDto eventDto);
}
