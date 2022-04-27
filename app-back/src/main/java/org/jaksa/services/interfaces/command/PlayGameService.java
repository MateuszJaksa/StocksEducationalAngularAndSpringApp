package org.jaksa.services.interfaces.command;

import org.jaksa.dtos.command.PlayedGameCommand;

public interface PlayGameService {
    void playGame(PlayedGameCommand command, Long openGameId);
    void playMockGame(Long openGameId);
}
