package org.jaksa.services.impl;

import lombok.RequiredArgsConstructor;
import org.jaksa.models.InGameUserModel;
import org.jaksa.models.OpenGameModel;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.services.interfaces.ScheduledFinishingGameService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ScheduledFinishingGameServiceImpl implements ScheduledFinishingGameService {
    private final OpenGameRepository openGameRepository;
    private static final Long HOURS_TO_EXPIRE = 24L;

    Long zonedDateTimeDifference(ZonedDateTime d1, ZonedDateTime d2){
        return ChronoUnit.HOURS.between(d1, d2);
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    @Override
    public void finishGamesWhenObsolete() {
        for (OpenGameModel openGame : openGameRepository.findAll()) {
            if (!openGame.getIsFinished() && checkIfGameClosed(openGame)) {
                openGame.setIsFinished(true);
                openGame.getUsersInGame().forEach(u -> u.setHasFinished(true));
                openGameRepository.save(openGame);
            }
        }
    }

    private boolean checkIfGameClosed(OpenGameModel openGame) {
        for (InGameUserModel inGameUser : openGame.getUsersInGame()) {
            if (!inGameUser.getHasFinished()) {
                return zonedDateTimeDifference(openGame.getCreatedDate(), ZonedDateTime.now()) >= HOURS_TO_EXPIRE;
            }
        }
        return true;
    }
}
