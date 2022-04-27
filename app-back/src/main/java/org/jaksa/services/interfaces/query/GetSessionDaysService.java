package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetSessionDaysQuery;

public interface GetSessionDaysService {
    GetSessionDaysQuery getSessionDays(Long sessionId);
}
