package org.jaksa.dtos.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBetCommand implements Serializable {
    private String companyAbbreviation;
    private Long moneySpent;
    private String dayBuying;
    private String daySelling;
}
