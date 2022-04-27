package org.jaksa.controllers.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.PlayedGameCommand;
import org.jaksa.services.interfaces.command.PlayGameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-games")
public class PostPlayingCommandController {
    private final PlayGameService service;

    @PostMapping("/{id}/results")
    public void create(@RequestBody PlayedGameCommand command, @PathVariable Long id) {
        service.playGame(command, id);
    }

    @PostMapping("/{id}/mockResults")
    public void mockCreate(@PathVariable Long id) {
        service.playMockGame(id);
    }
}
