package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.command.ReceiveDataFromWebCommand;
import org.jaksa.dtos.query.StockWebDataReceivedDto;

import java.util.List;

public interface GetStockDataFromAPIService {
    List<StockWebDataReceivedDto> getStockDataFromAPI(ReceiveDataFromWebCommand command);
}
