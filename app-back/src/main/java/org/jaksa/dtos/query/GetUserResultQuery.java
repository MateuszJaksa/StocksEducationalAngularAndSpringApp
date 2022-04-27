package org.jaksa.dtos.query;

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
public class GetUserResultQuery implements Serializable {
    private Double yourScore;
    private Set<UserResultDto> otherResultsWithUsernames;
    private Double bondsResult;
    private GetBestResultsQuery bestResults;
}
