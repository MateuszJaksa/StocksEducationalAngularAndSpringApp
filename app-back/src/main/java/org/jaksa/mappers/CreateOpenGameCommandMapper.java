package org.jaksa.mappers;

import org.jaksa.dtos.command.CreateOpenGameCommand;
import org.jaksa.models.OpenGameModel;
import org.jaksa.repositories.SessionRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CreateOpenGameCommandMapper {
    @Autowired
    protected SessionRepository repository;

    @Mapping(target = "session", expression = "java(repository.getById(command.getSessionId()))")
    @Mapping(target = "usersInGame", ignore = true)
    public abstract OpenGameModel commandToModel(CreateOpenGameCommand command);
}
