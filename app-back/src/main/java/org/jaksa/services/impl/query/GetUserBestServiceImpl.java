package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.query.GetUserBestQuery;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.models.InGameUserModel;
import org.jaksa.models.OpenGameModel;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.services.interfaces.query.GetUserBestService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserBestServiceImpl implements GetUserBestService {
    private final OpenGameRepository openGameRepository;

    @Override
    public List<GetUserBestQuery> getUserBest() {
        try {
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));
            String username = keycloakSecurityContext.getToken().getPreferredUsername();

            return openGameRepository.findAll().stream().filter(g -> g.getIsFinished() && getUserScoreFromGame(g, username) != null)
                    .map(g -> new GetUserBestQuery(g.getId(), getUserScoreFromGame(g, username), g.getName(), g.getSession().getName())).sorted(new UserResultsByScoreComparator().reversed()).limit(10).toList();
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }

    private Double getUserScoreFromGame(OpenGameModel gameModel, String username) {
        var userModel = gameModel.getUsersInGame().stream().filter(u -> u.getUsername().equals(username) && u.getFinalEarnings() != 0.0).findFirst();
        return userModel.map(InGameUserModel::getFinalEarnings).orElse(null);
    }

    private static class UserResultsByScoreComparator implements Comparator<GetUserBestQuery> {
        @Override
        public int compare(GetUserBestQuery g1, GetUserBestQuery g2) {
            return g1.getScore().compareTo(g2.getScore());
        }
    }
}
