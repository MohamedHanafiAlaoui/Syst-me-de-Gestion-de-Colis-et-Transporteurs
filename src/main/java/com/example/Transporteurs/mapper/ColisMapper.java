package com.example.Transporteurs.mapper;

import com.example.Transporteurs.dto.ColisDto;
import com.example.Transporteurs.model.Colis;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ColisMapper {

    ColisDto toDto(Colis colis);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Colis toEntity(ColisDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ColisDto dto, @MappingTarget Colis entity);
}
