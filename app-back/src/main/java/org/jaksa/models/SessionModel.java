package org.jaksa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
public class SessionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,  name = "name")
    private String name;
    @Column(nullable = false, name = "creator")
    private String creator;
    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEN;
    @Column(name = "description_pl", columnDefinition = "TEXT")
    private String descriptionPL;
    @Column(nullable = false,  name = "starting_date")
    private String startingDate;
    @Column(nullable = false,  name = "ending_date")
    private String endingDate;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CompanyModel> companies;
    @Column(nullable = false,  name = "funds")
    private Long funds;
    @CreationTimestamp
    @Column(nullable = false,  name = "created_date")
    private ZonedDateTime createdDate;
    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private Set<OpenGameModel> openGames;
    @OneToMany(mappedBy = "sessionModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionDayModel> sessionDayModels;

}
