package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetUserResultQuery;

public interface GetUserResultsService {
    GetUserResultQuery getUserResults(Long openGameId);
}
