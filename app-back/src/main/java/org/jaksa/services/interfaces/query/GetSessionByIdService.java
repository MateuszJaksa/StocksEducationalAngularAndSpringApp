package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetSessionQuery;

public interface GetSessionByIdService {
    GetSessionQuery getSessionById(Long id);
}
