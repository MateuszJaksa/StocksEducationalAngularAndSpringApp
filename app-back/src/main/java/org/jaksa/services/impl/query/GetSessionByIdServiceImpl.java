package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetSessionQuery;
import org.jaksa.mappers.SessionMapper;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.query.GetSessionByIdService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSessionByIdServiceImpl implements GetSessionByIdService {
    private final SessionRepository repository;
    private final SessionMapper mapper;

    @Override
    public GetSessionQuery getSessionById(Long id) {
        return mapper.modelToQuery(repository.getById(id));
    }
}
