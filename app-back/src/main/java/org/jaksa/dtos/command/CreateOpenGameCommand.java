package org.jaksa.dtos.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jaksa.dtos.query.UserDto;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOpenGameCommand implements Serializable {
    private String name;
    private Integer timeToGuessInSeconds;
    private Long sessionId;
    private Set<UserDto> usersInGame;
}
