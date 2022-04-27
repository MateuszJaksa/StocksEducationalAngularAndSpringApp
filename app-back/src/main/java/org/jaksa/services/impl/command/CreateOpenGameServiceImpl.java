package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.command.CreateOpenGameCommand;
import org.jaksa.dtos.query.UserDto;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.exceptions.BadCommandContentException;
import org.jaksa.mappers.CreateOpenGameCommandMapper;
import org.jaksa.models.InGameUserModel;
import org.jaksa.models.OpenGameModel;
import org.jaksa.repositories.InGameUserRepository;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.command.CreateOpenGameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreateOpenGameServiceImpl implements CreateOpenGameService {
    private final OpenGameRepository openGameRepository;
    private final InGameUserRepository inGameUserRepository;
    private final CreateOpenGameCommandMapper commandMapper;
    private final SessionRepository sessionRepository;

    @Override
    public void createNewOpenGame(CreateOpenGameCommand command) {
        try {
            validateCreateOpenGameCommand(command);
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));
            OpenGameModel model = commandMapper.commandToModel(command);
            model.setCreator(keycloakSecurityContext.getToken().getPreferredUsername());
            model.setIsFinished(false);
            openGameRepository.save(model);
            addUserModelsFromUsernames(command.getUsersInGame(), model);
        } catch (BadCommandContentException be) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }

    private void addUserModelsFromUsernames(Set<UserDto> users, OpenGameModel openGame) {
        for (UserDto userDto : users) {
            inGameUserRepository.save(new InGameUserModel(userDto.getUsername(), 0.0, openGame, false));
        }
    }

    private void validateCreateOpenGameCommand(CreateOpenGameCommand command) throws BadCommandContentException {
        if (command == null) {
            throw new BadCommandContentException("emptyBodyExc");
        }
        if (command.getName() == null || command.getName().isBlank()) {
            throw new BadCommandContentException("emptyTitleExc");
        }
        if (openGameRepository.findAll().stream().anyMatch(o -> o.getName().equals(command.getName()))) {
            throw new BadCommandContentException("occTitleExc");
        }
        if (command.getTimeToGuessInSeconds()== null || command.getTimeToGuessInSeconds() < 60) {
            throw new BadCommandContentException("timeToGExc");
        }
        if (command.getUsersInGame() == null || command.getUsersInGame().isEmpty()) {
            throw new BadCommandContentException("noUsersExc");
        }
        if (command.getSessionId() == null || sessionRepository.findAll().stream().noneMatch(s -> Objects.equals(s.getId(), command.getSessionId()))) {
            throw new BadCommandContentException("noSessionExc");
        }
    }
}