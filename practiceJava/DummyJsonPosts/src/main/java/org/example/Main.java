package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        DummyServices dummyServices = new DummyServices();
        DummyController dummyController = new DummyController(dummyServices);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("java");
        tags.add("programming");
        Post post = new Post(50, "Sample Post", "This is a sample post content.", 1, tags, 10);

//        List<Post> pageOne = dummyController.list(1,10);
//        System.out.println(pageOne);
//        Post newPost = dummyController.create(post);
//        System.out.println(newPost);
            Post postOne = dummyController.delete(1);
        System.out.println(postOne);

    }
}