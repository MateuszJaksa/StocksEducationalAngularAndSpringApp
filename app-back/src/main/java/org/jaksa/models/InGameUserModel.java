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
@Table(name = "in_game_users")
public class InGameUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,  name = "username")
    private String username;
    @Column(nullable = false,  name = "final_earnings")
    private Double finalEarnings;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "open_game_id", nullable = false)
    private OpenGameModel openGame;
    @Column(nullable = false,  name = "has_finished")
    private Boolean hasFinished;

    public InGameUserModel(String username, Double finalEarnings, OpenGameModel openGame, Boolean hasFinished) {
        this.username = username;
        this.finalEarnings = finalEarnings;
        this.openGame = openGame;
        this.hasFinished = hasFinished;
    }
}
