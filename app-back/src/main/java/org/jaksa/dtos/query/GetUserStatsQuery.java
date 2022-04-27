package org.jaksa.dtos.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserStatsQuery implements Serializable {
    private String username;
    private Long gamesPlayed;
    private Long highestScore;
    private Double averageScore;
    private Long totalScore;
}
