package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetSessionQuery;

import java.util.List;

public interface GetSessionsService {
    List<GetSessionQuery> getSessions(Integer page, Integer size, String creator);
}
