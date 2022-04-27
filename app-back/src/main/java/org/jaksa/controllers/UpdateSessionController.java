package org.jaksa.controllers;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.UpdateSessionCommand;
import org.jaksa.services.interfaces.command.UpdateSessionService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class UpdateSessionController {
    private final UpdateSessionService service;

    @PutMapping
    public void update(@RequestBody UpdateSessionCommand command) {
        service.update(command);
    }
}
