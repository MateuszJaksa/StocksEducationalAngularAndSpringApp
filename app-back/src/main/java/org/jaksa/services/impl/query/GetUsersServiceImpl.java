package org.jaksa.services.impl.query;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.query.UserDto;
import org.jaksa.mappers.UserMapper;
import org.jaksa.services.interfaces.query.GetUsersService;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUsersServiceImpl implements GetUsersService {
    private final Keycloak keycloakClient;
    private final UserMapper mapper;
    @Value("${keycloak.realm}")
    private String realm;

    @Override
    public List<UserDto> getAllUsers() {
        return keycloakClient.realm(realm).users().list().stream().map(mapper::representationToDto).collect(Collectors.toList());
    }
}
