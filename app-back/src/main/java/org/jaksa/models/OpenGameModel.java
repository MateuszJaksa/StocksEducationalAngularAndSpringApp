package org.jaksa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "open_games")
public class OpenGameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,  name = "name", unique = true)
    private String name;
    @Column(nullable = false, name = "creator")
    private String creator;
    @Column(nullable = false,  name = "time_in_seconds")
    private Integer timeToGuessInSeconds;
    @OneToMany(mappedBy = "openGame", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<InGameUserModel> usersInGame;
    @CreationTimestamp
    @Column(nullable = false,  name = "created_date")
    private ZonedDateTime createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionModel session;
    @Column(nullable = false,  name = "is_finished")
    private Boolean isFinished;

    public void addInGameUserModel(InGameUserModel model) {
        usersInGame.add(model);
    }

    public void removeInGameUserModel(InGameUserModel model) {
        usersInGame.remove(model);
    }
}
