package practice;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
public class Store {
    private String name;
    private String storeCode;
    private String zipcode;

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
