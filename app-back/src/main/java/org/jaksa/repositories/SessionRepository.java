package org.jaksa.repositories;

import org.jaksa.models.SessionModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionModel, Long> {
    List<SessionModel> findByCreator(String creator);
    List<SessionModel> findByCreator(String creator, Pageable pageable);

    void deleteById(Long id);
}
