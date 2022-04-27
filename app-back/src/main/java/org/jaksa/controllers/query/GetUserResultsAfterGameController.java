package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetUserResultQuery;
import org.jaksa.services.interfaces.query.GetUserResultsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-games")
public class GetUserResultsAfterGameController {
    private final GetUserResultsService service;

    @GetMapping("/{id}/results")
    public GetUserResultQuery getById(@PathVariable Long id) {
        return service.getUserResults(id);
    }
}