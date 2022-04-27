package org.jaksa.services.interfaces.command;

import org.jaksa.dtos.command.CreateSessionCommand;

public interface CreateSessionService {
    void create(CreateSessionCommand command);
    void initialCreate(CreateSessionCommand command);
}
