package org.jaksa.dtos.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSessionCommand implements Serializable {
    private Long id;
    private String name;
    private String descriptionEN;
    private String descriptionPL;
    private String startingDate;
    private String endingDate;
    private Set<Long> companiesIDs;
    private Long funds;
}
