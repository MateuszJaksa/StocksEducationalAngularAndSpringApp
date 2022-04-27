package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.command.CompanyBetCommand;
import org.jaksa.dtos.command.PlayedGameCommand;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.exceptions.BadCommandContentException;
import org.jaksa.models.*;
import org.jaksa.repositories.InGameUserRepository;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.repositories.StockDataRepository;
import org.jaksa.services.interfaces.command.PlayGameService;
import org.jaksa.services.interfaces.command.UpdateUserStatsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayGameServiceImpl implements PlayGameService {
    private final StockDataRepository stockDataRepository;
    private final OpenGameRepository openGameRepository;
    private final InGameUserRepository inGameUserRepository;
    private final UpdateUserStatsService updateUserStatsService;

    @Override
    public void playGame(PlayedGameCommand command, Long openGameId) {
        try {
            command = validatePlayedGameCommandAndOpenGameId(command, openGameId);
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));
            double score = 0.0;
            SessionModel sessionModel = openGameRepository.getById(openGameId).getSession();
            for (CompanyBetCommand bet : command.getCompanyBets()) {
                if (!bet.getDayBuying().isEmpty() && !bet.getDaySelling().isEmpty()) {
                    Optional<CompanyModel> companyModel = sessionModel.getCompanies().stream().filter
                            (company -> company.getFullName().equals(bet.getCompanyAbbreviation())).findAny();
                    if (companyModel.isPresent()) {
                        StockDataModel boughtDay = stockDataRepository.findByDatetimeAndCompanyModel_Abbreviation(
                                bet.getDayBuying(), companyModel.get().getAbbreviation()).orElse(null);
                        StockDataModel soldDay = stockDataRepository.findByDatetimeAndCompanyModel_Abbreviation(
                                bet.getDaySelling(), companyModel.get().getAbbreviation()).orElse(null);
                        if (boughtDay != null && soldDay != null) {
                            score += bet.getMoneySpent() * (soldDay.getClose() / boughtDay.getClose() - 1.0);
                        }
                    }
                }
            }
            String username = keycloakSecurityContext.getToken().getPreferredUsername();
            InGameUserModel inGameUser = inGameUserRepository.findByUsernameAndOpenGame_Id
                    (username, openGameId);
            inGameUser.setFinalEarnings(score);
            inGameUser.setHasFinished(true);
            inGameUserRepository.save(inGameUser);
            updateUserStatsService.updateUserStatsAfterGame(username, score, score / sessionModel.getFunds());
        } catch (BadCommandContentException be) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }

    @Override
    public void playMockGame(Long openGameId) {
        try {
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));
            String username = keycloakSecurityContext.getToken().getPreferredUsername();
            InGameUserModel inGameUser = inGameUserRepository.findByUsernameAndOpenGame_Id
                    (username, openGameId);
            inGameUser.setHasFinished(true);
            inGameUserRepository.save(inGameUser);
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }

    private PlayedGameCommand validatePlayedGameCommandAndOpenGameId(PlayedGameCommand command, Long openGameId) throws BadCommandContentException {
        if (command == null || command.getCompanyBets() == null || command.getCompanyBets().isEmpty()) {
            throw new BadCommandContentException("emptyBodyExc");
        }
        var openGame = openGameRepository.findById(openGameId);
        if (openGame.isEmpty()) {
            throw new BadCommandContentException("noGameExc");
        }
        if (command.getCompanyBets().stream().mapToInt(b -> Math.toIntExact(b.getMoneySpent()))
                .sum() > openGame.get().getSession().getFunds()) {
            throw new BadCommandContentException("exceedFundsExc");
        }
        if (command.getCompanyBets().stream().anyMatch(b -> b.getMoneySpent() < 0)) {
            throw new BadCommandContentException("negSpendingExc");
        }
        return new PlayedGameCommand(command.getCompanyBets().stream().filter(b -> b.getMoneySpent() > 0).collect(Collectors.toSet()));
    }
}
