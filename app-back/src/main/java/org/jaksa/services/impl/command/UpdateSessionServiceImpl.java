package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.command.UpdateSessionCommand;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.mappers.SessionMapper;
import org.jaksa.models.SessionModel;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.command.UpdateSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UpdateSessionServiceImpl implements UpdateSessionService {
    private final SessionRepository repository;
    private final SessionMapper mapper;

    @Override
    public void update(UpdateSessionCommand command) {
        try {
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));

            SessionModel model = mapper.updateCommandToModel(command);
            model.setCreator(keycloakSecurityContext.getToken().getPreferredUsername());
            repository.save(model);
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }
}
