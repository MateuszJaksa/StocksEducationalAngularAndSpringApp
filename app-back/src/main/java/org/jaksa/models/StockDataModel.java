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
@Table(name = "stock_data")
public class StockDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "company_model_id")
    private CompanyModel companyModel;
    @Column(nullable = false,  name = "datetime")
    private String datetime;
    @Column(nullable = false,  name = "open")
    private Double open;
    @Column(nullable = false,  name = "high")
    private Double high;
    @Column(nullable = false,  name = "low")
    private Double low;
    @Column(nullable = false,  name = "close")
    private Double close;
    @Column(nullable = false,  name = "volume")
    private Double volume;
}