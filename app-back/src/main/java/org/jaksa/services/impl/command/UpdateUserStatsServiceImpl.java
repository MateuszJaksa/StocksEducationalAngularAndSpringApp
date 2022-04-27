package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.models.UserStatsModel;
import org.jaksa.repositories.UserStatsRepository;
import org.jaksa.services.interfaces.command.UpdateUserStatsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserStatsServiceImpl implements UpdateUserStatsService {
    private final UserStatsRepository repository;

    @Override
    public void updateUserStatsAfterGame(String username, double absoluteScore, double percentageScore) {
        UserStatsModel statsModel = repository.findByUsername(username);
        if (statsModel != null) {
            statsModel.setGamesPlayed(statsModel.getGamesPlayed() + 1);
            statsModel.setTotalScore(statsModel.getTotalScore() + absoluteScore);
            if (statsModel.getHighestScore() < absoluteScore) {
                statsModel.setHighestScore(absoluteScore);
            }
            statsModel.setAverageScore((statsModel.getAverageScore() * (statsModel.getGamesPlayed() - 1) + percentageScore) / statsModel.getGamesPlayed());
            repository.save(statsModel);
        }
    }
}