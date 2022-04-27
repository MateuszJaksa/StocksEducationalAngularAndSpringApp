package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetOpenGameQuery;

public interface GetOpenGameByIdService {
    GetOpenGameQuery getOpenGameById(Long id);
}
