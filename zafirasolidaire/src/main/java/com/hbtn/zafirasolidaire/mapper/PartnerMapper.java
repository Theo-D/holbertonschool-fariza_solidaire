package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hbtn.zafirasolidaire.dto.PartnerDto;
import com.hbtn.zafirasolidaire.dto.RequestPartnerDto;
import com.hbtn.zafirasolidaire.model.Partner;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    // Entity -> Response DTO
    PartnerDto partnerToDto(Partner partner);

    // Request DTO -> Entity
    @Mapping(target = "id", ignore = true) // id is generated, so ignore
    @Mapping(target = "createDate", ignore = true) // set by BaseModel or service
    @Mapping(target = "updateDate", ignore = true) // set by BaseModel or service
    Partner requestDtoToPartner(RequestPartnerDto dto);
}
