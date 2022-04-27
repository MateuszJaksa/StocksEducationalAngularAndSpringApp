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
@Table(name = "session_days")
public class SessionDayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "datetime")
    private String datetime;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "session_model_id")
    private SessionModel sessionModel;

    public SessionDayModel(String datetime, SessionModel sessionModel) {
        this.datetime = datetime;
        this.sessionModel = sessionModel;
    }
}
