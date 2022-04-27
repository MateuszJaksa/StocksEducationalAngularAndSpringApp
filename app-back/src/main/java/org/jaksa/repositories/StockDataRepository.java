package org.jaksa.repositories;

import org.jaksa.models.CompanyModel;
import org.jaksa.models.StockDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockDataRepository extends JpaRepository<StockDataModel, Long> {
    Optional<StockDataModel> findByDatetimeAndCompanyModel(String datetime, CompanyModel companyModel);

    Optional<StockDataModel> findByDatetimeAndCompanyModel_Abbreviation(String datetime, String abbreviation);
}