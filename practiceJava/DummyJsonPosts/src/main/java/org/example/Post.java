package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post{
    private Integer id;
    private String title;
    private String body;
    private Integer userId;
    private ArrayList<String> tags;
    private Integer reactions;

}
