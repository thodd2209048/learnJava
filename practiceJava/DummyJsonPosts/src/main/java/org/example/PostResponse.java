package org.example;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostResponse {
    private ArrayList<Post> posts;
    private Integer total;
    private Integer skip;
    private Integer limit;
}
