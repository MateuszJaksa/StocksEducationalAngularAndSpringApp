package org.jaksa.controllers.command;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.RegisterCommand;
import org.jaksa.services.interfaces.command.RegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService service;
    private final HttpServletRequest request;

    @PostMapping("/register")
    public void register(@RequestBody RegisterCommand command) {
        service.register(command);
    }

    @GetMapping("/logout")
    public void logout() throws ServletException {
        request.logout();
    }
}
