package org.jaksa.mappers;

import org.jaksa.dtos.query.GetUserStatsQuery;
import org.jaksa.models.UserStatsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserStatsQueryMapper {
    GetUserStatsQuery modelToQuery(UserStatsModel model);
}
