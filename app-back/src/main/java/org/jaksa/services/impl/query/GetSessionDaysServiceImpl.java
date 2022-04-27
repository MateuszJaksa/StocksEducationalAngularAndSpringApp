package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetSessionDaysQuery;
import org.jaksa.models.SessionDayModel;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.query.GetSessionDaysService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GetSessionDaysServiceImpl implements GetSessionDaysService {
    private final SessionRepository repository;

    @Override
    public GetSessionDaysQuery getSessionDays(Long sessionId) {
        var sessionModel = repository.findById(sessionId);
        return sessionModel.map(model -> new GetSessionDaysQuery(model.getSessionDayModels().stream()
                .map(SessionDayModel::getDatetime).toList())).orElseGet(() -> new GetSessionDaysQuery(new ArrayList<>()));
    }

}
