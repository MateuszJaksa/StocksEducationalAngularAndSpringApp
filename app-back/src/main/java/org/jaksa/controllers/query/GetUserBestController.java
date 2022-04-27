package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.GetUserBestQuery;
import org.jaksa.services.interfaces.query.GetUserBestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user-best")
public class GetUserBestController {
    private final GetUserBestService service;

    @GetMapping()
    public List<GetUserBestQuery> getUserBest() {
        return service.getUserBest();
    }
}
