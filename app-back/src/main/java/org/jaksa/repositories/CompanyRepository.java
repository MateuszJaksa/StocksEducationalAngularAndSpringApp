package org.jaksa.repositories;

import org.jaksa.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
    CompanyModel findByAbbreviation(String abbreviation);

}
