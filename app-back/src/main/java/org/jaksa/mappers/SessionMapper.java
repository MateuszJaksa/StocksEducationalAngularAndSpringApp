package org.jaksa.mappers;

import org.jaksa.dtos.command.CreateSessionCommand;
import org.jaksa.dtos.command.UpdateSessionCommand;
import org.jaksa.dtos.query.GetSessionQuery;
import org.jaksa.models.SessionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SessionMapper {
    @Autowired
    protected CompanyMapper mapper;

    @Mapping(target = "companies", expression = "java(mapper.dtosToModels(command.getCompanies()))")
    public abstract SessionModel createCommandToModel(CreateSessionCommand command);

    public abstract SessionModel updateCommandToModel(UpdateSessionCommand command);

    @Mapping(target = "companies", expression = "java(mapper.modelsToDtos(model.getCompanies()))")
    public abstract GetSessionQuery modelToQuery(SessionModel model);
}