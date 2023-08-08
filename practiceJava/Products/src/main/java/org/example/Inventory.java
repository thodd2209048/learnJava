package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
public class Inventory {
    private String storeCode;
    private String sku;
    private Integer quantity;

    @Override
    public String toString() {
        return "Inventory{" +
                "storeCode='" + storeCode + '\'' +
                ", sku='" + sku + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
