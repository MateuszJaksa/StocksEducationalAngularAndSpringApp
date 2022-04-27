package org.jaksa.repositories;

import org.jaksa.models.UserStatsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStatsModel, Long> {
    UserStatsModel findByUsername(String username);

}
