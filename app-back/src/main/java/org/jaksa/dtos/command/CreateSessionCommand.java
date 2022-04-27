package org.jaksa.dtos.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jaksa.dtos.query.CompanyDto;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionCommand implements Serializable {
    private String name;
    private String descriptionEN;
    private String descriptionPL;
    private String startingDate;
    private String endingDate;
    private List<CompanyDto> companies;
    private Long funds;
}
