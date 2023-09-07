package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private Type type;
}
