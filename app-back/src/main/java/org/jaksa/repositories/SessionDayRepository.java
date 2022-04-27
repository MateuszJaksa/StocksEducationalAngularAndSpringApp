package org.jaksa.repositories;

import org.jaksa.models.SessionDayModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDayRepository extends JpaRepository<SessionDayModel, Long> {
}