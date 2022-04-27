package org.jaksa.mappers;

import org.jaksa.dtos.query.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto representationToDto(UserRepresentation representation);
}
