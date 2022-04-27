package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.CompanyDto;

import java.util.List;

public interface GetCompaniesService {
    List<CompanyDto> getAllCompanies();
}
