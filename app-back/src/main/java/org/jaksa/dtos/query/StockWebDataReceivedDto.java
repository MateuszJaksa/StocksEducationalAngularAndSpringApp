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
public class StockWebDataReceivedDto implements Serializable {
    private String datetime;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
}
