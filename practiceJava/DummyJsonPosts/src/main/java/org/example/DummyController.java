package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class DummyController {
    private final DummyServices dummyServices;

    public DummyController(DummyServices dummyServices) {
        this.dummyServices = dummyServices;
    }

    public List<Post> list(int page, int size) throws IOException {
        return dummyServices.list(page, size);
    }

    public Post create(Post post) throws IOException{
        return dummyServices.create(post);
    }

    public Post update(Integer id, Post post) throws IOException {
        return dummyServices.update(id, post);
    }

    public Post delete(Integer id) throws IOException {
        return dummyServices.delete(id);
    }
}
