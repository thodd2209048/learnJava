package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Post{
    public int id;
    public String title;
    public String body;
    public int userId;
    public ArrayList<String> tags;
    public int reactions;

}
