package org.jaksa.controllers.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.CreateOpenGameCommand;
import org.jaksa.services.interfaces.command.CreateOpenGameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-games")
public class CreateOpenGameController {
    private final CreateOpenGameService service;

    @PostMapping
    public void create(@RequestBody CreateOpenGameCommand command) {service.createNewOpenGame(command);
    }
}
