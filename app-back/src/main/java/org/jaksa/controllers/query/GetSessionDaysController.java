package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetSessionDaysQuery;
import org.jaksa.services.interfaces.query.GetSessionDaysService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class GetSessionDaysController {
    private final GetSessionDaysService service;

    @GetMapping("/{id}/days")
    public GetSessionDaysQuery getById(@PathVariable Long id) {
        return service.getSessionDays(id);
    }
}