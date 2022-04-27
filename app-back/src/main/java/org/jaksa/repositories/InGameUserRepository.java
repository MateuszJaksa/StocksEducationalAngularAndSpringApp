package org.jaksa.repositories;

import org.jaksa.models.InGameUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InGameUserRepository extends JpaRepository<InGameUserModel, Long> {
    InGameUserModel findByUsernameAndOpenGame_Id(String username, Long id);
}