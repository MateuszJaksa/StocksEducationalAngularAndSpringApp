package org.jaksa.services.interfaces.command;

public interface UpdateUserStatsService {
    void updateUserStatsAfterGame(String username, double absoluteScore, double percentageScore);
}
