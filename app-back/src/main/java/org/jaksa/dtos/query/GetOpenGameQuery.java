package org.jaksa.dtos.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOpenGameQuery implements Serializable {
    private Long id;
    private String name;
    private String creator;
    private Integer timeToGuessInSeconds;
    private Set<String> usersInGameNames;
    private ZonedDateTime createdDate;
    private GetSessionQuery session;
    private Boolean isFinished;
}
