package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetUserBestQuery;

import java.util.List;

public interface GetUserBestService {
    List<GetUserBestQuery> getUserBest();
}
