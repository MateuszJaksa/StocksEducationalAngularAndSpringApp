package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetUserStatsQuery;
import org.jaksa.services.interfaces.query.GetUserStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-stats")
public class GetUserStatsController {
    private final GetUserStatsService service;

    @GetMapping()
    public GetUserStatsQuery getUserStats() {
        return service.getUserStats();
    }
}
