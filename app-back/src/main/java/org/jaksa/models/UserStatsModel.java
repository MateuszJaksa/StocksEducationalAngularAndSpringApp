package org.jaksa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_stats")
public class UserStatsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,  name = "username", unique = true)
    private String username;
    @Column(nullable = false,  name = "games_played")
    private Integer gamesPlayed;
    @Column(nullable = false,  name = "highest_score")
    private Double highestScore;
    @Column(nullable = false,  name = "average_score")
    private Double averageScore;
    @Column(nullable = false,  name = "total_score")
    private Double totalScore;

    public UserStatsModel(String username, Integer gamesPlayed, Double highestScore, Double averageScore, Double totalScore) {
        this.username = username;
        this.gamesPlayed = gamesPlayed;
        this.highestScore = highestScore;
        this.averageScore = averageScore;
        this.totalScore = totalScore;
    }
}
