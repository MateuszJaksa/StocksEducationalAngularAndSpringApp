package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetChartDataQuery;
import org.jaksa.services.interfaces.query.GetChartDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class GetChartDataBySessionIdController {
    private final GetChartDataService service;

    @GetMapping("/{id}/chart-data")
    public List<GetChartDataQuery> getChartBySessionId(@PathVariable Long id) {
        return service.getChartDataForSessionID(id);
    }
}