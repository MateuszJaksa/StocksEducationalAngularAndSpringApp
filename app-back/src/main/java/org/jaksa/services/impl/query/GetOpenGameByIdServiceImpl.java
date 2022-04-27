package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetOpenGameQuery;
import org.jaksa.mappers.GetOpenGameQueryMapper;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.services.interfaces.query.GetOpenGameByIdService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOpenGameByIdServiceImpl implements GetOpenGameByIdService {
    private final OpenGameRepository openGameRepository;
    private final GetOpenGameQueryMapper queryMapper;

    @Override
    public GetOpenGameQuery getOpenGameById(Long id) {
        return queryMapper.modelToQuery(openGameRepository.getById(id));
    }
}