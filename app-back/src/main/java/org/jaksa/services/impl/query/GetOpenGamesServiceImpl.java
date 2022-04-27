package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetOpenGameQuery;
import org.jaksa.exceptions.PaginationException;
import org.jaksa.mappers.GetOpenGameQueryMapper;
import org.jaksa.repositories.OpenGameRepository;
import org.jaksa.services.interfaces.query.GetOpenGamesService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOpenGamesServiceImpl implements GetOpenGamesService {
    private final OpenGameRepository openGameRepository;
    private final GetOpenGameQueryMapper queryMapper;

    @Override
    public List<GetOpenGameQuery> getGames(Boolean areOpen, String participant, Integer page, Integer size) {
        try {
            validatePageNumberAndSizeIfExist(page, size);
            if (areOpen) {
                if (participant == null || participant.isBlank()) {
                    return getAllOpenGames(page, size);
                } else {
                    return getOpenGamesByParticipantUsername(participant, page, size);
                }
            } else {
                if (participant == null || participant.isBlank()) {
                    return getAllGames(page, size);
                } else {
                    return getAllGamesByParticipantUsername(participant, page, size);
                }
            }
        } catch (PaginationException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, pe.getMessage());
        }
    }

    private void validatePageNumberAndSizeIfExist(Integer page, Integer size) throws PaginationException {
        if (page != null && size != null) {
            if (page < 0 || size < 1) {
                throw new PaginationException("paginationExc");
            }
        }
    }


    private List<GetOpenGameQuery> getAllGames(Integer page, Integer size) {
        if (page != null && size != null) {
            return openGameRepository.findAll(PageRequest.of(page, size)).stream().map(queryMapper::modelToQuery).toList();
        }
        return openGameRepository.findAll().stream().map(queryMapper::modelToQuery).toList();
    }

    private List<GetOpenGameQuery> getAllOpenGames(Integer page, Integer size) {
        if (page != null && size != null) {
            return openGameRepository.findByIsFinishedIsFalse(PageRequest.of(page, size)).stream().map(queryMapper::modelToQuery).toList();
        }
        return openGameRepository.findByIsFinishedIsFalse().stream().map(queryMapper::modelToQuery).toList();
    }

    private List<GetOpenGameQuery> getAllGamesByParticipantUsername(String username, Integer page, Integer size) {
        if (page != null && size != null) {
            return openGameRepository.findByUsersInGame_UsernameEquals(username, PageRequest.of(page, size))
                    .stream().map(queryMapper::modelToQuery).toList();
        }
        return openGameRepository.findByUsersInGame_UsernameEquals(username)
                .stream().map(queryMapper::modelToQuery).toList();
    }

    private List<GetOpenGameQuery> getOpenGamesByParticipantUsername(String username, Integer page, Integer size) {
        if (page != null && size != null) {
            return openGameRepository.findByIsFinishedIsFalseAndUsersInGame_UsernameEquals(username, PageRequest.of(page, size))
                    .stream().map(queryMapper::modelToQuery).toList();
        }
        return openGameRepository.findByIsFinishedIsFalseAndUsersInGame_UsernameEquals(username)
                .stream().map(queryMapper::modelToQuery).toList();
    }
}
