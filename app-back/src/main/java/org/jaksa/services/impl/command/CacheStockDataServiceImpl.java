package org.jaksa.services.impl.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.ReceiveDataFromWebCommand;
import org.jaksa.mappers.ReceivedStockWebDataMapper;
import org.jaksa.models.CompanyModel;
import org.jaksa.models.SessionDayModel;
import org.jaksa.models.SessionModel;
import org.jaksa.models.StockDataModel;
import org.jaksa.repositories.CompanyRepository;
import org.jaksa.repositories.SessionDayRepository;
import org.jaksa.repositories.StockDataRepository;
import org.jaksa.services.interfaces.command.CacheStockDataService;
import org.jaksa.services.interfaces.query.GetStockDataFromAPIService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheStockDataServiceImpl implements CacheStockDataService {
    private final CompanyRepository companyRepository;
    private final StockDataRepository repository;
    private final GetStockDataFromAPIService webService;
    private final ReceivedStockWebDataMapper mapper;
    private final SessionDayRepository sessionDayRepository;

    @Override
    public void cacheStockDataOfSession(SessionModel sessionModel) {
        List<String> sessionDays = new ArrayList<>();
        for (CompanyModel model : sessionModel.getCompanies()) {
            sessionDays = saveStockWebData( new ReceiveDataFromWebCommand(sessionModel.getStartingDate(), sessionModel.getEndingDate(), model.getAbbreviation()));
        }
        sessionDays.forEach(day -> sessionDayRepository.save(new SessionDayModel(day, sessionModel)));
    }

    private List<String> saveStockWebData(ReceiveDataFromWebCommand command) {
        List<StockDataModel> receivedList = mapper.dtosToModels(webService.getStockDataFromAPI(command));
        CompanyModel company = companyRepository.findByAbbreviation(command.getCompanyAbbr());
        if (company != null) {
            for (StockDataModel model : receivedList) {
                if (repository.findByDatetimeAndCompanyModel_Abbreviation(model.getDatetime(), command.getCompanyAbbr()).isEmpty()) {
                    model.setCompanyModel(company);
                    repository.save(model);
                }
            }
        }
        return receivedList.stream().map(StockDataModel::getDatetime).toList();
    }
}
