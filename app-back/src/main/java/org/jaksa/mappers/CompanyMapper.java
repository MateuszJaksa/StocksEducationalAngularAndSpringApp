package org.jaksa.mappers;
import org.jaksa.dtos.query.CompanyDto;
import org.jaksa.models.CompanyModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<CompanyDto> modelsToDtos(List<CompanyModel> models);
    List<CompanyModel> dtosToModels(List<CompanyDto> models);
}
