package org.jaksa.services.interfaces.command;

import org.jaksa.dtos.command.RegisterCommand;

public interface RegisterService {
    void register(RegisterCommand registerForm);
}
