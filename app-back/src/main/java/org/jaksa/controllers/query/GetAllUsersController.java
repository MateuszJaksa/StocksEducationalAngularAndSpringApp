package org.jaksa.controllers.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.UserDto;
import org.jaksa.services.interfaces.query.GetUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GetAllUsersController {
    private final GetUsersService service;

    @GetMapping()
    public List<UserDto> getAll() {
        return service.getAllUsers();
    }
}
