package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.command.CreateSessionCommand;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.exceptions.BadCommandContentException;
import org.jaksa.mappers.SessionMapper;
import org.jaksa.models.SessionModel;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.command.CacheStockDataService;
import org.jaksa.services.interfaces.command.CreateSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class CreateSessionServiceImpl implements CreateSessionService {
    private final SessionRepository repository;
    private final SessionMapper mapper;
    private final CacheStockDataService service;

    @Override
    public void create(CreateSessionCommand command) {
        try {
            validateCreateSessionCommand(command);
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));

            SessionModel model = mapper.createCommandToModel(command);
            model.setCreator(keycloakSecurityContext.getToken().getPreferredUsername());
            repository.save(model);
            service.cacheStockDataOfSession(model);
        } catch (BadCommandContentException be) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }

    @Override
    public void initialCreate(CreateSessionCommand command) {
        SessionModel model = mapper.createCommandToModel(command);
        model.setCreator("-");
        repository.save(model);
        service.cacheStockDataOfSession(model);
    }

    private void validateCreateSessionCommand(CreateSessionCommand command) throws BadCommandContentException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (command == null) {
            throw new BadCommandContentException("emptyBodyExc");
        }
        if (command.getName() == null || command.getName().isBlank()) {
            throw new BadCommandContentException("emptyTitleExc");
        }
        if (repository.findAll().stream().anyMatch(o -> o.getName().equals(command.getName()))) {
            throw new BadCommandContentException("occTitleExc");
        }
        if (command.getStartingDate() == null) {
            throw new BadCommandContentException("emptyStDateExc");
        }
        if (command.getEndingDate() == null) {
            throw new BadCommandContentException("emptyEDAteExc");
        }
        if (command.getFunds() == null || command.getFunds() < 100) {
            throw new BadCommandContentException("noSufFundsExc");
        }
        if (command.getCompanies() == null || command.getCompanies().isEmpty() || command.getCompanies().size() > 6) {
            throw new BadCommandContentException("wrongCompAddedExc");
        }
        LocalDate startingDate;
        LocalDate endingDate;
        try {
            startingDate = LocalDate.parse(command.getStartingDate(), dtf);
            endingDate = LocalDate.parse(command.getEndingDate(), dtf);
            if (startingDate == null || endingDate == null) {
                throw new BadCommandContentException("dateFormatExc");
            }
            if (startingDate.isAfter(endingDate) || startingDate.isEqual(endingDate)) {
                throw new BadCommandContentException("endBeforeStartExc");
            }
            if (!endingDate.isBefore(LocalDate.now())) {
                throw new BadCommandContentException("notHistoricalExc");
            }
        } catch (DateTimeParseException e) {
            throw new BadCommandContentException("dateFormatExc");
        }
    }
}
