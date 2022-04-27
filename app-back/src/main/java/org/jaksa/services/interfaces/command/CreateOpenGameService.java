package org.jaksa.services.interfaces.command;

import org.jaksa.dtos.command.CreateOpenGameCommand;

public interface CreateOpenGameService {
    void createNewOpenGame(CreateOpenGameCommand command);
}
