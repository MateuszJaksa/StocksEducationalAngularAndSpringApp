package org.jaksa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class CompanyModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "abbreviation", unique = true)
    private String abbreviation;
    @Column(nullable = false, name = "full_name", unique = true)
    private String fullName;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<SessionModel> sessions;
    @OneToMany(mappedBy = "companyModel",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockDataModel> stockDataModels;

    public CompanyModel(String abbreviation, String fullName) {
        this.abbreviation = abbreviation;
        this.fullName = fullName;
    }
}
