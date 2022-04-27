package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetOpenGameQuery;
import org.jaksa.services.interfaces.query.GetOpenGameByIdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-games")
public class GetOpenGameByIdController {
    private final GetOpenGameByIdService service;

    @GetMapping("/{id}")
    public GetOpenGameQuery getById(@PathVariable Long id) {
        return service.getOpenGameById(id);
    }
}
