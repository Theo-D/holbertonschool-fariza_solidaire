package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.hbtn.zafirasolidaire.dto.EventCategoryDto;
import com.hbtn.zafirasolidaire.dto.RequestEventCategoryDto;
import com.hbtn.zafirasolidaire.model.EventCategory;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "createDate", target = "createDate"),
        @Mapping(source = "updateDate", target = "updateDate"),
        @Mapping(source = "name", target = "name")
    })
    EventCategoryDto eventCategoryToDto(EventCategory eventCategory);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createDate", ignore = true),
        @Mapping(target = "updateDate", ignore = true),
        @Mapping(source = "name", target = "name")
    })
    EventCategory requestDtoToEventCategory(RequestEventCategoryDto dto);
}
