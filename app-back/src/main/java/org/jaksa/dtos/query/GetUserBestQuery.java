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
public class GetUserBestQuery implements Serializable {
    private Long gameId;
    private Double score;
    private String name;
    private String sessionName;
}
