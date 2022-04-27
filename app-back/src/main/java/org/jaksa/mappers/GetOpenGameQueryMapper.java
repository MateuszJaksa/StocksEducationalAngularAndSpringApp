package org.jaksa.mappers;

import org.jaksa.dtos.query.GetOpenGameQuery;
import org.jaksa.models.InGameUserModel;
import org.jaksa.models.OpenGameModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GetOpenGameQueryMapper {
    @Autowired
    protected SessionMapper mapper;

    @Mapping(target = "usersInGameNames", expression = "java(getUsernameListFromOpenGame(model))")
    @Mapping(target = "session", expression = "java(mapper.modelToQuery(model.getSession()))")
    public abstract GetOpenGameQuery modelToQuery(OpenGameModel model);

    public Set<String> getUsernameListFromOpenGame(OpenGameModel model) {
        return model.getUsersInGame().stream().map(InGameUserModel::getUsername).collect(Collectors.toSet());
    }
}
