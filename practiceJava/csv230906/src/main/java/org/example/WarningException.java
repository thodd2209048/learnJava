package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarningException extends IllegalArgumentException{
    private DataWarning dataWarning;
}
