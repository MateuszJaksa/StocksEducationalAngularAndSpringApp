package org.jaksa.repositories;

import org.jaksa.models.OpenGameModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenGameRepository extends JpaRepository<OpenGameModel, Long> {
    List<OpenGameModel> findByIsFinishedIsFalse();
    List<OpenGameModel> findByIsFinishedIsFalse(Pageable pageable);

    List<OpenGameModel> findByUsersInGame_UsernameEquals(String username);
    List<OpenGameModel> findByUsersInGame_UsernameEquals(String username, Pageable pageable);

    List<OpenGameModel> findByIsFinishedIsFalseAndUsersInGame_UsernameEquals(String username);
    List<OpenGameModel> findByIsFinishedIsFalseAndUsersInGame_UsernameEquals(String username, Pageable pageable);
}
