package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetSessionQuery;
import org.jaksa.services.interfaces.query.GetSessionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class GetAllSessionsController {
    private final GetSessionsService service;

    @GetMapping()
    public List<GetSessionQuery> getAll(@RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size,
                                        @RequestParam(required = false) String creator) {
        return service.getSessions(page, size, creator);
    }
}
