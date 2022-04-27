package org.jaksa.dtos.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetChartDataQuery implements Serializable {
    private String name;
    private List<GetChartDetailsQuery> series;
}
