package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.RegisterCommand;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.exceptions.BadCommandContentException;
import org.jaksa.models.UserStatsModel;
import org.jaksa.repositories.UserStatsRepository;
import org.jaksa.services.interfaces.command.RegisterService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserStatsRepository repository;
    private final Keycloak keycloakClient;
    @Value("${keycloak.realm}")
    private String realm;

    @Override
    public void register(RegisterCommand registerForm) {
        try {
            validateRegisterCommand(registerForm);
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(registerForm.getUsername());
            userRepresentation.setEnabled(true);
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(registerForm.getPassword());
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

            Response response = keycloakClient.realm(realm).users().create(userRepresentation);
            if (!HttpStatus.valueOf(response.getStatus()).is2xxSuccessful()) {
                throw new AuthorizationException("keycloakExc");
            }
            repository.save(new UserStatsModel(registerForm.getUsername(),
                    0, 0.0, 0.0, 0.0));
        } catch (BadCommandContentException be) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ae.getMessage());
        }
    }

    private void validateRegisterCommand(RegisterCommand command) throws BadCommandContentException {
        if (command == null) {
            throw new BadCommandContentException("emptyBodyExc");
        }
        if (command.getUsername() == null || command.getUsername().isBlank()) {
            throw new BadCommandContentException("emptyUsernameExc");
        }
        if (keycloakClient.realm(realm).users().list().stream().anyMatch(u -> u.getUsername().equals(command.getUsername()))) {
            throw new BadCommandContentException("takenUsernameExc");
        }
        if (command.getPassword() == null || command.getPassword().isBlank()) {
            throw new BadCommandContentException("emptyPasswordExc");
        }
        if (!command.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$")) {
            throw new BadCommandContentException("wrongPasswordExc");
        }

    }
}
