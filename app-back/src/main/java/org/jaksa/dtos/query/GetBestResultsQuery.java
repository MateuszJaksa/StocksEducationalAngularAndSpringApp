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
public class GetBestResultsQuery implements Serializable {
    private String companyName;
    private Double earnings;
    private String buyDate;
    private String sellDate;
}
