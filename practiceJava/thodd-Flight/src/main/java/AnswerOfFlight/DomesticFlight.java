package AnswerOfFlight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class DomesticFlight extends Flight {
    private String number;
    private Double distance;

    public Double
}
