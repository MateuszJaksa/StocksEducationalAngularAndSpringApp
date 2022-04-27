package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.query.GetUserStatsQuery;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.mappers.GetUserStatsQueryMapper;
import org.jaksa.repositories.UserStatsRepository;
import org.jaksa.services.interfaces.query.GetUserStatsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GetUserStatsServiceImpl implements GetUserStatsService {
    private final UserStatsRepository repository;
    private final GetUserStatsQueryMapper mapper;

    @Override
    public GetUserStatsQuery getUserStats() {
        try {
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));
            return mapper.modelToQuery(repository.findByUsername(keycloakSecurityContext.getToken().getPreferredUsername()));
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }
}
