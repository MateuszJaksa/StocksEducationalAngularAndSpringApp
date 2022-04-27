package org.jaksa.services.interfaces.query;

import org.jaksa.dtos.query.UserDto;

import java.util.List;

public interface GetUsersService {
    List<UserDto> getAllUsers();
}
