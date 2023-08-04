package Flight;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OverseaFlight extends Flight {
    private String destinationCity;
    private String destinationCountry;


}
