package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.Consts;
import org.jaksa.config.KeycloakContextHolder;
import org.jaksa.dtos.query.GetBestResultsQuery;
import org.jaksa.dtos.query.GetUserResultQuery;
import org.jaksa.dtos.query.UserResultDto;
import org.jaksa.exceptions.AuthorizationException;
import org.jaksa.models.*;
import org.jaksa.repositories.InGameUserRepository;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.services.interfaces.query.GetUserResultsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUserResultsServiceImpl implements GetUserResultsService {
    private final OpenGameRepository openGameRepository;
    private final InGameUserRepository inGameUserRepository;

    @Override
    public GetUserResultQuery getUserResults(Long openGameId) {
        try {
            var keycloakSecurityContext = KeycloakContextHolder.getKeycloakPrincipal()
                    .orElseThrow(() -> new AuthorizationException("notLoggedExc"));
            OpenGameModel openGameModel = openGameRepository.getById(openGameId);
            Set<UserResultDto> userPairs = openGameModel.getUsersInGame().stream()
                    .map(user -> new UserResultDto(user.getUsername(), user.getFinalEarnings())).collect(Collectors.toSet());
            Double ownResult = inGameUserRepository.findByUsernameAndOpenGame_Id
                    (keycloakSecurityContext.getToken().getPreferredUsername(), openGameId).getFinalEarnings();
            return new GetUserResultQuery(ownResult, userPairs, calculateBondsGain(openGameId), calculateBestPossibleResults(openGameModel.getSession()));
        } catch (AuthorizationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ae.getMessage());
        }
    }

    private GetBestResultsQuery calculateBestPossibleResults(SessionModel sessionModel) {
        double highestEarnings = 0.0;
        GetBestResultsQuery getBestResultsQuery = new GetBestResultsQuery("", 0.0, "", "");
        for (CompanyModel company: sessionModel.getCompanies()) {
            double max = company.getStockDataModels().stream().map(StockDataModel::getClose).max(Double::compareTo).orElse(0.0);
            double min = company.getStockDataModels().stream().map(StockDataModel::getClose).min(Double::compareTo).orElse(0.0);
            var buyingStockData = company.getStockDataModels().stream().filter(c -> c.getClose() == min).findFirst();
            var sellingStockData = company.getStockDataModels().stream().filter(c -> c.getClose() == max).findFirst();
            double currentBest = sessionModel.getFunds() * max / min;
            if (currentBest > highestEarnings && buyingStockData.isPresent() && sellingStockData.isPresent()) {
                getBestResultsQuery = new GetBestResultsQuery(company.getFullName(), currentBest,
                        buyingStockData.get().getDatetime(), sellingStockData.get().getDatetime());
                highestEarnings = currentBest;
            }
        }
        return getBestResultsQuery;
    }

    private Double calculateBondsGain(Long openGameId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SessionModel model = openGameRepository.getById(openGameId).getSession();
        LocalDate startingDate = LocalDate.parse(model.getStartingDate(), dtf);
        LocalDate endingDate = LocalDate.parse(model.getEndingDate(), dtf);
        long daysBetween = ChronoUnit.DAYS.between(startingDate, endingDate);
        return model.getFunds() * Math.pow(1 + (Consts.YEARLY_BOND_RETURN / 365), daysBetween) - model.getFunds();
    }
}