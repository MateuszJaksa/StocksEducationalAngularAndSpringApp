package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetOpenGameQuery;
import org.jaksa.services.interfaces.query.GetOpenGamesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-games")
public class GetAllOpenGamesController {
    private final GetOpenGamesService service;

    @GetMapping()
    public List<GetOpenGameQuery> getAll(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size,
                                         @RequestParam(required = false, defaultValue = "false") Boolean areOpen,
                                         @RequestParam(required = false) String participant) {
        return service.getGames(areOpen, participant, page, size);
    }
}
