package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
public class Buyer {
    private String name;
    private String zipcode;
    private String sku;
}
