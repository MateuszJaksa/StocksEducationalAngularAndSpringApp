package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.CompanyDto;
import org.jaksa.mappers.CompanyMapper;
import org.jaksa.repositories.CompanyRepository;
import org.jaksa.services.interfaces.query.GetCompaniesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCompaniesServiceImpl implements GetCompaniesService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;

    @Override
    public List<CompanyDto> getAllCompanies() {
        return mapper.modelsToDtos(companyRepository.findAll().stream().toList());
    }
}
