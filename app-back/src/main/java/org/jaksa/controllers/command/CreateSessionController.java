package org.jaksa.controllers.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.CreateSessionCommand;
import org.jaksa.services.interfaces.command.CreateSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class CreateSessionController {
    private final CreateSessionService service;

    @PostMapping
    public void create(@RequestBody CreateSessionCommand command) { service.create(command);
    }
}
