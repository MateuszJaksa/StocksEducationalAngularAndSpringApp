package org.jaksa.services.impl.query;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.jaksa.Consts;
import org.jaksa.dtos.command.ReceiveDataFromWebCommand;
import org.jaksa.dtos.query.StockWebDataReceivedDto;
import org.jaksa.services.interfaces.query.GetStockDataFromAPIService;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class GetStockDataFromAPIServiceImpl implements GetStockDataFromAPIService {
    private final Gson gson;
    private final RestTemplate restTemplate;

    @Override
    public List<StockWebDataReceivedDto> getStockDataFromAPI(ReceiveDataFromWebCommand command) {
        List<StockWebDataReceivedDto> list = new ArrayList<>();
        try {
            String url = String.format(Consts.API_REST_PATH, command.getStartingDate(), command.getEndingDate(),
                            command.getCompanyAbbr(), Consts.API_KEY);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            if (response.getBody() != null && response.getBody().contains("\"code\":429")) {
                throw new IOException(Consts.API_429);
            } else if (response.getStatusCodeValue() > 299) {
                throw new IOException("Interaction with external API impossible");
            }
            if (response.getBody() != null) {
                for (Object object : new JSONObject(response.getBody()).getJSONArray("values")) {
                    list.add(gson.fromJson(object.toString(), StockWebDataReceivedDto.class));
                }
            }
        } catch (IOException e) {
            if (e.getMessage().equals(Consts.API_429)) {
                try {
                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                    Callable<List<StockWebDataReceivedDto>> task = () -> getStockDataFromAPI(command);
                    ScheduledFuture<List<StockWebDataReceivedDto>> future = scheduler.schedule(
                            task, 60, TimeUnit.SECONDS);
                    return future.get();
                } catch (InterruptedException | ExecutionException ee) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return list;
    }
}
