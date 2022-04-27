package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.CompanyDto;
import org.jaksa.services.interfaces.query.GetCompaniesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class GetAllCompaniesController {
    private final GetCompaniesService service;

    @GetMapping()
    public List<CompanyDto> getAll() {
        return service.getAllCompanies();
    }
}
