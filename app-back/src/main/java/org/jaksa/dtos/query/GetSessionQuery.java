package org.jaksa.dtos.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetSessionQuery implements Serializable {
    private Long id;
    private String name;
    private String creator;
    private String descriptionEN;
    private String descriptionPL;
    private String startingDate;
    private String endingDate;
    private List<CompanyDto> companies;
    private Long funds;
    private ZonedDateTime createdDate;
}
