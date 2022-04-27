package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetOpenGameQuery;

import java.util.List;

public interface GetOpenGamesService {
    List<GetOpenGameQuery> getGames(Boolean areOpen, String participant, Integer page, Integer size);
}
