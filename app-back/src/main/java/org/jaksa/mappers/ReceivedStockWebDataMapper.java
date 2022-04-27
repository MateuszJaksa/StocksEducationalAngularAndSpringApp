package org.jaksa.mappers;

import org.jaksa.dtos.query.StockWebDataReceivedDto;
import org.jaksa.models.StockDataModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceivedStockWebDataMapper {
    StockDataModel dtoToModel(StockWebDataReceivedDto dto);

    List<StockDataModel> dtosToModels(List<StockWebDataReceivedDto> dtos);
}