package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetSessionQuery;
import org.jaksa.exceptions.PaginationException;
import org.jaksa.mappers.SessionMapper;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.query.GetSessionsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSessionsServiceImpl implements GetSessionsService {
    private final SessionRepository repository;
    private final SessionMapper mapper;

    @Override
    public List<GetSessionQuery> getSessions(Integer page, Integer size, String creator) {
        try {
            validatePageNumberAndSizeIfExist(page, size);
            if (creator == null || creator.isBlank()) {
                return getAllSessions(page, size);
            } else {
                return getSessionsByCreator(creator, page, size);
            }
        } catch (PaginationException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, pe.getMessage());
        }
    }

    private List<GetSessionQuery> getAllSessions(Integer page, Integer size) {
        if (page != null && size != null) {
            return repository.findAll(PageRequest.of(page, size)).stream().map(mapper::modelToQuery).toList();
        }
        return repository.findAll().stream().map(mapper::modelToQuery).toList();
    }

    private List<GetSessionQuery> getSessionsByCreator(String creator, Integer page, Integer size) {
        if (page != null && size != null) {
            return repository.findByCreator(creator, PageRequest.of(page, size)).stream().map(mapper::modelToQuery).toList();
        }
        return repository.findByCreator(creator).stream().map(mapper::modelToQuery).toList();
    }

    private void validatePageNumberAndSizeIfExist(Integer page, Integer size) throws PaginationException {
        if (page != null && size != null) {
            if (page < 0 || size < 1) {
                throw new PaginationException("paginationExc");
            }
        }
    }
}
