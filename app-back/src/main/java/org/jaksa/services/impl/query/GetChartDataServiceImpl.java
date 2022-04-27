package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetChartDataQuery;
import org.jaksa.dtos.query.GetChartDetailsQuery;
import org.jaksa.models.CompanyModel;
import org.jaksa.models.SessionDayModel;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.repositories.StockDataRepository;
import org.jaksa.services.interfaces.query.GetChartDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetChartDataServiceImpl implements GetChartDataService {
    private final SessionRepository sessionRepository;
    private final StockDataRepository stockDataRepository;

    @Override
    public List<GetChartDataQuery> getChartDataForSessionID(Long sessionId) {
        var sessionModel = sessionRepository.findById(sessionId);
        if (sessionModel.isPresent()) {
            List<CompanyModel> companyModels = sessionModel.get().getCompanies();
            List<String> dateTimes = sessionModel.get().getSessionDayModels().stream().map(SessionDayModel::getDatetime).toList();
            return companyModels.stream().map(c -> createGetChartDataDto(c, dateTimes)).toList();
        }
        else {
            return null;
        }
    }

    private GetChartDetailsQuery createGetChartDetailsDto(String datetime, CompanyModel companyModel) {
        var stockData = stockDataRepository.findByDatetimeAndCompanyModel(datetime, companyModel);
        return stockData.map(stockDataModel -> new GetChartDetailsQuery(datetime, stockDataModel.getClose())).orElse(null);
    }

    private GetChartDataQuery createGetChartDataDto(CompanyModel companyModel, List<String> datetimes) {
        List<GetChartDetailsQuery> details = datetimes.stream().map
                (d -> createGetChartDetailsDto(d, companyModel)).sorted(new GetChartDetailsDtoComparator()).collect(Collectors.toList());
        return new GetChartDataQuery(companyModel.getFullName(), details);
    }

    private static class GetChartDetailsDtoComparator implements Comparator<GetChartDetailsQuery>
    {
        private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public int compare(GetChartDetailsQuery c1, GetChartDetailsQuery c2) {
            return LocalDate.parse(c1.getName(), dtf).compareTo(LocalDate.parse(c2.getName(), dtf));
        }
    }
}
