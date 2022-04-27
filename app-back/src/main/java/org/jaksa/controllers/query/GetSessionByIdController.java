package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetSessionQuery;
import org.jaksa.services.interfaces.query.GetSessionByIdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class GetSessionByIdController {
    private final GetSessionByIdService service;

    @GetMapping("/{id}")
    public GetSessionQuery getById(@PathVariable Long id) {
        return service.getSessionById(id);
    }
}
