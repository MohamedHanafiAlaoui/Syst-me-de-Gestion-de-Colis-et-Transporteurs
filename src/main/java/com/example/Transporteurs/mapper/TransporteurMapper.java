package com.example.Transporteurs.mapper;

import com.example.Transporteurs.dto.TransporteurDto;
import com.example.Transporteurs.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransporteurMapper {

    @Mapping(target = "password", ignore = true)
    TransporteurDto toDto(User user);

    User toEntity(TransporteurDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDto(TransporteurDto dto, @MappingTarget User entity);
}
