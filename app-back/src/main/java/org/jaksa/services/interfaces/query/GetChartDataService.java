package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.GetChartDataQuery;

import java.util.List;

public interface GetChartDataService {
    List<GetChartDataQuery> getChartDataForSessionID(Long sessionId);
}
