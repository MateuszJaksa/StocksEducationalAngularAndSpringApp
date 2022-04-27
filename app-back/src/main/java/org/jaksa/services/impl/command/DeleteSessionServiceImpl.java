package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.command.DeleteSessionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSessionServiceImpl implements DeleteSessionService {
    private final SessionRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
