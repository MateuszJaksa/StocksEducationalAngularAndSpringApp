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
public class ReceiveDataFromWebCommand implements Serializable {
    private String startingDate;
    private String endingDate;
    private String companyAbbr;
}
